package utility;

// TODO: Нормальная реализация генерации ID
// Класс является временным решением до начала работы с файловой системой
public class IdManager {
    private static Long id = 0L;

    public static Long getNextId() {
        id += 1;
        return id;
    }
}
