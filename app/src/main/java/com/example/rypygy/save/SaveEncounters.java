package com.example.rypygy.save;

import com.example.rypygy.data.NpcData;
import com.example.rypygy.enums.Location;

public class SaveEncounters {
    private boolean forest;
    private boolean garages;
    private boolean toilets;
    private boolean computerLab;
    private boolean dormitory;
    private boolean courtyard;
    private boolean kaczyce;

    public SaveEncounters() {
        forest = NpcData.isVisited.get(Location.FOREST);
        garages = NpcData.isVisited.get(Location.GARAGES);
        toilets = NpcData.isVisited.get(Location.TOILETS);
        computerLab = NpcData.isVisited.get(Location.COMPUTER_LAB);
        dormitory = NpcData.isVisited.get(Location.DORMITORY);
        courtyard = NpcData.isVisited.get(Location.COURTYARD);
        kaczyce = NpcData.isVisited.get(Location.KACZYCE);
    }

    public boolean isForest() {
        return forest;
    }
    public boolean isGarages() {
        return garages;
    }
    public boolean isToilets() {
        return toilets;
    }
    public boolean isComputerLab() {
        return computerLab;
    }
    public boolean isDormitory() {
        return dormitory;
    }
    public boolean isCourtyard() {
        return courtyard;
    }
    public boolean isKaczyce() {
        return kaczyce;
    }

    @Override
    public String toString() {
        return "SaveEncounters{" +
                "forest=" + forest +
                ", garages=" + garages +
                ", toilets=" + toilets +
                ", computerLab=" + computerLab +
                ", dormitory=" + dormitory +
                ", courtyard=" + courtyard +
                ", kaczyce=" + kaczyce +
                '}';
    }
}
