package utility;

import java.util.Scanner;

public class CommandManager {
    private final int commandHistoryAmount = 8;

    private CollectionManager collectionManager;
    private String[] userCommands = new String[commandHistoryAmount];

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void interactiveMode() {
        String userCommand = "";
        
        try (Scanner commandScanner = new Scanner(System.in)) {
            while (!userCommand.equals("exit")) {
                // TODO: Обработка ошибок
                System.out.print(">>> ");
                userCommand = commandScanner.nextLine().trim();
                System.out.println();

                switch (userCommand) {
                    case "exit":
                        break;
                    default:
                        System.out.println("Команда '" + userCommand + "' не найдена. Наберите 'help' для справки.");
                }
            }
        }
    }
}
