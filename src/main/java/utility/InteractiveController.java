package utility;

import java.util.Scanner;

public class InteractiveController {
    private CommandManager commandManager;

    public InteractiveController(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void interactiveMode() {
        try (Scanner commandScanner = new Scanner(System.in)) {
            String userCommand = "";

            while (!userCommand.equals("exit")) {
                // TODO: Обработка ошибок
                System.out.print("\n>>> ");
                userCommand = commandScanner.nextLine().trim();

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
                    case "history":
                        commandManager.history();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "clear":
                        commandManager.clear();
                        commandManager.addToHistory(userCommand);
                        break;
                    case "sum_of_healf":
                        commandManager.sum_of_healf();
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
