package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Model.Parcel;
import Model.Personnel;
import Model.TypeParcel;

public class DetailActivity extends AppCompatActivity {
    ActionMenuView actionMenuView;
    MenuItem menuItem1, menuItem2;
    Parcel parcel;
    TextView tvId, tvname_sender, tvphone_sender, tvname_receiver,
            tvphone_receiver, tvaddress_receiver, tv_personnel,
            tvstatus, tv_type, tvweight, tvdecription, tvdate_get, tvdate_trans, tvtransfree;
    Button btnChange;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        try {
            setEvent();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private void setEvent() throws ParseException {
        //Load content
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Parcel p = (Parcel) bundle.getSerializable("parcel");
        parcel = BunkerActivity.getParcelById(p.getParcel_id());
        UpData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }


        });
        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final CharSequence[] changes = {"Trong kho", "Dang giao", "Đã nhận", "Hàng trả"};
                int selectedCategory = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("Chọn tiến trình bưu phẩm:")
                        .setSingleChoiceItems(changes, selectedCategory, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        parcel.setStatus(i);
                                    }
                                }
                        )
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Parcel parcel1;
                                try {
                                    parcel1 = BunkerActivity.getParcelById(parcel.getParcel_id());
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                if (parcel1 != null) {
                                    BunkerActivity.updateParcel(parcel);
                                    try {
                                        BunkerActivity.data_LV = BunkerActivity.getAllParcels();
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                                Intent intent1 = new Intent();
                                setResult(RESULT_OK,intent1);
                                finish();
                            }
                        })
                        .setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void UpData() {
        tvId.setText("Mã đơn hàng: ");
        tvstatus.setText("Trạng thái: ");
        tvtransfree.setText("Phí cước: ");
        tvname_sender.setText("Người gửi: ");
        tvphone_sender.setText("SDT người gửi: ");
        tvname_receiver.setText("Người nhận: ");
        tvphone_receiver.setText("SDT người nhận: ");
        tv_personnel.setText("Người nhập kho: ");
        tvdate_get.setText("Ngày nhập kho: ");
        tvweight.setText("Cân nặng: ");
        tv_type.setText("Loại : ");
        tvId.setText(tvId.getText().toString() + parcel.getParcel_id());
        tvname_sender.setText(tvname_sender.getText().toString() + parcel.getName_sender());
        tvname_receiver.setText(tvname_receiver.getText().toString() + parcel.getName_receiver());
        tvphone_sender.setText(tvphone_sender.getText().toString() + parcel.getPhone_sender());
        tvphone_receiver.setText(tvphone_receiver.getText().toString() + parcel.getPhone_receiver());
        tvdate_get.setText(tvdate_get.getText().toString() + parcel.getDate_get());
        tvdecription.setText(parcel.getDecription());
        if (parcel.getDate_trans().equals("01/01/0001")) {
            tvdate_trans.setText(tvdate_trans.getText() + "N/A");

        } else {
            tvdate_trans.setText(tvdate_trans.getText() + parcel.getDate_trans().toString());

        }
        tvaddress_receiver.setText(tvaddress_receiver.getText().toString() + parcel.getAddress_receiver());
        tvweight.setText(tvweight.getText().toString() + parcel.getWeight() + " kg");

        if (parcel.getStatus() == 0) {
            btnChange.setBackgroundResource(R.color.primal_pink);
            tvstatus.setText(tvstatus.getText().toString() + "Trong kho");
            btnChange.setText("Xác nhận gửi hàng");
        }
        if (parcel.getStatus() == 1) {
            btnChange.setBackgroundResource(R.color.primal_green);
            tvstatus.setText(tvstatus.getText().toString() + "Đang vận chuyển");
            btnChange.setText("Xác nhận nhận hàng");

        }
        if (parcel.getStatus() == 2) {
            btnChange.setBackgroundResource(R.color.primal_blue);
            tvstatus.setText(tvstatus.getText().toString() + "Đã nhận");
            btnChange.setText("Hàng đã nhận");

        }
        if (parcel.getStatus() == 3) {
            btnChange.setBackgroundResource(R.color.primal_red);
            tvstatus.setText(tvstatus.getText().toString() + "Trả hàng");
            btnChange.setText("Hàng trả lại");

        }
        tvtransfree.setText(tvtransfree.getText().toString() + parcel.getTransport_free() + " VND");
        for (Personnel personnel : BunkerActivity.personnels) {
            if (parcel.getId_personnel() == personnel.getId_personnel()) {
                tv_personnel.setText(tv_personnel.getText().toString() + personnel.getName());
                break;
            }
        }
        for (TypeParcel typeParcel : BunkerActivity.typeParcels) {
            if (typeParcel.getType_id() == parcel.getId_type()) {
                tv_type.setText(tv_type.getText().toString() + typeParcel.getTitle());
            }
        }

    }


    private void setControl() {
        actionMenuView = findViewById(R.id.detailMenu);
        tvId = findViewById(R.id.detailTvId);
        tvname_sender = findViewById(R.id.detailTvSenderName);
        tvname_receiver = findViewById(R.id.detailTvReceiverName);
        tvphone_receiver = findViewById(R.id.detailTvReceiverPhone);
        tvphone_sender = findViewById(R.id.detailTvSenderPhone);
        tv_personnel = findViewById(R.id.detailTvPerso);
        tv_type = findViewById(R.id.detailTvTyle);
        tvaddress_receiver = findViewById(R.id.detailTvAddressRece);
        tvstatus = findViewById(R.id.detailTvStatus);
        tvweight = findViewById(R.id.detailTvWeight);
        tvdecription = findViewById(R.id.detailTvDecrip);
        tvdate_get = findViewById(R.id.detailTvDateAdd);
        tvdate_trans = findViewById(R.id.detailTvDateSent);
        btnChange = findViewById(R.id.detailBtnChange);
        tvtransfree = findViewById(R.id.detailTvFree);
        btnBack = findViewById(R.id.detailBtnBack);
    }
}