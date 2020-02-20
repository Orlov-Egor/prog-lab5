package commands;

/**
 * Command 'help'. It's here just for logical structure.
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Executes the command. (Easter Egg)
     */
    @Override
    public void execute(String argument) {
        System.out.println("Вы нашли пасхалку! Программирование - это здорово!");
    }
}
