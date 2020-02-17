package run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;

import commands.AddCommand;
import commands.ClearCommand;
import commands.InfoCommand;
import commands.RemoveByIdCommand;
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

public class App {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in);
             FileWriter collectionFileWriter = new FileWriter("marinesCollection.json")) {
            
            Gson gson = new Gson();
            MarineAsker marineAsker = new MarineAsker(userScanner);
            FileManager fileManager = new FileManager(collectionFileWriter, gson);
            CollectionManager collectionManager = new CollectionManager(fileManager);

            CommandManager commandManager = new CommandManager(
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, marineAsker),
                new UpdateCommand(collectionManager, marineAsker),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new SumOfHealthCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner);

            console.interactiveMode();
        } catch (IOException exception) {
            System.out.println(" Не удается открыть/создать файл для сохранения коллекции!");
            System.exit(0);
        }
    }
}
