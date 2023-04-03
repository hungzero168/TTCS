package com.ttcs.shinehair.Class;

public class DichVu {
    public int ID;
    public String TenDichVu;
    public String GiaDichVu;
    public String ChiTiet;
    public byte[] hinh;

    public DichVu() {
    }

    public DichVu(int ID, String tenDichVu, String giaDichVu, String chiTiet) {
        this.ID = ID;
        TenDichVu = tenDichVu;
        GiaDichVu = giaDichVu;
        ChiTiet = chiTiet;
    }
    public DichVu(int ID, String tenDichVu, String giaDichVu, String chiTiet, byte[] hinhanh) {
        this.ID = ID;
        TenDichVu = tenDichVu;
        GiaDichVu = giaDichVu;
        ChiTiet = chiTiet;
        this.hinh = hinhanh;
    }
    public DichVu(String tenDichVu, String giaDichVu, String chiTiet) {
        this.ID = ID;
        TenDichVu = tenDichVu;
        GiaDichVu = giaDichVu;
        ChiTiet = chiTiet;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        TenDichVu = tenDichVu;
    }

    public String getGiaDichVu() {
        return GiaDichVu;
    }

    public void setGiaDichVu(String giaDichVu) {
        GiaDichVu = giaDichVu;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        ChiTiet = chiTiet;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}