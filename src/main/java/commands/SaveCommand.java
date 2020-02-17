package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
        collectionManager.saveCollection();
        System.out.println("Коллекция сохранена!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
