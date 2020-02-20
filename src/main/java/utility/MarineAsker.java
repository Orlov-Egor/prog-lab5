package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.Weapon;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import exceptions.WrongAmountOfElementsException;

public class MarineAsker {
    private final int MAX_Y = 262;
    private final double MIN_HEALTH = 0;
    final int MAX_MARINES = 1000;

    private Scanner userScanner;
    
    public MarineAsker(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public String askName() {
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

    public Coordinates askCoordinates() {
        String[] strCoordArray;
        double x;
        Float y;
        while (true) {
            try {
                System.out.println(" Формат ввода местоположения - 'X Y', где Y < " + (MAX_Y+1));
                System.out.print("Введите местоположение: ");
                strCoordArray = userScanner.nextLine().trim().split(" ");
                if (strCoordArray.length != 2) throw new WrongAmountOfElementsException();
                x = Double.parseDouble(strCoordArray[0]);
                y = Float.parseFloat(strCoordArray[1]);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
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

    public double askHealth() {
        double health;
        while (true) {
            try {
                System.out.print("Введите здоровье: ");
                health = Double.parseDouble(userScanner.nextLine().trim());
                if (health < MIN_HEALTH) throw new NotInDeclaredLimitsException();
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

    public AstartesCategory askCategory() {
        AstartesCategory category;
        while (true) {
            try {
                System.out.println(" Список категорий - " + AstartesCategory.nameList());
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

    public Weapon askWeaponType() {
        Weapon weaponType;
        while (true) {
            try {
                System.out.println(" Список оружия дальнего боя - " + Weapon.nameList());
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

    public MeleeWeapon askMeleeWeapon() {
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                System.out.println(" Список оружия ближнего боя - " + MeleeWeapon.nameList());
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

    public Chapter askChapter() {
        String[] strChapterArray;
        String name;
        long marinesCount;
        while (true) {
            try {
                System.out.println(" Формат ввода ордена - 'NAME MARINES', где MARINES < " + (MAX_MARINES+1));
                System.out.print("Введите орден: ");
                strChapterArray = userScanner.nextLine().trim().split(" ");
                if (strChapterArray.length != 2) throw new WrongAmountOfElementsException();
                name = strChapterArray[0];
                marinesCount = Long.parseLong(strChapterArray[1]);
                if (marinesCount > MAX_MARINES || marinesCount < 1) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Орден не распознан!");
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Количество солдат в ордене должно быть положительным и не превышать " + MAX_MARINES + "!");
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

    public boolean askQuestion(String question) {
        String finalQuestion = question + " (+/-): ";
        String answer;
        while (true) {
            try {
                System.out.print(finalQuestion);
                answer = userScanner.nextLine().trim();
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Ответ не распознан!");
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Ответ должен быть представлен знаками '+' или '-'!");
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {
        return "MarineAsker (вспомогательный класс для запросов пользователю)";
    }
}
