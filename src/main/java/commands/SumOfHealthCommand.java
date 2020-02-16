package commands;

import utility.CollectionManager;

public class SumOfHealthCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SumOfHealthCommand(CollectionManager collectionManager) {
        super("sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (collectionManager.collectionSize() == 0) System.out.println("Коллекция пуста!");
        else System.out.println("Сумма здоровья всех солдат: " + collectionManager.getSumOfHealth());
    }
}
