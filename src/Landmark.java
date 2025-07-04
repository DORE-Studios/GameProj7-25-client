/**
 * A Landmark is a feature present inside a cell, for example a planet, spacestation or star.
 * 
 * Each landmark has a name, description and landmark type.
 * The name and description give extra information to players about the landmark.
 * The landmark type decides certain gameplay mechanics that can take place on the landmark's cell.
 */
public class Landmark {

    private String name;
    private String description;
    private LandmarkType type;

    public Landmark (LandmarkData data) {
        this.name = data.name();
        this.description = data.description();
        this.type = LandmarkType.valueOf(data.type());
    }
    
    public String getName () {
        return name;
    }
    

    public String getDescription () {
        return description;
    }

    public LandmarkType getType() {
        return type;
    }

}