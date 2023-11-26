package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Model.TypeParcel;
import Orther.TypeParcelAdapter;

public class ListTypeParcelFragment extends Fragment {

    ListView listView;
    List<TypeParcel> typeParcels = new ArrayList<>();
    public  static TypeParcelAdapter typeParcelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_type_parcel, container, false);
        //ánh xạ
        listView = view.findViewById(R.id.list_type_parcel_LvDanhSach);
//main
        typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        typeParcelAdapter = new TypeParcelAdapter(getContext(),R.layout.card_type_parcel_layout,typeParcels);
        listView.setAdapter(typeParcelAdapter);
        return view;
    }
}
