package commands;

import java.time.LocalDateTime;

import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.SpaceMarine;
import data.Weapon;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.MarineNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.MarineAsker;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MarineAsker marineAsker;

    public UpdateCommand(CollectionManager collectionManager, MarineAsker marineAsker) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
        this.marineAsker = marineAsker;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            SpaceMarine oldMarine = collectionManager.getById(id);
            if (oldMarine == null) throw new MarineNotFoundException();

            String name = oldMarine.getName();
            Coordinates coordinates = oldMarine.getCoordinates();
            LocalDateTime creationDate = oldMarine.getCreationDate();
            double health = oldMarine.getHealth();
            AstartesCategory category = oldMarine.getCategory();
            Weapon weaponType = oldMarine.getWeaponType();
            MeleeWeapon meleeWeapon = oldMarine.getMeleeWeapon();
            Chapter chapter = oldMarine.getChapter();

            collectionManager.removeFromCollection(oldMarine);

            if (marineAsker.askQuestion("Хотите изменить имя солдата?")) name = marineAsker.askName();
            if (marineAsker.askQuestion("Хотите изменить координаты солдата?")) coordinates = marineAsker.askCoordinates();
            if (marineAsker.askQuestion("Хотите изменить здоровье солдата?")) health = marineAsker.askHealth();
            if (marineAsker.askQuestion("Хотите изменить категорию солдата?")) category = marineAsker.askCategory();
            if (marineAsker.askQuestion("Хотите изменить оружие дальнего боя солдата?")) weaponType = marineAsker.askWeaponType();
            if (marineAsker.askQuestion("Хотите изменить оружие ближнего боя солдата?")) meleeWeapon = marineAsker.askMeleeWeapon();
            if (marineAsker.askQuestion("Хотите изменить орден солдата?")) chapter = marineAsker.askChapter();

            collectionManager.addToCollection(new SpaceMarine(
                id,
                name,
                coordinates,
                creationDate,
                health,
                category,
                weaponType,
                meleeWeapon,
                chapter
            ));
            System.out.println("Солдат успешно изменен!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            System.out.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.out.println("ID должен быть представлен числом!");
        } catch (MarineNotFoundException exception) {
            System.out.println("Солдата с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {
            System.out.println(" Проверьте скрипт на корректность введенных данных!");
        }
    }
}
