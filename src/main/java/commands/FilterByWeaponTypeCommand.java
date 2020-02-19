package commands;

import data.Weapon;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;

public class FilterByWeaponTypeCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterByWeaponTypeCommand(CollectionManager collectionManager) {
        super("filter_by_weapon_type <weaponType>", "вывести элементы, значение поля weaponType которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Weapon weapon = Weapon.valueOf(argument.toUpperCase());
            System.out.println(collectionManager.weaponFilteredInfo(weapon));
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        } catch (IllegalArgumentException exception) {
            System.out.println(" Оружия нет в списке!");
            System.out.println(" Список оружия дальнего боя - HEAVY_BOLTGUN, BOLT_PISTOL, GRAV_GUN");
        }
    }
}
