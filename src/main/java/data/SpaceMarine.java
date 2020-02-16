package data;

import java.time.LocalDateTime;

public class SpaceMarine implements Comparable<SpaceMarine> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarine(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, double health,
                       AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public double getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (id > o.getId()) return 1;
        else if (id < o.getId()) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        String info = "";

        info += "Солдат №" + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Здоровье: " + health;
        info += "\n Категория: " + category;
        info += "\n Дальнее оружие: " + weaponType;
        info += "\n Ближнее оружие: " + meleeWeapon;
        info += "\n Орден: " + chapter;

        return info;
    }
}
