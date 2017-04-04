package ru.merkulyevsasha.industrytest.pojo;

import java.io.Serializable;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class Flat implements Serializable {

    private int id;
    private int buildingId;
    private int floor;
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
