package commands;

import utility.CollectionManager;

public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена!");
    }
}
