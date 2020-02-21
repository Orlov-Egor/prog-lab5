package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates command input.
 */
public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private MarineAsker marineAsker;

    public Console(CommandManager commandManager, Scanner userScanner, MarineAsker marineAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.marineAsker = marineAsker;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        try {
            do {
                System.out.print("\n>>> ");
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
            } while (launchCommand(userCommand));
        } catch (NoSuchElementException exception) {
            System.out.println("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            System.out.println("Непредвиденная ошибка!");
            System.exit(0);
        }
    }

    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     */
    public boolean scriptMode(String argument) {
        String[] userCommand = {"", ""};
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = marineAsker.getUserScanner();
            marineAsker.setUserScanner(scriptScanner);
            marineAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                System.out.println("\n>>> " + String.join(" ", userCommand));
            } while (launchCommand(userCommand) && scriptScanner.hasNextLine());
            marineAsker.setUserScanner(tmpScanner);
            marineAsker.setUserMode();
            if (userCommand[0].equals("exit") || userCommand[1].isEmpty()) return true;
        } catch (FileNotFoundException exception) {
            System.out.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            System.out.println("Файл со скриптом пуст!");
        } catch (IllegalStateException exception) {
            System.out.println("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    /**
     * Launchs the command.
     * @param userCommand Command to launch.
     */
    private boolean launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "help":
                commandManager.help(userCommand[1]);
                break;
            case "info":
                commandManager.info(userCommand[1]);
                break;
            case "show":
                commandManager.show(userCommand[1]);
                break;
            case "add":
                commandManager.add(userCommand[1]);
                break;
            case "update":
                commandManager.update(userCommand[1]);
                break;
            case "remove_by_id":
                commandManager.removeById(userCommand[1]);
                break;
            case "clear":
                commandManager.clear(userCommand[1]);
                break;
            case "save":
                commandManager.save(userCommand[1]);
                break;
            case "execute_script":
                commandManager.executeScript(userCommand[1]);
                if (!userCommand[1].isEmpty() && scriptMode(userCommand[1])) return false;
                break;
            case "add_if_min":
                commandManager.addIfMin(userCommand[1]);
                break;
            case "remove_greater":
                commandManager.removeGreater(userCommand[1]);
                break;
            case "history":
                commandManager.history(userCommand[1]);
                break;
            case "sum_of_health":
                commandManager.sumOfHealth(userCommand[1]);
                break;
            case "max_by_melee_weapon":
                commandManager.maxByMeleeWeapon(userCommand[1]);
                break;
            case "filter_by_weapon_type":
                commandManager.filterByWeaponType(userCommand[1]);
                break;
            case "exit":
                commandManager.exit(userCommand[1]);
                if (userCommand[1].isEmpty()) return false;
                break;
            default:
                commandManager.noSuchCommand(userCommand[0]);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
}
