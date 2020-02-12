package data;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public long getMarinesCount() {
        return marinesCount;
    }

    @Override
    public String toString() {
        return name + " (" + marinesCount + " солдат)";
    }
}
