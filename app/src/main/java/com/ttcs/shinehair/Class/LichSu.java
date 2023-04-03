package com.ttcs.shinehair.Class;

public class LichSu {
    int id;
    private String idtendichvu;
    private String ngaydat;
    private String giodat;
    private String gia;
    private String emailkhachhang;
    private String idnhanvien;
    private String trangthai;
    private String ghichu;

    public LichSu() {
    }

    public LichSu(int id,String ngaydat,String giodat,String emailkhachhang,String idtendichvu,String idnhanvien,String trangthai,String ghichu) {
        this.id = id;
        this.idtendichvu = idtendichvu;
        this.ngaydat = ngaydat;
        this.giodat = giodat;
        this.gia = gia;
        this.emailkhachhang = emailkhachhang;
        this.idnhanvien = idnhanvien;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public LichSu(int id, String idtendichvu, String ngaydat, String giodat, String gia, String emailkhachhang, String idnhanvien, String trangthai, String ghichu) {
        this.id = id;
        this.idtendichvu = idtendichvu;
        this.ngaydat = ngaydat;
        this.giodat = giodat;
        this.gia = gia;
        this.emailkhachhang = emailkhachhang;
        this.idnhanvien = idnhanvien;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public LichSu(int id, String idtendichvu, String ngaydat, String giodat, String gia) {
        this.id = id;
        this.idtendichvu = idtendichvu;
        this.ngaydat = ngaydat;
        this.giodat = giodat;
        this.gia = gia;
    }
    public LichSu(String idtendichvu, String ngaydat, String giodat, String gia) {
        this.idtendichvu = idtendichvu;
        this.ngaydat = ngaydat;
        this.giodat = giodat;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdtendichvu() {
        return idtendichvu;
    }

    public void setIdtendichvu(String idtendichvu) {
        this.idtendichvu = idtendichvu;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getGiodat() {
        return giodat;
    }

    public void setGiodat(String giodat) {
        this.giodat = giodat;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getEmailkhachhang() {
        return emailkhachhang;
    }

    public void setEmailkhachhang(String emailkhachhang) {
        this.emailkhachhang = emailkhachhang;
    }

    public String getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(String idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
