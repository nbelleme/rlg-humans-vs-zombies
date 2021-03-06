package io.resourcepool.hvsz.game;


import java.io.Serializable;

public class GameSettings implements Serializable {

    Integer gameDuration;
    Integer difficulty;
    Integer humanTickets;
    Integer maxHumansOnField;
    Integer nbSafeZones;
    Integer startingSafeZoneSupplies;
    Integer nbFoodSupplyZones;
    Integer nbFoodSupplies;
    Integer maximumFoodTransfer;

    // Generated values with difficulty
    Integer safezoneDropRate;

    /**
     * GameConfig constructor.
     */
    public GameSettings() {
    }

    public Integer getMaxHumansOnField() {
        return maxHumansOnField;
    }

    public void setMaxHumansOnField(Integer maxHumansOnField) {
        this.maxHumansOnField = maxHumansOnField;
    }

    public Integer getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Integer gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getHumanTickets() {
        return humanTickets;
    }

    public void setHumanTickets(Integer humanTickets) {
        this.humanTickets = humanTickets;
    }

    public Integer getNbSafeZones() {
        return nbSafeZones;
    }

    public void setNbSafeZones(Integer nbSafezone) {
        this.nbSafeZones = nbSafezone;
    }

    public Integer getNbFoodSupplyZones() {
        return nbFoodSupplyZones;
    }

    public void setNbFoodSupplyZones(Integer nbFoodSupplyZones) {
        this.nbFoodSupplyZones = nbFoodSupplyZones;
    }

    public Integer getNbFoodSupplies() {
        return nbFoodSupplies;
    }

    public void setNbFoodSupplies(Integer nbFoodSupplies) {
        this.nbFoodSupplies = nbFoodSupplies;
    }

    public Integer getSafezoneDropRate() {
        return safezoneDropRate;
    }

    public void setSafezoneDropRate(Integer safezoneDropRate) {
        this.safezoneDropRate = safezoneDropRate;
    }

    public Integer getMaximumFoodTransfer() {
        return maximumFoodTransfer;
    }

    public void setMaximumFoodTransfer(Integer maximumFoodTransfer) {
        this.maximumFoodTransfer = maximumFoodTransfer;
    }

    public Integer getStartingSafeZoneSupplies() {
        return startingSafeZoneSupplies;
    }

    public void setStartingSafeZoneSupplies(Integer startingSafeZoneSupplies) {
        this.startingSafeZoneSupplies = startingSafeZoneSupplies;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "gameDuration=" + gameDuration +
                ", difficulty=" + difficulty +
                ", humanTickets=" + humanTickets +
                ", nbSafeZones=" + nbSafeZones +
                ", nbFoodSupplyZones=" + nbFoodSupplyZones +
                ", nbFoodSupplies=" + nbFoodSupplies +
                ", safezoneDropRate=" + safezoneDropRate +
                ", maximumFoodTransfer=" + maximumFoodTransfer +
                '}';
    }
}
