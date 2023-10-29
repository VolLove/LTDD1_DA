package Orther;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BunkerActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.Parcel;
import Model.TypeParcel;

public class ParcelAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Parcel> data,dataClone;



    public ParcelAdapter(Context context, int resource, List<Parcel> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
        dataClone = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvID = convertView.findViewById(R.id.cardTvID);
        TextView tvTitleParcel = convertView.findViewById(R.id.cardtvTitleParcel);
        TextView tvStatus = convertView.findViewById(R.id.cardTvStatus);
        TextView tvNameTrans = convertView.findViewById(R.id.cardTvNameTrans);
        TextView tvNameGet = convertView.findViewById(R.id.cardTvNameGet);
        TextView tvDateGet = convertView.findViewById(R.id.cardTvDateGet);
        TextView tvDateTrans = convertView.findViewById(R.id.cardTvdateTrans);
        LinearLayout llStatus = convertView.findViewById(R.id.cardllStatus);

        Parcel parcel = data.get(position);

        tvID.setText(tvID.getText() + "" + parcel.getParcel_id());
        tvTitleParcel.setText(tvTitleParcel.getText() + parcel.getTitle());
        tvNameTrans.setText(tvNameTrans.getText() + parcel.getName_sender());
        tvNameGet.setText(tvNameGet.getText() + parcel.getName_receiver());
        tvDateGet.setText(tvDateGet.getText() + parcel.getDate_get().toString());
        if (parcel.getDate_trans().equals("01/01/0001")) {
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
        return convertView;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.values = dataClone;
                    results.count = dataClone.size();
                } else {
                    int x = Integer.parseInt(constraint.toString());
                    ArrayList<Parcel> parcels = new ArrayList<>();

                    for (Parcel c : data) {
                        if (c.getParcel_id() == x) {
                            parcels.add(c);
                        }
                    }

                    results.values = parcels;
                    results.count = parcels.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (ArrayList<Parcel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
