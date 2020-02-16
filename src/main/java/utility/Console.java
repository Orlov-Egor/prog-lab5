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

        while (!userCommand[0].equals("exit")) {
            System.out.print("\n>>> ");
            userCommand = userScanner.nextLine().trim().split(" ", 2);

            switch (userCommand[0]) {
                case "help":
                    commandManager.help();
                    break;
                case "info":
                    commandManager.info();
                    break;
                case "show":
                    commandManager.show();
                    break;
                case "add":
                    commandManager.add();
                    break;
                case "clear":
                    commandManager.clear();
                    break;
                case "sum_of_health":
                    commandManager.sumOfHealth();
                    break;
                case "exit":
                    break;
                default:
                    commandManager.noSuchCommand(userCommand[0]);
            }
        }
    }
}
