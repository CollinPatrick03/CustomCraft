package me.collinpatrick.customcraft.Models;

import java.sql.Date;

public class BlockLog {
    int blockLogId;
    String nameOfBlockInvolved;
    String nameOfPlayerInvolved;
    Date date;

    double xPos;
    double yPos;
    double zPos;

    boolean placeOrBreak;

    public BlockLog(int blockLogId, String nameOfBlockInvolved, String nameOfPlayerInvolved, boolean placeOrBreak, Date date, double xPos, double yPos, double zPos) {
        this.blockLogId = blockLogId;
        this.nameOfBlockInvolved = nameOfBlockInvolved;
        this.nameOfPlayerInvolved = nameOfPlayerInvolved;
        this.placeOrBreak = placeOrBreak;
        this.date = date;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public int getBlockLogId() {
        return blockLogId;
    }

    public void setBlockLogId(int blockLogId) {
        this.blockLogId = blockLogId;
    }

    public String getNameOfBlockInvolved() {
        return nameOfBlockInvolved;
    }

    public void setNameOfBlockInvolved(String nameOfBlockInvolved) {
        this.nameOfBlockInvolved = nameOfBlockInvolved;
    }

    public String getNameOfPlayerInvolved() {
        return nameOfPlayerInvolved;
    }

    public void setNameOfPlayerInvolved(String nameOfPlayerInvolved) {
        this.nameOfPlayerInvolved = nameOfPlayerInvolved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getPlaceOrBreak() {
        return placeOrBreak;
    }

    public void setPlaceOrBreak(boolean placeOrBreak) {
        this.placeOrBreak = placeOrBreak;
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
}
