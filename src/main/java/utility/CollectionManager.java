package utility;

import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.TreeSet;

import data.SpaceMarine;
import data.Weapon;

/**
 * Operates the collection itself.
 */
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

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return marinesCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return marinesCollection.size();
    }

    /**
     * @return The first element of the collection or null in case of collection is empty.
     */
    public SpaceMarine getFirst() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.first();
    }

    /**
     * @return The last element of the collection or null in case of collection is empty.
     */
    public SpaceMarine getLast() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.last();
    }

    /**
     * @param id ID of the marine.
     * @return A marine by his ID or null in case of marine not found.
     */
    public SpaceMarine getById(Long id) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getId().equals(id)) return marine;
        }
        return null;
    }

    /**
     * @param marineToFind A marine who's value will be found.
     * @return A marine by his value or null in case of marine not found.
     */
    public SpaceMarine getByValue(SpaceMarine marineToFind) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.equals(marineToFind)) return marine;
        }
        return null;
    }

    /**
     * @return Sum of all marine's health.
     */
    public double getSumOfHealth() {
        double sumOfHealth = 0;
        for (SpaceMarine marine : marinesCollection) {
            sumOfHealth += marine.getHealth();
        }
        return sumOfHealth;
    }

    /**
     * @return Marine, who has max melee weapon or string "Коллекция пуста!".
     */
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

    /**
     * @param weaponToFilter Weapon to filter by.
     * @return Information about valid marines, string "Коллекция пуста!" or "В коллекции нет солдат с выбранным типом оружия!".
     */
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
        return (marker) ? info.trim() : "В коллекции нет солдат с выбранным типом оружия!";
    }

    /**
     * Adds a new marine to collection.
     * @param marine A marine to add.
     */
    public void addToCollection(SpaceMarine marine) {
        marinesCollection.add(marine);
    }

    /**
     * Removes a new marine to collection.
     * @param marine A marine to remove.
     */
    public void removeFromCollection(SpaceMarine marine) {
        marinesCollection.remove(marine);
    }

    /**
     * Remove marines greater than the selected one.
     * @param marineToCompare A marine to compare with.
     */
    public void removeGreater(SpaceMarine marineToCompare) {
        marinesCollection.removeIf(marine -> marine.compareTo(marineToCompare) > 0);
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        marinesCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public Long generateNextId() {
        if (marinesCollection.isEmpty()) return 1L;
        return marinesCollection.last().getId() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
            fileManager.writeCollection(marinesCollection);
            lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
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
