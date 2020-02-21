package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.Weapon;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import exceptions.WrongAmountOfElementsException;

/**
 * Asks a user a marine's value.
 */
public class MarineAsker {
    private final int MAX_Y = 262;
    private final double MIN_HEALTH = 0;
    final int MAX_MARINES = 1000;

    private Scanner userScanner;
    private boolean fileMode;
    
    public MarineAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets marine asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets marine asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks a user a marine's name.
     * @return Marine's name.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                System.out.print("Введите имя: ");
                name = userScanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                System.out.println(" Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user a marine's coordinates.
     * @return Marine's coordinates.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        String[] strCoordArray;
        double x;
        Float y;
        while (true) {
            try {
                System.out.println(" Формат ввода местоположения - 'X Y', где Y < " + (MAX_Y+1));
                System.out.print("Введите местоположение: ");
                strCoordArray = userScanner.nextLine().trim().split(" ");
                if (fileMode) System.out.println(String.join(" ", strCoordArray));
                if (strCoordArray.length != 2) throw new WrongAmountOfElementsException();
                x = Double.parseDouble(strCoordArray[0]);
                y = Float.parseFloat(strCoordArray[1]);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Координаты не распознаны!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Координата Y не может превышать " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (WrongAmountOfElementsException exception) {
                System.out.println(" Неверное количество координат!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println(" Координаты должны быть представлены числами!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return new Coordinates(x, y);
    }

    /**
     * Asks a user a marine's health.
     * @return Marine's health.
     */
    public double askHealth() throws IncorrectInputInScriptException {
        String strHealth;
        double health;
        while (true) {
            try {
                System.out.print("Введите здоровье: ");
                strHealth = userScanner.nextLine().trim();
                if (fileMode) System.out.println(strHealth);
                health = Double.parseDouble(strHealth);
                if (health < MIN_HEALTH) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Здоровье не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Здоровье должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println(" Здоровье должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return health;
    }

    /**
     * Asks a user a marine's category.
     * @return Marine's category.
     */
    public AstartesCategory askCategory() throws IncorrectInputInScriptException {
        String strCategory;
        AstartesCategory category;
        while (true) {
            try {
                System.out.println(" Список категорий - " + AstartesCategory.nameList());
                System.out.print("Введите категорию: ");
                strCategory = userScanner.nextLine().trim().toUpperCase();
                if (fileMode) System.out.println(strCategory);
                category = AstartesCategory.valueOf(strCategory);
                if (fileMode) System.out.println(category);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Категория не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                System.out.println(" Категории нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return category;
    }

    /**
     * Asks a user a marine's weapon type.
     * @return Marine's weapon type.
     */
    public Weapon askWeaponType() throws IncorrectInputInScriptException {
        String strWeaponType;
        Weapon weaponType;
        while (true) {
            try {
                System.out.println(" Список оружия дальнего боя - " + Weapon.nameList());
                System.out.print("Введите оружие дальнего боя: ");
                strWeaponType = userScanner.nextLine().trim().toUpperCase();
                if (fileMode) System.out.println(strWeaponType);
                weaponType = Weapon.valueOf(strWeaponType);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Оружие не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                System.out.println(" Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weaponType;
    }

    /**
     * Asks a user a marine's melee weapon.
     * @return Marine's melee weapon.
     */
    public MeleeWeapon askMeleeWeapon() throws IncorrectInputInScriptException {
        String strMeleeWeapon;
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                System.out.println(" Список оружия ближнего боя - " + MeleeWeapon.nameList());
                System.out.print("Введите оружие ближнего боя: ");
                strMeleeWeapon = userScanner.nextLine().trim().toUpperCase();
                if (fileMode) System.out.println(strMeleeWeapon);
                meleeWeapon = MeleeWeapon.valueOf(strMeleeWeapon);
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Оружие не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                System.out.println(" Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return meleeWeapon;
    }

    /**
     * Asks a user a marine's chapter.
     * @return Marine's chapter.
     */
    public Chapter askChapter() throws IncorrectInputInScriptException {
        String[] strChapterArray;
        String name;
        long marinesCount;
        while (true) {
            try {
                System.out.println(" Формат ввода ордена - 'NAME MARINES', где MARINES < " + (MAX_MARINES+1));
                System.out.print("Введите орден: ");
                strChapterArray = userScanner.nextLine().trim().split(" ");
                if (fileMode) System.out.println(String.join(" ", strChapterArray));
                if (strChapterArray.length != 2) throw new WrongAmountOfElementsException();
                name = strChapterArray[0];
                marinesCount = Long.parseLong(strChapterArray[1]);
                if (marinesCount > MAX_MARINES || marinesCount < 1) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Орден не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Количество солдат в ордене должно быть положительным и не превышать " + MAX_MARINES + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (WrongAmountOfElementsException exception) {
                System.out.println(" Неверное количество характеристик ордена!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                System.out.println(" Количество солдат в ордене должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                System.out.println(" Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return new Chapter(name, marinesCount);
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-): ";
        String answer;
        while (true) {
            try {
                System.out.print(finalQuestion);
                answer = userScanner.nextLine().trim();
                if (fileMode) System.out.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println(" Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                System.out.println(" Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
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
