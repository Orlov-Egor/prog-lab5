package commands;

public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
    }

    @Override
    public void execute(String argument) {
        System.out.println("Вы нашли пасхалку! ИТМО - это чудо!");
    }
}
