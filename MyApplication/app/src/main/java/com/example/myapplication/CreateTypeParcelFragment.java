package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import Model.TypeParcel;

public class CreateTypeParcelFragment extends Fragment {
    EditText edtName,edtPackFree;
    Button btnClose,btnSave;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_create_type_parcel,container,false);
        //ánh xạ
        edtName = view.findViewById(R.id.createTypeParcelEdtName);
        edtPackFree = view.findViewById(R.id.createTypeParcelEdtPack);
        btnClose = view.findViewById(R.id.createTypeParcelBtnClose);
        btnSave = view.findViewById(R.id.createTypeParcelBtnSave);
        //main
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().length() == 0){
                    edtName.setError("Vui lòng nhập trường!");
                }
                else if (edtPackFree.getText().toString().length() ==0){
                    edtPackFree.setError("Vui lòng nhập trường!");
                }
                else {
                    TypeParcel typeParcel = new TypeParcel();
                    typeParcel.setPack_free(Integer.parseInt(edtPackFree.getText().toString()));
                    typeParcel.setTitle(edtName.getText().toString());

                    MainActivity.databaseHandler.addTypeParcel(typeParcel);

                    ListTypeParcelFragment fragment = new ListTypeParcelFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainFrame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
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
