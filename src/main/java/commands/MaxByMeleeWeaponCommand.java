package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'max_by_melee_weapon'. Prints the element of the collection with maximum melee weapon.
 */
public class MaxByMeleeWeaponCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public MaxByMeleeWeaponCommand(CollectionManager collectionManager) {
        super("max_by_melee_weapon", "вывести элемент, значение поля meleeWeapon которого максимально");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println(collectionManager.maxByMeleeWeapon());
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
    }
}
