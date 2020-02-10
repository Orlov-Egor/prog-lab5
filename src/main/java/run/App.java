package run;

import utility.CollectionManager;
import utility.CommandManager;
import utility.InteractiveController;

// TODO: Ограничение полей
// TODO: Добавить конструкторов utility-классам

public class App {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        InteractiveController interactiveController = new InteractiveController(commandManager);

        interactiveController.interactiveMode();
    }
}
