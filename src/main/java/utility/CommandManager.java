package utility;

import java.time.LocalDateTime;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private CollectionManager collectionManager;
    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
    }

    public void addToHistory(String command) {
        for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
            commandHistory[i] = commandHistory[i-1];
        }
        commandHistory[0] = command;
    }

    public void help() {
        System.out.println("Список команд:");
        System.out.printf("%-37s%-1s%n", " info", "вывести информацию о коллекции");
        System.out.printf("%-37s%-1s%n", " show", "вывести все элементы коллекции");
        System.out.printf("%-37s%-1s%n", " add {element}", "добавить новый элемент в коллекцию");
        System.out.printf("%-37s%-1s%n", " update <ID> {element}", "обновить значение элемента коллекции по ID");
        System.out.printf("%-37s%-1s%n", " remove_by_id <ID>", "удалить элемент из коллекции по ID");
        System.out.printf("%-37s%-1s%n", " clear", "очистить коллекцию");
        System.out.printf("%-37s%-1s%n", " save", "сохранить коллекцию в файл");
        System.out.printf("%-37s%-1s%n", " execute_script <file_name>", "исполнить скрипт из указанного файла");
        System.out.printf("%-37s%-1s%n", " exit", "завершить программу (без сохранения в файл)");
        System.out.printf("%-37s%-1s%n", " add_if_min {element}", "добавить новый элемент, если его значение меньше, чем у наименьшего");
        System.out.printf("%-37s%-1s%n", " remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        System.out.printf("%-37s%-1s%n", " history", "вывести последние 8 команд");
        System.out.printf("%-37s%-1s%n", " sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        System.out.printf("%-37s%-1s%n", " max_by_melee_weapon", "вывести элемент, значение поля meleeWeapon которого максимально");
        System.out.printf("%-37s%-1s%n", " filter_by_weapon_type <weaponType>", "вывести элементы, значение поля weaponType которых равно заданному");
    }

    public void info() {
        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
        
        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        System.out.println("Сведения о коллекции:");
        System.out.println(" Тип: " + collectionManager.collectionType());
        System.out.println(" Количество элементов: " + collectionManager.collectionSize());
        System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
        System.out.println(" Дата последней инициализации: " + lastInitTimeString);
    }

    public void show() {
        System.out.println(collectionManager.infoAll());
    }

    public void history() {
        System.out.println("Последние использованные команды:");
        for (int i=0; i<COMMAND_HISTORY_SIZE; i++) {
            if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
        }
    }

    public void clear() {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена");
    }

    public void sum_of_healf() {
        System.out.println("Суммарное здоровье всех солдат: " + collectionManager.collectionSumOfHealf());
    }
}
