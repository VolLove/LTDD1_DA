package Orther;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.DetailParcelFragment;
import com.example.myapplication.ListParcelFragment;
import com.example.myapplication.ListTypeParcelFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UpdateTypeParcelFragment;

import java.util.ArrayList;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;

public class TypeParcelAdapter extends ArrayAdapter {

    List<TypeParcel> typeParcels;
    Context context;
    int resource;
    TextView tvName;
    TextView tvPackFree;
    Button btnDelete;
    Button btnEdit;

    public TypeParcelAdapter(Context context, int resource, List<TypeParcel> typeParcelList) {
        super(context, resource, typeParcelList);
        this.context = context;
        this.resource = resource;
        this.typeParcels = typeParcelList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        tvName = convertView.findViewById(R.id.typeparcelTVName);
        tvPackFree = convertView.findViewById(R.id.typeparcelTVPackFree);
        btnDelete = convertView.findViewById(R.id.typeparcelbtnDelete);
        btnEdit = convertView.findViewById(R.id.typeparcelbtnEdit);

        TypeParcel typeParcel = typeParcels.get(position);

        tvName.setText(typeParcel.getTitle());
        tvPackFree.setText(String.valueOf(typeParcel.getPack_free()));

        // Xử lý sự kiện khi nhấn button Xóa
        btnDelete.setOnClickListener(v -> {
            List<Parcel> parcels = MainActivity.databaseHandler.getAllParcels();
            boolean b = false;
            for (Parcel parcel : parcels) {
                if (parcel.getId_type() == typeParcel.getType_id()) {
                    b = true;
                    break;
                }
            }
            if (b == true) {
                Toast.makeText(context, "Có sản phẩm sử dụng!Không thể xóa!", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có chắc muốn xóa loại bưu phẩm này không?").setTitle("Xóa bưu phẩm")
                        .setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.databaseHandler.deleteTypeParcel(typeParcel.getType_id());
                                setData(MainActivity.databaseHandler.getAllTypeParcels());
                                ListTypeParcelFragment.typeParcelAdapter.notifyDataSetChanged();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // Xử lý sự kiện khi nhấn button Sửa
        btnEdit.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("key_type", typeParcel.getType_id());
            UpdateTypeParcelFragment fragment = new UpdateTypeParcelFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return typeParcels.size();
    }

    public void setData(List<TypeParcel> newData) {
        typeParcels = newData;
    }
}
