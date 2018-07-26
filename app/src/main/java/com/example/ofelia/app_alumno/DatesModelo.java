package com.example.ofelia.app_alumno;

public class DatesModelo {
    private String date, typeDoc, carrer;
    private int iconImg;

    public DatesModelo() {

    }

    public DatesModelo(String date, String typeDoc, String carrer, int iconImg) {
        this.date = date;
        this.typeDoc = typeDoc;
        this.carrer = carrer;
        this.iconImg = iconImg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }
}
