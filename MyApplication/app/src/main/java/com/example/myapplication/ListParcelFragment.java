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

import Model.Parcel;
import Model.TypeParcel;
import Orther.ParcelAdapter;

public class ListParcelFragment extends Fragment {
    ListView listView;
    public static List<Parcel> parcels = new ArrayList<>();
    List<TypeParcel> typeParcels = new ArrayList<>();
    public static    ParcelAdapter parcelAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_parcel, container, false);
        listView = view.findViewById(R.id.list_parcel_LvDanhSach);
        parcels =  MainActivity.databaseHandler.getAllParcels();
        typeParcels =  MainActivity.databaseHandler.getAllTypeParcels();
        parcelAdapter = new ParcelAdapter(requireContext(),R.layout.card_parcel_layout,parcels,typeParcels);
        listView.setAdapter(parcelAdapter);
        return view;
    }
}
