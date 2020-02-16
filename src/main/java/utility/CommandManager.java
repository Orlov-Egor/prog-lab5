package utility;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandManager {
    private List<Command> commands = new ArrayList<>();

    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command clearCommand;
    private Command sumOfHealthCommand;

    public CommandManager(Command infoCommand, Command showCommand, Command addCommand, Command clearCommand,
                          Command sumOfHealthCommand) {
       this.infoCommand = infoCommand;
       this.showCommand = showCommand;
       this.addCommand = addCommand;
       this.clearCommand = clearCommand;
       this.sumOfHealthCommand = sumOfHealthCommand;

       commands.add(infoCommand);
       commands.add(showCommand);
       commands.add(addCommand);
       commands.add(clearCommand);
       commands.add(sumOfHealthCommand);
    }

    public void noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
    }
    
    public void help() {
        for (Command command : commands) {
            System.out.printf("%-37s%-1s%n", " " + command.getName(), command.getDescription());
        }
    }

    public void info() {
        infoCommand.execute();
    }

    public void show() {
        showCommand.execute();
    }

    public void add() {
        addCommand.execute();
    }

    public void clear() {
        clearCommand.execute();
    }

    public void sumOfHealth() {
        sumOfHealthCommand.execute();
    }
}
