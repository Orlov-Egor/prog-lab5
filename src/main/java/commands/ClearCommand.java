package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
