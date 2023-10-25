package com.example.myapplication;

import android.content.Intent;

public class PhepTinh {
    private int num1, num2, ketqua, pheptinh;
    String chinhxac;

    public PhepTinh() {
    }

    public PhepTinh(int num1, int num2, int ketqua, int pheptinh, String chinhxac) {
        this.num1 = num1;
        this.num2 = num2;
        this.ketqua = ketqua;
        this.pheptinh = pheptinh;
        this.chinhxac = chinhxac;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getKetqua() {
        return ketqua;
    }

    public void setKetqua(int ketqua) {
        this.ketqua = ketqua;
    }

    public int getPheptinh() {
        return pheptinh;
    }

    public void setPheptinh(int pheptinh) {
        this.pheptinh = pheptinh;
    }

    public String isChinhxac() {
        return chinhxac;
    }

    public void setChinhxac(String chinhxac) {
        this.chinhxac = chinhxac;
    }

    @Override
    public String toString() {

        String x = "";
        String phep = "";
        if (getPheptinh() == 0) {
            phep = "+";
        }

        if (getPheptinh() == 1) {
            phep = "-";
        }

        if (getPheptinh() == 2) {
            phep = "*";
        }

        if (getPheptinh() == 3) {
            phep = "/";
        }
        return num1 + " " + phep + " " + num2 + " = " + ketqua + ": " + chinhxac;
    }
}
