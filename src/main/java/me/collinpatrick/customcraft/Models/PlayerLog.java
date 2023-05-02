package me.collinpatrick.customcraft.Models;

import java.util.Date;

public class PlayerLog {
    String name;
    int deaths;
    int kills;
    long blocksBroken;
    Date lastLogin;

    public PlayerLog(String name, int deaths, int kills, long blocksBroken, Date lastLogin) {
        this.name = name;
        this.deaths = deaths;
        this.kills = kills;
        this.blocksBroken = blocksBroken;
        this.lastLogin = lastLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public long getBlocksBroken() {
        return blocksBroken;
    }

    public void setBlocksBroken(long blocksBroken) {
        this.blocksBroken = blocksBroken;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
