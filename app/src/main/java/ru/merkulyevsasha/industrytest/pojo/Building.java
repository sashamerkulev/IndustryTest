package ru.merkulyevsasha.industrytest.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class Building implements Serializable {

    private int id;
    private String name;
    private int floors;
    private String builder;

    private transient List<Flat> flats;

    public Building(){
    }

    public Building(int id, String name, String builder, int floors, List<Flat> flats){
        this.id = id;
        this.name = name;
        this.builder = builder;
        this.floors = floors;
        this.flats = flats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }
}
