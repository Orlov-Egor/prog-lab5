package commands;

public interface Command{
    void execute(String argument);
    String getDescription();
    String getName();
}
