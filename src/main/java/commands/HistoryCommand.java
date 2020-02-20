package commands;

/**
 * Command 'history'. It's here just for logical structure.
 */
public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
    }

    /**
     * Executes the command. (Easter Egg)
     */
    @Override
    public void execute(String argument) {
        System.out.println("Вы нашли пасхалку! ИТМО - это чудо!");
    }
}
