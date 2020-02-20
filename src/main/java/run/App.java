package run;

import java.util.Scanner;

import commands.AddCommand;
import commands.AddIfMinCommand;
import commands.ClearCommand;
import commands.ExitCommand;
import commands.FilterByWeaponTypeCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.InfoCommand;
import commands.MaxByMeleeWeaponCommand;
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

// TODO: RemoveGreater - повторы (?)
// TODO: Заменить возвращаемые объекты на клоны (?)
// TODO: Реализовать скрипты

public class App {
    public static final String envVariable = "LABA";
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            MarineAsker marineAsker = new MarineAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, marineAsker),
                new UpdateCommand(collectionManager, marineAsker),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExitCommand(),
                new AddIfMinCommand(collectionManager, marineAsker),
                new RemoveGreaterCommand(collectionManager, marineAsker),
                new HistoryCommand(),
                new SumOfHealthCommand(collectionManager),
                new MaxByMeleeWeaponCommand(collectionManager),
                new FilterByWeaponTypeCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner);

            console.interactiveMode();
        }
    }
}
