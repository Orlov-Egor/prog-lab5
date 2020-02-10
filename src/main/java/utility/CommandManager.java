package utility;

public class CommandManager {
    // private final int commandHistoryAmount = 8;

    private CollectionManager collectionManager;
    // private String[] userCommands = new String[commandHistoryAmount];

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
    }

    public void help() {
        System.out.println("Список команд:");
        System.out.printf("%-37s%-1s%n"," info","вывести информацию о коллекции");
        System.out.printf("%-37s%-1s%n"," show","вывести все элементы коллекции");
        System.out.printf("%-37s%-1s%n"," add {element}","добавить новый элемент в коллекцию");
        System.out.printf("%-37s%-1s%n"," update <ID> {element}","обновить значение элемента коллекции по ID");
        System.out.printf("%-37s%-1s%n"," remove_by_id <ID>","удалить элемент из коллекции по ID");
        System.out.printf("%-37s%-1s%n"," clear","очистить коллекцию");
        System.out.printf("%-37s%-1s%n"," save","сохранить коллекцию в файл");
        System.out.printf("%-37s%-1s%n"," execute_script <file_name>","исполнить скрипт из указанного файла");
        System.out.printf("%-37s%-1s%n"," exit","завершить программу (без сохранения в файл)");
        System.out.printf("%-37s%-1s%n"," add_if_min {element}","добавить новый элемент, если его значение меньше, чем у наименьшего");
        System.out.printf("%-37s%-1s%n"," remove_greater {element}","удалить из коллекции все элементы, превышающие заданный");
        System.out.printf("%-37s%-1s%n"," history","вывести последние 8 команд");
        System.out.printf("%-37s%-1s%n"," sum_of_health","вывести сумму значений поля health для всех элементов коллекции");
        System.out.printf("%-37s%-1s%n"," max_by_melee_weapon","вывести элемент, значение поля meleeWeapon которого максимально");
        System.out.printf("%-37s%-1s%n"," filter_by_weapon_type <weaponType>","вывести элементы, значение поля weaponType которых равно заданному");
    }

    public void info() {
        System.out.println("Сведения о коллекции:");
        System.out.println(" Тип: " + collectionManager.getCollectionType());
        System.out.println(" Количество элементов: " + collectionManager.getCollectionSize());
        System.out.println(" Дата последнего сохранения: " + collectionManager.getLastSaveTime());
        System.out.println(" Дата последней инициализации: " + collectionManager.getLastInitTime());
    }
}
