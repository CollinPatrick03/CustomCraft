package me.collinpatrick.customcraft.Models;


import org.bukkit.ChatColor;

import java.sql.Date;

public class Coordinates {
    String nameOfCoordinates;
    String nameOfCreator;
    double xPos;
    double yPos;
    double zPos;
    Date date;

    public Coordinates() {

    }

    public Coordinates(String nameOfCoordinates, String nameOfCreator, double xPos, double yPos, double zPos, Date date) {
        this.nameOfCoordinates = nameOfCoordinates;
        this.nameOfCreator = nameOfCreator;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.date = date;
    }
    @Override
    public String toString() {
        return (ChatColor.DARK_PURPLE + "[" + this.nameOfCoordinates + "] [" + this.nameOfCreator + "] [" + this.date.toString() + "]" + ChatColor.AQUA + " [" + this.xPos + ", " + this.yPos + ", " + this.zPos + "]");
    }

    public String getNameOfCoordinates() {
        return nameOfCoordinates;
    }

    public void setNameOfCoordinates(String nameOfCoordinates) {
        this.nameOfCoordinates = nameOfCoordinates;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }

    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getzPos() {
        return zPos;
    }

    public void setzPos(double zPos) {
        this.zPos = zPos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
