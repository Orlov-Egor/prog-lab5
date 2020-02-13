package run;

import utility.CollectionManager;
import utility.CommandManager;
import utility.InteractiveController;

// TODO: Добавить конструкторов utility-классам
// TODO: Попробовать в фабрику

public class App {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        InteractiveController interactiveController = new InteractiveController(commandManager);

        interactiveController.interactiveMode();
    }
}
