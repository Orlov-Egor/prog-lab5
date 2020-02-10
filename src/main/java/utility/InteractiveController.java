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
                        break;
                    case "info":
                        commandManager.info();
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
