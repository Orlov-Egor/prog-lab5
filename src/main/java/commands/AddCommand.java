package commands;

import java.time.LocalDateTime;

import data.SpaceMarine;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.MarineAsker;

public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MarineAsker marineAsker;

    public AddCommand(CollectionManager collectionManager, MarineAsker marineAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.marineAsker = marineAsker;
    }

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            
            collectionManager.addToCollection(new SpaceMarine(
                collectionManager.generateNextId(),
                marineAsker.askName(),
                marineAsker.askCoordinates(),
                LocalDateTime.now(),
                marineAsker.askHealth(),
                marineAsker.askCategory(),
                marineAsker.askWeaponType(),
                marineAsker.askMeleeWeapon(),
                marineAsker.askChapter()
            ));

            System.out.println("Солдат успешно добавлен!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}