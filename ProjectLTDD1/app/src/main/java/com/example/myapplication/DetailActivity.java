package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import Model.Parcel;
import Model.Personnel;
import Model.TypeParcel;

public class DetailActivity extends AppCompatActivity {
    ActionMenuView actionMenuView;
    MenuItem menuItem1, menuItem2;
    Parcel parcel;
    TextView tvId, tvname_sender, tvphone_sender, tvname_receiver,
            tvphone_receiver, tvaddress_receiver, tv_personnel,
            tvstatus, tv_type, tvweight, tvdecription, tvdate_get, tvdate_trans, tvtransfree;
    Button btnChange;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        setEvent();
    }


    private void setEvent() {
        //Load content
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        parcel = (Parcel) bundle.getSerializable("parcel");

        UpData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }


        });
        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final CharSequence[] changes = {"Trong kho", "Dang giao", "Đã nhận", "Hàng trả"};
                int selectedCategory = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
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
                                for (int x = 0; x < BunkerActivity.data_LV.size(); x++) {
                                    if (parcel.getParcel_id() == BunkerActivity.data_LV.get(x).getParcel_id()) {
                                        BunkerActivity.data_LV.get(x).setStatus(parcel.getStatus());
                                    }
                                }
                                BunkerActivity.parcelAdapter.notifyDataSetChanged();
                                finish();
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
        //delete
        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent intent1 = new Intent(DetailActivity.this, UpdateActivity.class);
                int id = -1;
                for (int i = 0; i < BunkerActivity.data_LV.size(); i++) {
                    if (BunkerActivity.data_LV.get(i).getParcel_id() == parcel.getParcel_id()) {
                        id = i;
                        break;
                    }
                }
                intent1.putExtra("ID", id);
                startActivityForResult(intent1, 2);
                return false;
            }
        });
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Bạn có chắc muốn xóa bưu phẩm này không?").setTitle("Xóa bưu phẩm")
                        .setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (Parcel parcel1 : BunkerActivity.data_LV) {
                                    if (parcel.getParcel_id() == parcel1.getParcel_id()) {
                                        BunkerActivity.data_LV.remove(parcel1);
                                        break;
                                    }
                                }
                                Intent intent1 = new Intent();
                                setResult(1, intent1);
                                finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

    }

    private void UpData() {
        tvId.setText("Mã đơn hàng: ");
        tvstatus.setText("Trạng thái: ");
        tvtransfree.setText("Phí cước: ");
        tvname_sender.setText("Người gửi: ");
        tvphone_sender.setText("SDT người gửi: ");
        tvname_receiver.setText("Người nhận: ");
        tvphone_receiver.setText("SDT người nhận: ");
        tv_personnel.setText("Người nhập kho: ");
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
        for (Personnel personnel : BunkerActivity.personnels) {
            if (parcel.getId_personnel() == personnel.getId_personnel()) {
                tv_personnel.setText(tv_personnel.getText().toString() + personnel.getName());
                break;
            }
        }
        for (TypeParcel typeParcel : BunkerActivity.typeParcels) {
            if (typeParcel.getType_id() == parcel.getId_type()) {
                tv_type.setText(tv_type.getText().toString() + typeParcel.getTitle());
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2 && resultCode == 2) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            parcel = (Parcel) data.getSerializableExtra("update");
            UpData();
        }
    }

    private void setControl() {
        actionMenuView = findViewById(R.id.detailMenu);
        menuItem1 = actionMenuView.getMenu().add(Menu.NONE, 1, Menu.NONE, "Chỉnh sửa");
        menuItem2 = actionMenuView.getMenu().add(Menu.NONE, 2, Menu.NONE, "Xóa");
        tvId = findViewById(R.id.detailTvId);
        tvname_sender = findViewById(R.id.detailTvSenderName);
        tvname_receiver = findViewById(R.id.detailTvReceiverName);
        tvphone_receiver = findViewById(R.id.detailTvReceiverPhone);
        tvphone_sender = findViewById(R.id.detailTvSenderPhone);
        tv_personnel = findViewById(R.id.detailTvPerso);
        tv_type = findViewById(R.id.detailTvTyle);
        tvaddress_receiver = findViewById(R.id.detailTvAddressRece);
        tvstatus = findViewById(R.id.detailTvStatus);
        tvweight = findViewById(R.id.detailTvWeight);
        tvdecription = findViewById(R.id.detailTvDecrip);
        tvdate_get = findViewById(R.id.detailTvDateAdd);
        tvdate_trans = findViewById(R.id.detailTvDateSent);
        btnChange = findViewById(R.id.detailBtnChange);
        tvtransfree = findViewById(R.id.detailTvFree);
        btnBack = findViewById(R.id.detailBtnBack);
    }
}