package utility;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.SpaceMarine;
import data.Weapon;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import exceptions.WrongAmountOfElementsException;

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

    public String inputName(Scanner userScanner) {
        String name;

        while (true) {
            try {
                System.out.print("Введите имя: ");
                name = userScanner.nextLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Имя не распознано!");
            } catch (MustBeNotEmptyException exception) {
                System.out.println(" Имя не может быть пустым!");
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return name;
    }

    public Coordinates inputCoords(Scanner userScanner) {
        final String SEPARATOR = " ";
        final int MAX_Y = 262;
        String[] strCoordArray;
        double x;
        Float y;

        while (true) {
            try {
                System.out.println(" Формат ввода местоположения - 'X Y', где Y < " + (MAX_Y+1));
                System.out.print("Введите местоположение: ");
                strCoordArray = userScanner.nextLine().trim().split(SEPARATOR);
                if (strCoordArray.length != 2) throw new WrongAmountOfElementsException();
                x = Double.parseDouble(strCoordArray[0]);
                y = Float.parseFloat(strCoordArray[1]);
                if (y > 262) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Координаты не распознаны!");
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Координата Y не может превышать " + MAX_Y + "!");
            } catch (WrongAmountOfElementsException exception) {
                System.out.println(" Неверное количество координат!");
            } catch (NumberFormatException exception) {
                System.out.println(" Координаты должны быть представлены числами!");
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return new Coordinates(x, y);
    }

    public double inputHealth(Scanner userScanner) {
        double health;

        while (true) {
            try {
                System.out.print("Введите здоровье: ");
                health = Double.parseDouble(userScanner.nextLine().trim());
                if (health <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Здоровье не распознано!");
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Здоровье должно быть больше нуля!");
            } catch (NumberFormatException exception) {
                System.out.println(" Здоровье должно быть представлено числом!");
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return health;
    }

    public AstartesCategory inputCategory(Scanner userScanner) {
        AstartesCategory category;

        while (true) {
            try {
                System.out.println(" Список категорий - DREADNOUGHT, ASSAULT, TACTICAL, CHAPLAIN, APOTHECARY");
                System.out.print("Введите категорию: ");
                category = AstartesCategory.valueOf(userScanner.nextLine().trim().toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Категория не распознана!");
            } catch (IllegalArgumentException exception) {
                System.out.println(" Категории нет в списке!");
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return category;
    }

    public Weapon inputWeaponType(Scanner userScanner) {
        Weapon weaponType;

        while (true) {
            try {
                System.out.println(" Список оружия дальнего боя - HEAVY_BOLTGUN, BOLT_PISTOL, GRAV_GUN");
                System.out.print("Введите оружие дальнего боя: ");
                weaponType = Weapon.valueOf(userScanner.nextLine().trim().toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Оружие не распознано!");
            } catch (IllegalArgumentException exception) {
                System.out.println(" Оружия нет в списке!");
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return weaponType;
    }

    public MeleeWeapon inputMeleeWeapon(Scanner userScanner) {
        MeleeWeapon meleeWeapon;

        while (true) {
            try {
                System.out.println(" Список оружия ближнего боя - CHAIN_SWORD,  CHAIN_AXE, LIGHTING_CLAW, POWER_BLADE, POWER_FIST");
                System.out.print("Введите оружие ближнего боя: ");
                meleeWeapon = MeleeWeapon.valueOf(userScanner.nextLine().trim().toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Оружие не распознано!");
            } catch (IllegalArgumentException exception) {
                System.out.println(" Оружия нет в списке!");
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return meleeWeapon;
    }

    public Chapter inputChapter(Scanner userScanner) {
        final String SEPARATOR = " ";
        final int MAX_MARINES = 1000;
        String[] strChapterArray;
        String name;
        long marinesCount;

        while (true) {
            try {
                System.out.println(" Формат ввода ордена - 'NAME MARINES', где MARINES < " + (MAX_MARINES+1));
                System.out.print("Введите орден: ");
                strChapterArray = userScanner.nextLine().trim().split(SEPARATOR);
                if (strChapterArray.length != 2) throw new WrongAmountOfElementsException();
                name = strChapterArray[0];
                marinesCount = Long.parseLong(strChapterArray[1]);
                if (marinesCount > MAX_MARINES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Орден не распознан!");
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Количество солдат в ордене не может превышать " + MAX_MARINES + "!");
            } catch (WrongAmountOfElementsException exception) {
                System.out.println(" Неверное количество характеристик ордена!");
            } catch (NumberFormatException exception) {
                System.out.println(" Количество солдат в ордене должно быть представлено числом!");
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return new Chapter(name, marinesCount);
    }

    public void add(Scanner userScanner) {
        // TODO: Обработка максимальных числовых значений
        // TODO: Изменить передачу сканнера в подфункции
        // TODO: Общий SEPARATOR
        String name = inputName(userScanner);
        Coordinates coordinates = inputCoords(userScanner);
        double health = inputHealth(userScanner);
        AstartesCategory category = inputCategory(userScanner);
        Weapon weaponType = inputWeaponType(userScanner);
        MeleeWeapon meleeWeapon = inputMeleeWeapon(userScanner);
        Chapter chapter = inputChapter(userScanner);

        SpaceMarine marine = new SpaceMarine(name, coordinates, health, category,
                                             weaponType, meleeWeapon, chapter);

        collectionManager.addToCollection(marine);

        System.out.println("Солдат успешно добавлен в коллекцию!");
    }

    public void history() {
        System.out.println("Последние использованные команды:");
        for (int i=0; i<COMMAND_HISTORY_SIZE; i++) {
            if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
        }
    }
}
