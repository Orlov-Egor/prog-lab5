package commands;

import utility.CollectionManager;

public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        // collectionManager.addToCollection(new SpaceMarine(
        //     collectionManager.generateNextId(),
        //     marineAsker.askName(),
        //     marineAsker.askCoordinates(),
        //     LocalDateTime.now(),
        //     marineAsker.askHealth(),
        //     marineAsker.askCategory(),
        //     marineAsker.askWeaponType(),
        //     marineAsker.askMeleeWeapon(),
        //     marineAsker.askChapter()
        // ));
    }
}
