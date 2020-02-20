package commands;

/**
 * Command 'help'. Prints the hint.
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute(String argument) {
        System.out.println("Вы нашли пасхалку! Программирование - это здорово!");
    }
}
