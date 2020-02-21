package commands;

import exceptions.IncorrectInputInScriptException;

/**
 * Interface for all commands.
 */
public interface Command {
    void execute(String argument);
    String getDescription();
    String getName();
}
