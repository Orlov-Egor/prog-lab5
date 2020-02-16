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
    private Command updateCommand;

    public CommandManager(Command infoCommand, Command showCommand, Command addCommand, Command clearCommand,
                          Command sumOfHealthCommand, Command updateCommand) {
       this.infoCommand = infoCommand;
       this.showCommand = showCommand;
       this.addCommand = addCommand;
       this.clearCommand = clearCommand;
       this.sumOfHealthCommand = sumOfHealthCommand;
       this.updateCommand = updateCommand;

       commands.add(infoCommand);
       commands.add(showCommand);
       commands.add(addCommand);
       commands.add(clearCommand);
       commands.add(sumOfHealthCommand);
       commands.add(updateCommand);
    }

    public void noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
    }
    
    public void help(String argument) {
        if (!argument.isEmpty()) {
            System.out.println(" Использование: 'help'");
            return;
        }
        for (Command command : commands) {
            System.out.printf("%-37s%-1s%n", " " + command.getName(), command.getDescription());
        }
    }

    public void info(String argument) {
        infoCommand.execute(argument);
    }

    public void show(String argument) {
        showCommand.execute(argument);
    }

    public void add(String argument) {
        addCommand.execute(argument);
    }

    public void update(String argument) {
        updateCommand.execute(argument);
    }

    public void clear(String argument) {
        clearCommand.execute(argument);
    }

    public void sumOfHealth(String argument) {
        sumOfHealthCommand.execute(argument);
    }
}
