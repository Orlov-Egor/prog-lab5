package run;

import utility.CollectionManager;
import utility.CommandManager;

// TODO: Ограничение полей
// TODO: Постараться отделить логгеры

public class App {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        commandManager.interactiveMode();
    }
}
