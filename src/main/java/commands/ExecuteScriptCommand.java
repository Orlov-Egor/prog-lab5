package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

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
            Console.println("Выполняю скрипт '" + argument + "'...");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
    }
}
