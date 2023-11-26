package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {
    Button btnTableParcel, btnTableTypeParcel, btnClose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        //ánh xạ
        btnClose = view.findViewById(R.id.homebntClose);
        btnTableParcel = view.findViewById(R.id.homebntParcels);
        btnTableTypeParcel = view.findViewById(R.id.homebntTypeParcels);
        //main

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        btnTableParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListParcelFragment fragment = new ListParcelFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainFrame, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnTableTypeParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListTypeParcelFragment fragment = new ListTypeParcelFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainFrame, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
