package utility;

import java.util.Scanner;

// TODO: Добавление в историю
// TODO: Аргументы функций + ошибки

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
                case "clear":
                    commandManager.clear(userCommand[1]);
                    break;
                case "sum_of_health":
                    commandManager.sumOfHealth(userCommand[1]);
                    break;
                case "exit":
                    if (!userCommand[1].isEmpty()) System.out.println(" Использование: 'exit'");
                    break;
                default:
                    commandManager.noSuchCommand(userCommand[0]);
            }
        }
    }
}
