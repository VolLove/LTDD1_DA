package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Database.DatabaseHandler;

public class ChartFragment extends Fragment {
    BarChart barChart;

    @Nullable
    @Override
    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chart, container, false);
//        ánh xạ
        barChart = view.findViewById(R.id.barChart);

        //main
        DatabaseHandler db = new DatabaseHandler(getActivity());

        Cursor cursor = db.GetDataChart();
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> dateGets = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String dateGet = cursor.getString(cursor.getColumnIndex("date_get"));
                    int status1Count = cursor.getInt(cursor.getColumnIndex("status_1_count"));
                    int status2Count = cursor.getInt(cursor.getColumnIndex("status_2_count"));
                    int status3Count = cursor.getInt(cursor.getColumnIndex("status_3_count"));
                    int status4Count = cursor.getInt(cursor.getColumnIndex("status_4_count"));
                    //
                    dateGets.add(dateGet);
                    entries.add(new BarEntry(entries.size(), new float[]{status1Count, status2Count, status3Count, status4Count}));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setStackLabels(new String[]{"Trong kho", "Đang chuyển", "Đã nhận", "Trả hàng"});
        BarData barData = new BarData(dataSet);
        // Tạo một ArrayList chứa các màu cho từng cột
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        dataSet.setColors(colors);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getBarLabel(BarEntry barEntry) {
                return String.format("%.0f",barEntry.getY());
            }
        };
        barData.setValueFormatter(formatter);
        barData.setValueTextSize(20f);
        // Gán dữ liệu vào biểu đồ
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dateGets));

        barChart.animateY(2000);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.invalidate();
        return view;
    }
}
