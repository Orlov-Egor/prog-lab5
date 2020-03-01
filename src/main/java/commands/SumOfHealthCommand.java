package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'sum_of_health'. Prints the sum of health of all marines.
 */
public class SumOfHealthCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SumOfHealthCommand(CollectionManager collectionManager) {
        super("sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Console.println("Сумма здоровья всех солдат: " + collectionManager.getSumOfHealth());
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
    }
}
