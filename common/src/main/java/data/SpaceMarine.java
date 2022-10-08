package data;

import exeptions.IncorrectData;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *  Main character
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double health; //Значение поля должно быть больше 0
    private String achievements; //Поле может быть null
    private AstartesCategory category; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(long id, String name, Coordinates coordinates, Date creationDate, double health, String achievements, AstartesCategory category, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.achievements = achievements;
        this.category = category;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine(String name, Coordinates coordinates, Date creationDate, double health, String achievements, AstartesCategory category, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.achievements = achievements;
        this.category = category;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine() {
        id = 1L;
    }

    public Chapter getChapter() {
        return chapter;
    }

    /**
     *
     * @return melee weapon
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @return creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return health
     */
    public double getHealth() {
        return health;
    }

    /**
     *
     * @return achievements
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     *
     * @return spacemarine category
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     * set id
     * @param id
     * @throws IncorrectData
     */
    public void setId(Long id) throws IncorrectData {
        if ((id == null) || (id <= 0)) {
            throw new IncorrectData();
        }
        this.id = id;
    }

    /**
     * set name
     * @param name
     * @throws IncorrectData
     */
    public void setName(String name) throws IncorrectData {
        if ((name == null) || (name.trim().equals(""))) {
            throw new IncorrectData();
        }
        this.name = name;
    }

    /**
     * set coordinates
     * @param coordinates
     * @throws IncorrectData
     */
    public void setCoordinates(Coordinates coordinates) throws IncorrectData {
        if (coordinates == null) {
            throw new IncorrectData();
        }
        this.coordinates = coordinates;
    }

    /**
     * set creation date
     * @param creationDate
     * @throws IncorrectData
     */
    public void setCreationDate(Date creationDate) throws IncorrectData {
        if (creationDate == null) {
            throw new IncorrectData();
        }
        this.creationDate = creationDate;
    }

    /**
     * set health
     * @param health
     * @throws IncorrectData
     */
    public void setHealth(double health) throws IncorrectData {
        if (health <= 0) {
            throw new IncorrectData();
        }
        this.health = health;
    }

    /**
     * set achievements
     * @param achievements
     * @throws IncorrectData
     */
    public void setAchievements(String achievements) throws IncorrectData {
        if (achievements == null) {
            throw new IncorrectData();
        }
        this.achievements = achievements;
    }

    /**
     * set category
     * @param category
     * @throws IncorrectData
     */
    public void setCategory(AstartesCategory category) throws IncorrectData {
        if (category == null) {
            throw new IncorrectData();
        }
        this.category = category;
    }

    /**
     * set melee weapon
     * @param meleeWeapon
     * @throws IncorrectData
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) throws IncorrectData {
        if (meleeWeapon == null) {
            throw new IncorrectData();
        }
        this.meleeWeapon = meleeWeapon;
    }

    /**
     * set chapter
     * @param chapter
     * @throws IncorrectData
     */
    public void setChapter(Chapter chapter) throws IncorrectData {
        if (chapter == null) {
            throw new IncorrectData();
        }
        this.chapter = chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return Double.compare(that.health, health) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health);
    }

    @Override
    public String toString() {
        return "SpaceMarine" + "\n" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                coordinates + "\n" +
                "creationDate = " + creationDate + "\n" +
                "health = " + health + "\n" +
                "achievements = " + achievements + "\n" +
                "category = " + category + "\n" +
                "meleeWeapon = " + meleeWeapon + "\n" +
                "chapter: " + chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return (int) (health - o.getHealth());

    }
}
