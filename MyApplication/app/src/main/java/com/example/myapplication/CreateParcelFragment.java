package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;

public class CreateParcelFragment extends Fragment {
    Button btnCrete, btnDestroy, btnChooseImage;
    EditText edtname_sender, edtphone_sender, edtname_receiver, edtphone_receiver, edtaddress_receiver, edtweight, edtDecription;
    Spinner spType;
    List<TypeParcel> typeParcels;
    ArrayAdapter<TypeParcel> arrayAdapter;
    String imagePath;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create_parcel, container, false);
        //ánh xạ
        spType = view.findViewById(R.id.createSpinType);
        btnCrete = view.findViewById(R.id.createbtnAccept);
        btnDestroy = view.findViewById(R.id.createbtnClose);
        edtaddress_receiver = view.findViewById(R.id.createedtAddress);
        edtname_receiver = view.findViewById(R.id.createedtReceiverName);
        edtname_sender = view.findViewById(R.id.createedtSenderName);
        edtweight = view.findViewById(R.id.createedtWeight);
        edtphone_sender = view.findViewById(R.id.createedtSenderPhone);
        edtphone_receiver = view.findViewById(R.id.createedtReceiverPhone);
        edtDecription = view.findViewById(R.id.createedtDecrip);
        btnChooseImage = view.findViewById(R.id.createbtnChooseImage);
        imageView = view.findViewById(R.id.createivImage);
        //Main
        typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, typeParcels);
        spType.setAdapter(arrayAdapter);

        btnCrete.setOnClickListener(new View.OnClickListener() {
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
                } else if (edtweight.getText().toString().equals("") || Integer.parseInt(edtweight.getText().toString()) == 0) {
                    edtweight.setError("Nhập cân nặng!");
                } else if (edtaddress_receiver.getText().toString().equals("")) {
                    edtaddress_receiver.setError("Cần địa chỉ nhận hàng!");
                } else if (edtDecription.getText().toString().equals("")) {
                    edtDecription.setError("Đừng để trống!");
                } else {
                    Parcel parcel = new Parcel();

                    if (imagePath != null) {
                        parcel.setImage_path(imagePath);
                        String imageFileName = "IMG_" + System.currentTimeMillis() + ".jpg"; // Tạo tên cho ảnh (ví dụ: IMG_1632929097284.jpg)
                        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Thư mục lưu trữ ảnh

                        File imageFile = new File(storageDir, imageFileName);
                        MainActivity.saveImage(getActivity(),Uri.parse(imagePath), imageFile);
                    }
                    parcel.setStatus(0);
                    parcel.setId_type(typeParcels.get(spType.getSelectedItemPosition()).getType_id());
                    parcel.setDate_get_date(new Date());
                    parcel.setDate_trans("1/1/1");
                    parcel.setAddress_receiver(edtaddress_receiver.getText().toString());
                    parcel.setName_receiver(edtname_receiver.getText().toString());
                    parcel.setName_sender(edtname_sender.getText().toString());
                    parcel.setPhone_receiver(edtphone_receiver.getText().toString());
                    parcel.setPhone_sender(edtphone_sender.getText().toString());
                    parcel.setWeight(Integer.parseInt(edtweight.getText().toString()));
                    parcel.setDecription(edtDecription.getText().toString());
                    MainActivity.databaseHandler.addParcel(parcel);
                    ListParcelFragment fragment = new ListParcelFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainFrame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        btnDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 99);
            }
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            imagePath = uri.toString();
        }
    }
}
