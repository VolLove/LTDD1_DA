package Orther;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.DetailParcelFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;

public class ParcelAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Parcel> data;

    List<Parcel> clone;
    List<TypeParcel> typeParcels;



    public ParcelAdapter(Context context, int resource, List<Parcel> data, List<TypeParcel> typeParcels) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.typeParcels = typeParcels;
        clone = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvID = convertView.findViewById(R.id.cardParcelTvID);
        TextView tvStatus = convertView.findViewById(R.id.cardParcelTvStatus);
        TextView tvNameTrans = convertView.findViewById(R.id.cardParcelTvNameTrans);
        TextView tvNameGet = convertView.findViewById(R.id.cardParcelTvNameGet);
        TextView tvDateGet = convertView.findViewById(R.id.cardParcelTvDateGet);
        TextView tvType = convertView.findViewById(R.id.cardParcelTvType);
        TextView tvDateTrans = convertView.findViewById(R.id.cardParcelTvdateTrans);
        LinearLayout llStatus = convertView.findViewById(R.id.cardParcelllStatus);
        Button cardBtnMore = convertView.findViewById(R.id.cardParcelBtnMore);
        Parcel parcel = data.get(position);
        tvID.setText(tvID.getText() + "" + parcel.getParcel_id());
        tvNameTrans.setText(tvNameTrans.getText() + parcel.getName_sender());
        tvNameGet.setText(tvNameGet.getText() + parcel.getName_receiver());
        tvDateGet.setText(tvDateGet.getText() + parcel.getDate_get().toString());
        if (parcel.getDate_trans().equals("1/1/1")) {
            tvDateTrans.setText(tvDateTrans.getText() + "N/A");

        } else {
            tvDateTrans.setText(tvDateTrans.getText() + parcel.getDate_trans().toString());

        }
        if (parcel.getStatus() == 0) {
            tvStatus.setText("Trong kho");
            llStatus.setBackgroundResource(R.color.primal_pink);
        }
        if (parcel.getStatus() == 1) {
            llStatus.setBackgroundResource(R.color.primal_green);
            tvStatus.setText("Đang vận chuyển");
        }
        if (parcel.getStatus() == 2) {
            llStatus.setBackgroundResource(R.color.primal_blue);
            tvStatus.setText("Đã nhận");
        }
        if (parcel.getStatus() == 3) {
            llStatus.setBackgroundResource(R.color.primal_red);
            tvStatus.setText("Trả hàng");
        }
        for (TypeParcel typeParcel : typeParcels) {
            if (parcel.getId_type() == typeParcel.getType_id()) {
                tvType.setText(typeParcel.getTitle());
                break;
            }
        }
        cardBtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("key", parcel.getParcel_id());
                DetailParcelFragment fragment = new DetailParcelFragment();
                fragment.setArguments(bundle);

                // Sử dụng FragmentManager để thay thế Fragment hiện tại bằng Fragment mới
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return convertView;
    }

    public void setData(List<Parcel> newData) {
        data = newData;
    }

    public void searchById(String tparcelId) {
        List<Parcel> filteredList = new ArrayList<>();
        if (tparcelId.length() != 0) {
            int parcelId = Integer.parseInt(tparcelId);
            for (Parcel parcel : data) {
                if (parcel.getParcel_id() == parcelId) {
                    filteredList.add(parcel);
                }
            }
            setData(filteredList);
            notifyDataSetChanged();
        } else {
            setData(clone);
            notifyDataSetChanged();
        }
    }
}
