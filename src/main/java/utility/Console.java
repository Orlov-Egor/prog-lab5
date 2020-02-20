package utility;

import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;

    public Console(CommandManager commandManager, Scanner userScanner) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
    }

    public void interactiveMode() {
        String[] userCommand = {"", ""};

        while (!userCommand[0].equals("exit") || !userCommand[1].isEmpty()) {
            System.out.print("\n>>> ");
            userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
            userCommand[1] = userCommand[1].trim();

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
                    break;
                default:
                    commandManager.noSuchCommand(userCommand[0]);
            }
            commandManager.addToHistory(userCommand[0]);
        }
    }

    @Override
    public String toString() {
        return "Console";
    }
}
