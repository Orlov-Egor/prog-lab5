package utility;

public class IdHandler {
    private static Long id = 0L;

    public static Long getNextId() {
        id += 1;
        return id;
    }
}
