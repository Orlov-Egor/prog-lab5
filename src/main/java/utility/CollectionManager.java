package utility;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.TreeSet;

import data.SpaceMarine;
import data.Weapon;

public class CollectionManager {
    private NavigableSet<SpaceMarine> marinesCollection =  new TreeSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
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

    public SpaceMarine getFirst() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.first();
    }

    public SpaceMarine getLast() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.last();
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
        if (marinesCollection.isEmpty()) return "Коллекция пуста!";

        SpaceMarine maxMarine = getFirst();
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getMeleeWeapon().compareTo(maxMarine.getMeleeWeapon()) > 0) {
                maxMarine = marine;
            }
        }
        return maxMarine.toString();
    }

    public String weaponFilteredInfo(Weapon weaponToFilter) {
        if (marinesCollection.isEmpty()) return "Коллекция пуста!";

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

    public void addToCollection(SpaceMarine marine) {
        marinesCollection.add(marine);
    }

    public void removeFromCollection(SpaceMarine marine) {
        marinesCollection.remove(marine);
    }

    public void removeGreater(SpaceMarine marineToCompare) {
        marinesCollection.removeIf(marine -> marine.compareTo(marineToCompare) > 0);
    }

    public void clearCollection() {
        marinesCollection.clear();
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
        if (marinesCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (SpaceMarine marine : marinesCollection) {
            info += marine;
            if (marine != marinesCollection.last()) info += "\n\n";
        }
        return info;
    }
}
