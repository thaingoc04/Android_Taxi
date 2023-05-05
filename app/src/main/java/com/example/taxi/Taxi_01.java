package com.example.taxi;

import java.io.Serializable;

public class Taxi_01 implements Serializable {
    private int Id;
    private String carId;
    private float Distance;
    private float unitPrice;
    private int promotion;

    public Taxi_01(){}

    public Taxi_01( String carId, float distance, float unitPrice, int promotion) {

        this.carId = carId;
        Distance = distance;
        this.unitPrice = unitPrice;
        this.promotion = promotion;
    }
    public Taxi_01( int id, String carId, float distance, float unitPrice, int promotion) {
        Id= id;
        this.carId = carId;
        Distance = distance;
        this.unitPrice = unitPrice;
        this.promotion = promotion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public float getDistance() {
        return Distance;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public float TotalPrice(){
        float rate = (float) (this.unitPrice* this.Distance *((100-this.promotion)*1.0/100));
        return Math.round(rate*100f)/1000f;
    }

    @Override
    public String toString(){
        return "Taxi_01{" +
                "ID= " +Id+
                ", CarId= '" + carId + '\'' +
                ", Distance= " +Distance+
                ", Unit Price= " +unitPrice+
                ", Promotion= " +promotion+
                '}';

    }
}
