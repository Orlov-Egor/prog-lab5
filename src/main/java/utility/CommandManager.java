package utility;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import exceptions.HistoryIsEmptyException;
import exceptions.WrongAmountOfElementsException;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command executeScriptCommand;
    private Command addIfMinCommand;
    private Command removeGreaterCommand;
    private Command historyCommand;
    private Command sumOfHealthCommand;
    private Command maxByMeleeWeaponCommand;
    private Command filterByWeaponTypeCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand, Command updateCommand,
                          Command removeByIdCommand, Command clearCommand, Command saveCommand, Command exitCommand, Command executeScriptCommand,
                          Command addIfMinCommand, Command removeGreaterCommand, Command historyCommand, Command sumOfHealthCommand,
                          Command maxByMeleeWeaponCommand, Command filterByWeaponTypeCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.historyCommand = historyCommand;
        this.sumOfHealthCommand = sumOfHealthCommand;
        this.maxByMeleeWeaponCommand = maxByMeleeWeaponCommand;
        this.filterByWeaponTypeCommand = filterByWeaponTypeCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(addIfMinCommand);
        commands.add(removeGreaterCommand);
        commands.add(historyCommand);
        commands.add(sumOfHealthCommand);
        commands.add(maxByMeleeWeaponCommand);
        commands.add(filterByWeaponTypeCommand);
    }

    public String[] getCommandHistory() {
        return commandHistory;
    }

    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    public void noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
    }
    
    public void help(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();

            for (Command command : commands) {
                System.out.printf("%-37s%-1s%n", " " + command.getName(), command.getDescription());
            }
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + helpCommand.getName() + "'");
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

    public void removeById(String argument) {
        removeByIdCommand.execute(argument);
    }

    public void clear(String argument) {
        clearCommand.execute(argument);
    }

    public void save(String argument) {
        saveCommand.execute(argument);
    }

    public void exit(String argument) {
        exitCommand.execute(argument);
    }

    public void executeScript(String argument) {
        executeScriptCommand.execute(argument);
    }

    public void addIfMin(String argument) {
        addIfMinCommand.execute(argument);
    }

    public void removeGreater(String argument) {
        removeGreaterCommand.execute(argument);
    }
    public void history(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (commandHistory.length == 0) throw new HistoryIsEmptyException();

            System.out.println("Последние использованные команды:");
            for (int i=0; i<commandHistory.length; i++) {
                if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
            }
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + historyCommand.getName() + "'");
        } catch (HistoryIsEmptyException exception) {
            System.out.println("Ни одной команды еще не было использовано!");
        }
    }

    public void sumOfHealth(String argument) {
        sumOfHealthCommand.execute(argument);
    }

    public void maxByMeleeWeapon(String argument) {
        maxByMeleeWeaponCommand.execute(argument);
    }

    public void filterByWeaponType(String argument) {
        filterByWeaponTypeCommand.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
