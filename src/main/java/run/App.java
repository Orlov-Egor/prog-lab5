package run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import commands.AddCommand;
import commands.AddIfMinCommand;
import commands.ClearCommand;
import commands.InfoCommand;
import commands.RemoveByIdCommand;
import commands.RemoveGreaterCommand;
import commands.SaveCommand;
import commands.ShowCommand;
import commands.SumOfHealthCommand;
import commands.UpdateCommand;
import utility.CollectionManager;
import utility.CommandManager;
import utility.Console;
import utility.FileManager;
import utility.MarineAsker;

// TODO: Добавить конструкторов utility-классам
// TODO: Имя файла через переменную окружения
// TODO: RemoveGreater - повторы
// TODO: Заменить возвращаемые объекты на клоны

public class App {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            
            MarineAsker marineAsker = new MarineAsker(userScanner);
            FileManager fileManager = new FileManager();
            CollectionManager collectionManager = new CollectionManager(fileManager);

            CommandManager commandManager = new CommandManager(
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, marineAsker),
                new UpdateCommand(collectionManager, marineAsker),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new AddIfMinCommand(collectionManager, marineAsker),
                new RemoveGreaterCommand(collectionManager, marineAsker),
                new SumOfHealthCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner);

            console.interactiveMode();
        }
    }
}
