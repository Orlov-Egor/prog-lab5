package utility;

import java.util.TreeSet;
import java.time.LocalDateTime;

import data.*;

public class CollectionManager {
    private TreeSet<SpaceMarine> collection = new TreeSet<SpaceMarine>();
    private LocalDateTime lastInitTime = null;
    private LocalDateTime lastSaveTime = null;


    public CollectionManager() {
        load();
    }

    public int getSize() {
        return collection.size();
    }

    public String getCollectionType() {
        return collection.getClass().getName();
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public String getLastInitTime() {
        if (lastInitTime == null) return "в данной сессии инициализации еще не происходило";
        return lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
    }

    public String getLastSaveTime() {
        if (lastSaveTime == null) return "в данной сессии сохранения еще не происходило";
        return lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();
    }

    public void save() {
        // TODO: Сохранение коллекции в файл
        lastSaveTime = LocalDateTime.now();
    }

    private void load() {
        // TODO: Загрузка коллекции из файла
        // Временно загружает в коллекцию 2 тестовых объекта
        collection.add(new SpaceMarine("Test1", new Coordinates(2.0, 3.0F), 100.0, AstartesCategory.DREADNOUGHT,
                       Weapon.GRAV_GUN, MeleeWeapon.POWER_BLADE, new Chapter("TestChapter1", 243L)));
        
        collection.add(new SpaceMarine("Test2", new Coordinates(36.0, 41.0F), 56.0, AstartesCategory.ASSAULT,
                       Weapon.BOLT_PISTOL, MeleeWeapon.POWER_FIST, new Chapter("TestChapter2", 398L)));
        
        lastInitTime = LocalDateTime.now();
    }
}
