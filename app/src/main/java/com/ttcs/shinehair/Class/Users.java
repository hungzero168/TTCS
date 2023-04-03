package com.ttcs.shinehair.Class;

public class Users {
    int id;

    private String email;
    private String password;
    private int typeUser;
    private String name;
    private String sdt;
    private String diaChi;
    private byte[] hinh;

    public Users() {
    }

    public Users(int id, String email, int typeUser, String name) {
        this.id = id;
        this.email = email;
        this.typeUser = typeUser;
        this.name = name;
    }


    public Users(String email, String password, int typeUser, String name, String sdt, String diaChi) {
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
        this.name = name;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public Users(String email, String password, int typeUser) {
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
    }

    public Users(String email, int typeUser, String name) {
        this.email = email;
        this.typeUser = typeUser;
        this.name = name;
    }

    public Users(String email, int typeUser, String name, byte[] hinh) {
        this.email = email;
        this.typeUser = typeUser;
        this.name = name;
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}

