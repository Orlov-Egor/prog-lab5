package utility;

import java.util.TreeSet;

import data.*;

public class CollectionManager {
    private TreeSet<SpaceMarine> collection = new TreeSet<SpaceMarine>();

    public CollectionManager() {
        load();
    }

    private void load() {
        // TODO: Загрузка коллекции из файла
        // Временно загружает в коллекцию 2 тестовых объекта
        collection.add(new SpaceMarine("Test1", new Coordinates(2.0, 3.0F), 100.0, AstartesCategory.DREADNOUGHT,
                       Weapon.GRAV_GUN, MeleeWeapon.POWER_BLADE, new Chapter("TestChapter1", 243L)));
        
        collection.add(new SpaceMarine("Test2", new Coordinates(36.0, 41.0F), 56.0, AstartesCategory.ASSAULT,
                       Weapon.BOLT_PISTOL, MeleeWeapon.POWER_FIST, new Chapter("TestChapter2", 398L)));
    }

    
}
