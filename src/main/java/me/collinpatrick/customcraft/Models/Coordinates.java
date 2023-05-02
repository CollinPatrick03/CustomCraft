package me.collinpatrick.customcraft.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Coordinates {
    private Double xPos;
    private Double yPos;
    private Double zPos;
    private String nameOfCreator;
    private String nameOfCoords;
    private String dateCreated;
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public Coordinates() {

    }

    public Coordinates(Double xPos, Double yPos, Double zPos, String nameOfCoords, String nameOfCreator, Date dateCreated) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.nameOfCoords = nameOfCoords;
        this.nameOfCreator = nameOfCreator;
        this.dateCreated = format.format(dateCreated);
    }

    public Double getxPos() {
        return xPos;
    }

    public Double getyPos() {
        return yPos;
    }

    public Double getzPos() {
        return zPos;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }


    public void setName(String name) {
        this.nameOfCreator = name;
    }

    public void setxPos(Double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(Double yPos) {
        this.yPos = yPos;
    }

    public void setzPos(Double zPos) {
        this.zPos = zPos;
    }

    public String getNameOfCoords() {
        return nameOfCoords;
    }

    public void setNameOfCoords(String nameOfCoords) {
        this.nameOfCoords = nameOfCoords;
    }
}
