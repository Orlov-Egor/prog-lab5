package commands;

import exceptions.WrongAmountOfElementsException;

/**
 * Command 'execute_script'. Executes scripts from a file.
 */
public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    /**
     * Executes the command, but partially.
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            System.out.println("Выполняю скрипт '" + argument + "'...");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println(" Использование: '" + getName() + "'");
        }
    }
}
