package com.ttcs.shinehair.Class;

public class Day {
    private String Thu;
    private String GioBatDau;
    private String GioKetThuc;
    private int NgayNghi;

    public Day() {
    }

    public Day(String thu, String gioBatDau, String gioKetThuc, int ngayNghi) {
        Thu = thu;
        GioBatDau = gioBatDau;
        GioKetThuc = gioKetThuc;
        NgayNghi = ngayNghi;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    public String getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        GioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        GioKetThuc = gioKetThuc;
    }

    public int getNgayNghi() {
        return NgayNghi;
    }

    public void setNgayNghi(int ngayNghi) {
        NgayNghi = ngayNghi;
    }
}
