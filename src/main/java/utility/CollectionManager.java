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

    public void help() {
        System.out.println("Список команд:");
        System.out.printf("%-35s%-1s%n"," info","вывести информацию о коллекции");
        System.out.printf("%-35s%-1s%n"," show","вывести все элементы коллекции");
        System.out.printf("%-35s%-1s%n"," add {element}","добавить новый элемент в коллекцию");
        System.out.printf("%-35s%-1s%n"," update <ID> {element}","обновить значение элемента коллекции по ID");
        System.out.printf("%-35s%-1s%n"," remove_by_id <ID>","удалить элемент из коллекции по ID");
        System.out.printf("%-35s%-1s%n"," clear","очистить коллекцию");
        System.out.printf("%-35s%-1s%n"," save","сохранить коллекцию в файл");
        System.out.printf("%-35s%-1s%n"," execute_script <file_name>","исполнить скрипт из указанного файла");
        System.out.printf("%-35s%-1s%n"," exit","завершить программу (без сохранения в файл)");
        System.out.printf("%-35s%-1s%n"," add_if_min {element}","добавить новый элемент, если его значение меньше, чем у наименьшего");
        System.out.printf("%-35s%-1s%n"," remove_greater {element}","удалить из коллекции все элементы, превышающие заданный");
        System.out.printf("%-35s%-1s%n"," history","вывести последние 8 команд");
        System.out.printf("%-35s%-1s%n"," sum_of_health","вывести сумму значений поля health для всех элементов коллекции");
        System.out.printf("%-35s%-1s%n"," max_by_melee_weapon","вывести элемент, значение поля meleeWeapon которого максимально");
        System.out.printf("%-35s%-1s%n"," filter_by_weapon_type <weaponType>","вывести элементы, значение поля weaponType которых равно заданному");
    }
}
