package sample.GestionReservation.model;

import java.util.Date;

public class Fruit {
    private String idF;
    private String name;
    private String imgSrc;
    private double price;
    private String color;
    private Date date;

    public Fruit(String idf, String name, String imgSrc, double price, String color,Date date) {
        this.idF=idf;
        this.name = name;
        this.imgSrc = imgSrc;
        this.price = price;
        this.color = color;
        this.date=date;
    }

    public String getIdF() {
        return idF;
    }

    public void setIdF(String idF) {
        this.idF = idF;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
