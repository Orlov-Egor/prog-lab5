package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

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
            System.out.println(collectionManager.maxByMeleeWeapon());
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
