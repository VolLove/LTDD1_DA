package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;

public class UpdateParcelFragment extends Fragment {
    EditText edtname_sender, edtphone_sender, edtname_receiver, edtphone_receiver, edtaddress_receiver, edtdecription, edtweight;
    Spinner sptype;
    Button btnSave, btnClose;
    Parcel parcel;
    List<TypeParcel> typeParcels;
    ArrayAdapter<TypeParcel> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_update, container, false);
        //ánh xạ
        edtname_receiver = view.findViewById(R.id.updateedtReceiverName);
        edtname_sender = view.findViewById(R.id.updateedtSenderName);
        edtphone_receiver = view.findViewById(R.id.updateedtReceiverPhone);
        edtphone_sender = view.findViewById(R.id.updateedtSenderPhone);
        edtaddress_receiver = view.findViewById(R.id.updateedtAddress);
        edtdecription = view.findViewById(R.id.updateedtDecrip);
        edtweight = view.findViewById(R.id.updateedtWeight);
        sptype = view.findViewById(R.id.updateSpinType);
        btnSave = view.findViewById(R.id.updatebtnAccept);
        btnClose = view.findViewById(R.id.updatebtnClose);

        //main
        Bundle bundle =getArguments();
        int i = bundle.getInt("Key");
        parcel = MainActivity.databaseHandler.getParcelById(i);

        typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, typeParcels);
        sptype.setAdapter(arrayAdapter);

        edtname_sender.setText(parcel.getName_sender());
        edtname_receiver.setText(parcel.getName_receiver());
        edtphone_sender.setText(parcel.getPhone_sender());
        edtphone_receiver.setText(parcel.getPhone_receiver());
        edtdecription.setText(parcel.getDecription());
        edtweight.setText(String.valueOf(parcel.getWeight()));
        edtaddress_receiver.setText(parcel.getAddress_receiver());
        for (int x = 0; x < typeParcels.size(); x++) {
            if (parcel.getId_type() == typeParcels.get(x).getType_id()) {
                sptype.setSelection(x);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtname_sender.getText().toString().equals("")) {
                    edtname_sender.setError("Vui lòng nhập tên người gửi!");
                } else if (edtphone_sender.getText().toString().equals("")) {
                    edtphone_sender.setError("Vui lòng nhập số điện thoại!");
                } else if (edtname_receiver.getText().toString().equals("")) {
                    edtname_receiver.setError("Vui lòng nhập tên người nhận!");
                } else if (edtphone_receiver.getText().toString().equals("")) {
                    edtphone_receiver.setError("Vui lòng nhập số điện thoại!");
                } else if (edtweight.getText().toString().equals("")) {
                    edtweight.setError("Nhập cân nặng!");
                } else if (edtaddress_receiver.getText().toString().equals("")) {
                    edtaddress_receiver.setError("Cần địa chỉ nhận hàng!");
                } else if (edtdecription.getText().toString().equals("")) {
                    edtdecription.setError("Đừng để trống!");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setTitle("Update!")
                            .setMessage("Bạn có muốn lưu thay đổi!")
                            .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    parcel.setId_type(sptype.getSelectedItemPosition());
                                    parcel.setAddress_receiver(edtaddress_receiver.getText().toString());
                                    parcel.setName_receiver(edtname_receiver.getText().toString());
                                    parcel.setName_sender(edtname_sender.getText().toString());
                                    parcel.setPhone_receiver(edtphone_receiver.getText().toString());
                                    parcel.setPhone_sender(edtphone_sender.getText().toString());
                                    try {
                                        parcel.setWeight(Double.valueOf(edtweight.getText().toString()));
                                    } catch (NumberFormatException e) {
                                    }
                                    parcel.setDecription(edtdecription.getText().toString());
                                    MainActivity.databaseHandler.updateParcel(parcel);
                                    ListParcelFragment fragment = new ListParcelFragment();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.replace(R.id.mainFrame, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();

                                }
                            })
                            .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        return view;
    }
}
