package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

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
    public static ParcelAdapter parcelAdapter;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_parcel, container, false);
        //Ánh xạ
        listView = view.findViewById(R.id.list_parcel_LvDanhSach);
        parcels = MainActivity.databaseHandler.getAllParcels();
        typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        spinner = view.findViewById(R.id.parcelSp);
        editText = view.findViewById(R.id.list_parcel_EdtSearch);
        //main
        parcelAdapter = new ParcelAdapter(requireContext(), R.layout.card_parcel_layout, parcels, typeParcels);
        listView.setAdapter(parcelAdapter);
        final CharSequence[] changes = {"All", "Trong kho", "Dang giao", "Đã nhận", "Hàng trả"};
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, changes);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    parcels = MainActivity.databaseHandler.getParcelsByStatus(spinner.getSelectedItemPosition()-1);
                    parcelAdapter = new ParcelAdapter(requireContext(), R.layout.card_parcel_layout, parcels, typeParcels);
                    listView.setAdapter(parcelAdapter);
                } else {
                    parcels = MainActivity.databaseHandler.getAllParcels();
                    parcelAdapter = new ParcelAdapter(requireContext(), R.layout.card_parcel_layout, parcels, typeParcels);
                    listView.setAdapter(parcelAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = editText.getText().toString();
                parcelAdapter.searchById(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
}
