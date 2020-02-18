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

    public Long generateNextId() {
        if (marinesCollection.isEmpty()) return 1L;
        return marinesCollection.last().getId() + 1;
    }

    public void saveCollection() throws IOException {
            fileManager.writeCollection(marinesCollection);
            lastSaveTime = LocalDateTime.now();
    }

    private void loadCollection() {
        Type collectionType = new TypeToken<TreeSet<SpaceMarine>>(){}.getType();

            try {
                marinesCollection = gson.fromJson(fileManager.readCollection().toString(), collectionType);
                System.out.println(marinesCollection);

            } catch (JsonSyntaxException ex) {
                System.out.println("Ошибка синтаксиса Json. Коллекция не может быть загружена.");
                System.exit(1);
            }
        // marinesCollection.add(new SpaceMarine(generateNextId(), "Test1", new Coordinates(2.0, 3.0F), LocalDateTime.now(), 100.0, AstartesCategory.DREADNOUGHT,
        //                Weapon.GRAV_GUN, MeleeWeapon.POWER_BLADE, new Chapter("TestChapter1", 243L)));
        // marinesCollection.add(new SpaceMarine(generateNextId(), "Test2", new Coordinates(36.0, 41.0F), LocalDateTime.now(), 56.0, AstartesCategory.ASSAULT,
        //                Weapon.BOLT_PISTOL, MeleeWeapon.POWER_FIST, new Chapter("TestChapter2", 398L)));
        //System.out.println(fileManager.readCollection());
        //marinesCollection.add(fileManager.readCollection());
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (collectionSize() == 0) return "Коллекция пуста!";

        String infoAll = "";

        for (SpaceMarine marine : marinesCollection) {
            infoAll += marine;
            if (marine != marinesCollection.last()) infoAll += "\n\n";
        }

        return infoAll;
    }
}
