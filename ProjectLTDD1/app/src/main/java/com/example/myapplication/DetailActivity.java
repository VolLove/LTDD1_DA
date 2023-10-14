package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;

public class DetailActivity extends AppCompatActivity {
    ActionMenuView actionMenuView;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
    private void setLoad() {

        menuItem.setIcon(R.drawable.ic_launcher_background);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        actionMenuView.requestLayout();
    }

    private void setControl() {
        actionMenuView = findViewById(R.id.detailMenu);
        menuItem = actionMenuView.getMenu().add(Menu.NONE, 1 , Menu.NONE ,"Chỉn sửa");
        menuItem = actionMenuView.getMenu().add(Menu.NONE, 2 , Menu.NONE ,"Xóa");
    }
}