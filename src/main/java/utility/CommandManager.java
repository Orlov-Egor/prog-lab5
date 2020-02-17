package utility;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();

    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command sumOfHealthCommand;
    private Command saveCommand;

    public CommandManager(Command infoCommand, Command showCommand, Command addCommand, Command updateCommand,
                          Command removeByIdCommand, Command clearCommand, Command sumOfHealthCommand, Command saveCommand) {
       this.infoCommand = infoCommand;
       this.showCommand = showCommand;
       this.addCommand = addCommand;
       this.updateCommand = updateCommand;
       this.removeByIdCommand = removeByIdCommand;
       this.clearCommand = clearCommand;
       this.sumOfHealthCommand = sumOfHealthCommand;
       this.saveCommand = saveCommand;

       commands.add(infoCommand);
       commands.add(showCommand);
       commands.add(addCommand);
       commands.add(updateCommand);
       commands.add(removeByIdCommand);
       commands.add(clearCommand);
       commands.add(sumOfHealthCommand);
       commands.add(saveCommand);
    }

    public void addToHistory(String commandToStore) {
        boolean marker = false;

        for (Command command : commands) {
            if (command.getName().equals(commandToStore)) marker = true;
        }
        if (commandToStore.equals("help") || commandToStore.equals("history")) marker = true;

        if (marker) {
            for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                commandHistory[i] = commandHistory[i-1];
            }
            commandHistory[0] = commandToStore;
        }
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

    public void history() {
        boolean marker = false;

        System.out.println("Последние использованные команды:");
        for (int i=0; i<COMMAND_HISTORY_SIZE; i++) {
            if (commandHistory[i] != null) {
                System.out.println(" " + commandHistory[i]);
                marker = true;
            }
        }

        if (!marker) System.out.println("*пусто*");
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

    public void removeById(String argument) {
        removeByIdCommand.execute(argument);
    }

    public void clear(String argument) {
        clearCommand.execute(argument);
    }

    public void sumOfHealth(String argument) {
        sumOfHealthCommand.execute(argument);
    }

    public void save(String argument) {
        saveCommand.execute(argument);
    }
}
