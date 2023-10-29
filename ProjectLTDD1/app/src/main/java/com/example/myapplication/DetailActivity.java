package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;

public class DetailActivity extends AppCompatActivity {
    ActionMenuView actionMenuView;
    MenuItem menuItem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        setEvent();
    }


    private void setEvent() {
        menuItem1.setIcon(R.drawable.ic_launcher_background);
        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
        actionMenuView.requestLayout();
    }

    private void setControl() {

        actionMenuView = findViewById(R.id.detailMenu);
        menuItem1 = actionMenuView.getMenu().add(Menu.NONE, 1, Menu.NONE, "Chỉnh sửa");
        menuItem1 = actionMenuView.getMenu().add(Menu.NONE, 2, Menu.NONE, "Xóa");
    }
}