package data;

import exeptions.IncorrectData;

import java.io.Serializable;

/**
 * Chapter with marines
 */
public class Chapter implements Serializable {
    private Long id;
    private String name; //field can't be null
    private String parentLegion;

    public Chapter(String name, String parentLegion) throws IncorrectData {
        this.setName(name);
        this.setParentLegion(parentLegion);
    }
    public Chapter(){}

    public Chapter(Long id, String name, String parentLegion) {
        this.id = id;
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * set name
     * @param name
     * @throws IncorrectData
     */
    private void setName(String name) throws IncorrectData {
        if ((name == null) || (name.trim().equals(""))) {
            throw new IncorrectData();
        }
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * set parent legion
     * @param parentLegion
     */
    private void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public String toString() {
        return "\n" + "Chapter Name = " + name + "\n" +
                "Parent Legion = " + parentLegion + "\n";
    }

    /**
     * @return parent legion
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }
}
