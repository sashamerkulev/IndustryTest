package ru.merkulyevsasha.industrytest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class Flat implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("buildingId")
    @Expose
    private int buildingId;
    @SerializedName("floor")
    @Expose
    private int floor;
    @SerializedName("square")
    @Expose
    private int square;

    public Flat(){
    }

    public Flat(int id, int buildingId, int floor, int square){
        this.id = id;
        this.buildingId = buildingId;
        this.floor = floor;
        this.square = square;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
