package utility;

import java.util.TreeSet;
import java.time.LocalDateTime;

import data.*;

public class CollectionManager {
    private TreeSet<SpaceMarine> marinesCollection =  new TreeSet<>(); 
    private LocalDateTime lastInitTime = null;
    private LocalDateTime lastSaveTime = null;

    public CollectionManager() {
        load();
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

    public SpaceMarine getById(Long id) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getId().equals(id)) return marine;
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

    public void save() {
        // TODO: Сохранение коллекции в файл
        lastSaveTime = LocalDateTime.now();
    }

    private void load() {
        // TODO: Загрузка коллекции из файла
        marinesCollection.add(new SpaceMarine(generateNextId(), "Test1", new Coordinates(2.0, 3.0F), LocalDateTime.now(), 100.0, AstartesCategory.DREADNOUGHT,
                       Weapon.GRAV_GUN, MeleeWeapon.POWER_BLADE, new Chapter("TestChapter1", 243L)));
        
        marinesCollection.add(new SpaceMarine(generateNextId(), "Test2", new Coordinates(36.0, 41.0F), LocalDateTime.now(), 56.0, AstartesCategory.ASSAULT,
                       Weapon.BOLT_PISTOL, MeleeWeapon.POWER_FIST, new Chapter("TestChapter2", 398L)));
        
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
