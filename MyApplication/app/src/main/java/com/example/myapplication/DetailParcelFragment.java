package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHandler;
import Model.Parcel;
import Model.TypeParcel;

public class DetailParcelFragment extends Fragment {
    Parcel parcel;
    TextView tvId, tvname_sender, tvphone_sender, tvname_receiver,
            tvphone_receiver, tvaddress_receiver,
            tvstatus, tv_type, tvweight, tvdecription, tvdate_get, tvdate_trans, tvtransfree;
    Button btnChange, btnDelete, btnEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_detail_parcel, container, false);
        //ánh xạ
        tvId = view.findViewById(R.id.detailTvId);
        tvname_sender = view.findViewById(R.id.detailTvSenderName);
        tvname_receiver = view.findViewById(R.id.detailTvReceiverName);
        tvphone_receiver = view.findViewById(R.id.detailTvReceiverPhone);
        tvphone_sender = view.findViewById(R.id.detailTvSenderPhone);
        tv_type = view.findViewById(R.id.detailTvTyle);
        tvaddress_receiver = view.findViewById(R.id.detailTvAddressRece);
        tvstatus = view.findViewById(R.id.detailTvStatus);
        tvweight = view.findViewById(R.id.detailTvWeight);
        tvdecription = view.findViewById(R.id.detailTvDecrip);
        tvdate_get = view.findViewById(R.id.detailTvDateAdd);
        tvdate_trans = view.findViewById(R.id.detailTvDateSent);
        btnChange = view.findViewById(R.id.detailBtnChange);
        tvtransfree = view.findViewById(R.id.detailTvFree);
        btnDelete = view.findViewById(R.id.detailBtnDelete);
        btnEdit = view.findViewById(R.id.detailBtnEdit);
        //main
        Bundle bundle = getArguments();
        int i = bundle.getInt("key");
        parcel = MainActivity.databaseHandler.getParcelById(i);
        UpData();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] changes = {"Trong kho", "Dang giao", "Đã nhận", "Hàng trả"};
                int selectedCategory = parcel.getStatus();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Chọn tiến trình bưu phẩm:")
                        .setSingleChoiceItems(changes, selectedCategory, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        parcel.setStatus(i);
                                    }
                                }
                        )
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Parcel parcel1;
                                parcel1 = MainActivity.databaseHandler.getParcelById(parcel.getParcel_id());
                                if (parcel1 != null) {
                                    MainActivity.databaseHandler.updateParcel(parcel);
                                    ListParcelFragment fragment = new ListParcelFragment();
                                    // Sử dụng FragmentManager để thay thế Fragment hiện tại bằng Fragment mới
                                    ListParcelFragment.parcels = MainActivity.databaseHandler.getAllParcels();
                                    ListParcelFragment.parcelAdapter.setData(MainActivity.databaseHandler.getAllParcels());
                                    ListParcelFragment.parcelAdapter.notifyDataSetChanged();
                                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                    fragmentManager.popBackStack();

                                }
                            }
                        })
                        .setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("key",parcel.getParcel_id());
                UpdateParcelFragment fragment = new UpdateParcelFragment();
                fragment.setArguments(bundle);

                // Sử dụng FragmentManager để thay thế Fragment hiện tại bằng Fragment mới
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Bạn có chắc muốn xóa bưu phẩm này không?").setTitle("Xóa bưu phẩm")
                        .setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                List<Parcel> list = new ArrayList<>();
                                Parcel parcel1;
                                parcel1 = MainActivity.databaseHandler.getParcelById(parcel.getParcel_id());
                                if (parcel1 != null) {
                                    MainActivity.databaseHandler.deleteParcel(parcel.getParcel_id());
                                    ListParcelFragment.parcels = MainActivity.databaseHandler.getAllParcels();
                                    ListParcelFragment.parcelAdapter.setData(MainActivity.databaseHandler.getAllParcels());
                                    ListParcelFragment.parcelAdapter.notifyDataSetChanged();
                                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                    fragmentManager.popBackStack();
                                }

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }


    private void UpData() {
        tvId.setText("Mã đơn hàng: ");
        tvstatus.setText("Trạng thái: ");
        tvtransfree.setText("Phí cước: ");
        tvname_sender.setText("Người gửi: ");
        tvphone_sender.setText("SDT người gửi: ");
        tvname_receiver.setText("Người nhận: ");
        tvphone_receiver.setText("SDT người nhận: ");
        tvdate_get.setText("Ngày nhập kho: ");
        tvweight.setText("Cân nặng: ");
        tv_type.setText("Loại : ");
        tvId.setText(tvId.getText().toString() + parcel.getParcel_id());
        tvname_sender.setText(tvname_sender.getText().toString() + parcel.getName_sender());
        tvname_receiver.setText(tvname_receiver.getText().toString() + parcel.getName_receiver());
        tvphone_sender.setText(tvphone_sender.getText().toString() + parcel.getPhone_sender());
        tvphone_receiver.setText(tvphone_receiver.getText().toString() + parcel.getPhone_receiver());
        tvdate_get.setText(tvdate_get.getText().toString() + parcel.getDate_get());
        tvdecription.setText(parcel.getDecription());
        if (parcel.getDate_trans().equals("01/01/0001")) {
            tvdate_trans.setText(tvdate_trans.getText() + "N/A");

        } else {
            tvdate_trans.setText(tvdate_trans.getText() + parcel.getDate_trans().toString());

        }
        tvaddress_receiver.setText(tvaddress_receiver.getText().toString() + parcel.getAddress_receiver());
        tvweight.setText(tvweight.getText().toString() + parcel.getWeight() + " kg");

        if (parcel.getStatus() == 0) {
            btnChange.setBackgroundResource(R.color.primal_pink);
            tvstatus.setText(tvstatus.getText().toString() + "Trong kho");
            btnChange.setText("Xác nhận gửi hàng");
        }
        if (parcel.getStatus() == 1) {
            btnChange.setBackgroundResource(R.color.primal_green);
            tvstatus.setText(tvstatus.getText().toString() + "Đang vận chuyển");
            btnChange.setText("Xác nhận nhận hàng");

        }
        if (parcel.getStatus() == 2) {
            btnChange.setBackgroundResource(R.color.primal_blue);
            tvstatus.setText(tvstatus.getText().toString() + "Đã nhận");
            btnChange.setText("Hàng đã nhận");

        }
        if (parcel.getStatus() == 3) {
            btnChange.setBackgroundResource(R.color.primal_red);
            tvstatus.setText(tvstatus.getText().toString() + "Trả hàng");
            btnChange.setText("Hàng trả lại");

        }
        tvtransfree.setText(tvtransfree.getText().toString() + parcel.getTransport_free() + " VND");
        List<TypeParcel> typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        for (TypeParcel typeParcel : typeParcels) {
            if (typeParcel.getType_id() == parcel.getId_type()) {
                tv_type.setText(tv_type.getText().toString() + typeParcel.getTitle());
            }
        }
    }

}
