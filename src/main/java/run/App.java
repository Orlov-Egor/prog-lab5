package run;

import java.util.Scanner;

import utility.CollectionManager;
import utility.CommandManager;
import utility.Console;
import utility.MarineAsker;
import commands.*;

// TODO: Добавить конструкторов utility-классам

public class App {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            CollectionManager collectionManager = new CollectionManager();
            MarineAsker marineAsker = new MarineAsker(userScanner);
            CommandManager commandManager = new CommandManager(
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, marineAsker),
                new UpdateCommand(collectionManager, marineAsker),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SumOfHealthCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner);

            console.interactiveMode();
        }
    }
}
