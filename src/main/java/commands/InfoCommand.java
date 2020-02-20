package commands;

import java.time.LocalDateTime;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                                        lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
            
            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                                        lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            System.out.println("Сведения о коллекции:");
            System.out.println(" Тип: " + collectionManager.collectionType());
            System.out.println(" Количество элементов: " + collectionManager.collectionSize());
            System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
