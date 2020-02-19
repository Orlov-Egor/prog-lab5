package utility;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TreeSet;

import com.google.gson.Gson;

import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.SpaceMarine;
import data.Weapon;

public class CollectionManager {
    private TreeSet<SpaceMarine> marinesCollection =  new TreeSet<>();
    private LocalDateTime lastInitTime = null;
    private LocalDateTime lastSaveTime = null;

    private Gson gson = new Gson();

    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        loadCollection();
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType() {
        return marinesCollection.getClass().getName();
    }

    public int collectionSize() {
        return marinesCollection.size();
    }

    public void addToCollection(SpaceMarine marine) {
        marinesCollection.add(marine);
    }

    public void removeFromCollection(SpaceMarine marine) {
        marinesCollection.remove(marine);
    }


    public void clearCollection() {
        marinesCollection.clear();
    }

    public void removeGreater(SpaceMarine marineToCompare) {
        marinesCollection.removeIf(marine -> marine.compareTo(marineToCompare) > 0);
    }

    public SpaceMarine getFirst() {
        return marinesCollection.first();
    }

    public SpaceMarine getById(Long id) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getId().equals(id)) return marine;
        }

        return null;
    }

    public SpaceMarine getByValue(SpaceMarine marineToFind) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.equals(marineToFind)) return marine;
        }

        return null;
    }

    public double getSumOfHealth() {
        double sumOfHealth = 0;
        for (SpaceMarine marine : marinesCollection) {
            sumOfHealth += marine.getHealth();
        }
        return sumOfHealth;
    }

    public String maxByMeleeWeapon() {
        if (collectionSize() == 0) return "Коллекция пуста!";

        SpaceMarine maxMarine = marinesCollection.first();

        for (SpaceMarine marine : marinesCollection) {
            if (marine.getMeleeWeapon().compareTo(maxMarine.getMeleeWeapon()) > 0) {
                maxMarine = marine;
            }
        }

        return maxMarine.toString();
    }

    public String weaponFilteredInfo(Weapon weaponToFilter) {
        if (collectionSize() == 0) return "Коллекция пуста!";

        String info = "";
        boolean marker = false;

        for (SpaceMarine marine : marinesCollection) {
            if (marine.getWeaponType().equals(weaponToFilter)) {
                info += marine + "\n\n";
                marker = true;
            }
        }

        return (marker) ? info.substring(0, info.length()-2) : "В коллекции нет солдат с выбранным типом оружия!";
    }

    public Long generateNextId() {
        if (marinesCollection.isEmpty()) return 1L;
        return marinesCollection.last().getId() + 1;
    }

    public void saveCollection() throws IOException {
            fileManager.writeCollection(marinesCollection);
            lastSaveTime = LocalDateTime.now();
    }

    private void loadCollection() {
        marinesCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (collectionSize() == 0) return "Коллекция пуста!";

        String info = "";

        for (SpaceMarine marine : marinesCollection) {
            info += marine;
            if (marine != marinesCollection.last()) info += "\n\n";
        }

        return info;
    }
}
