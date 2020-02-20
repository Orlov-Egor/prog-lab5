package commands;

/**
 * Command 'history'. Prints the history of last used commands.
 */
public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        System.out.println("Вы нашли пасхалку! ИТМО - это чудо!");
    }
}
