package utility;

import java.util.Scanner;

public class InteractiveController {
    private CommandManager commandManager;

    public InteractiveController(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void interactiveMode() {
        try (Scanner userScanner = new Scanner(System.in)) {
            String userCommand = "";

            while (!userCommand.equals("exit")) {
                // TODO: Обработка ошибок
                // TODO: Добавление в историю единожды
                // TODO: Пулл с командами и информацией о них
                // TODO: РЕАЛИЗОВАТЬ ПАТТЕРН COMMAND С ЛЕКЦИИ
                System.out.print("\n>>> ");
                userCommand = userScanner.nextLine().trim();

                switch (userCommand) {
                    case "help":
                        commandManager.help();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "info":
                        commandManager.info();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "show":
                        commandManager.show();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "add":
                        commandManager.add(userScanner);
                        commandManager.addToHistory(userCommand);
                        break;
                    case "history":
                        commandManager.history();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "clear":
                        commandManager.clear();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "sum_of_health":
                        commandManager.sum_of_health();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "exit":
                        break;
                    default:
                        commandManager.noSuchCommand(userCommand);
                }
            }
        }
    }
}
