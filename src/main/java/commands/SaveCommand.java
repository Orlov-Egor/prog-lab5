package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.saveCollection();
            System.out.println("Коллекция успешна сохранена в файл!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}