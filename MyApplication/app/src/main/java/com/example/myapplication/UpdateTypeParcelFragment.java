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

import java.text.DecimalFormat;

import Model.TypeParcel;

public class UpdateTypeParcelFragment extends Fragment {
    EditText edtName, edtPackFree;
    TypeParcel typeParcel = new TypeParcel();
    Button btnClose, btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_update_type_parcel, container, false);
        //ánh xạ
        edtName = view.findViewById(R.id.updateTypeParcelEdtName);
        edtPackFree = view.findViewById(R.id.updateTypeParcelEdtPack);
        btnClose = view.findViewById(R.id.updateTypeBtnClose);
        btnSave = view.findViewById(R.id.updateTypeBtnSave);
        //main
        Bundle bundle = getArguments();
        int i = bundle.getInt("key_type");
        typeParcel = MainActivity.databaseHandler.getTypeParcelById(i);
        edtName.setText(typeParcel.getTitle());
        DecimalFormat decimalFormat = new DecimalFormat("###");
        String formattedPackFree = decimalFormat.format(typeParcel.getPack_free());
        edtPackFree.setText(formattedPackFree);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().length() == 0) {
                    edtName.setError("Vui lòng nhập trường!");
                } else if (edtPackFree.getText().toString().length() == 0) {
                    edtPackFree.setError("Vui lòng nhập trường!");
                } else {
                    typeParcel.setPack_free(Double.valueOf(edtPackFree.getText().toString()));
                    typeParcel.setTitle(edtName.getText().toString());

                    MainActivity.databaseHandler.updateTypeParcel(typeParcel);

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
