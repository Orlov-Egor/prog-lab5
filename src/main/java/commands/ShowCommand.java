package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            System.out.println(collectionManager);
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
