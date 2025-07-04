/**
 * Represents a cell in the map's grid.
 * 
 * A cell is a tile in the map that players can traverse through. They can have certain environments, can be owned by a certain
 * faction, and can have a landmark present in the cell.
 */
public class Cell {
    
    private final boolean[] environmentFlags = new boolean[4];
    private final Faction faction;
    private final Landmark landmark;

    public Cell (CellData data) {

        byte environmentFlagsInt = (byte) data.environmentFlags();
        for (int i = 0; i < 4; ++i) {
            this.environmentFlags[i] = (environmentFlagsInt & (1 << i)) != 0;
        }

        this.faction = Faction.valueOf(data.faction());
        if (data.landmark() != null) {
            this.landmark = new Landmark(data.landmark());
        } else {
            this.landmark = null;
        }

    }

    /**
     * Gets the Faction that the cell belongs to.
     * @return The faction this cell belongs to, or null if not owned by any faction. 
     */
    public Faction getFaction () {
        return faction;
    }

    /**
     * Gets the singular landmark present inside the cell.
     * @return The landmark, or null if no landmark exists.
     */
    public Landmark getLandmark () {
        return landmark;
    }

    public boolean isNebula() {
        return environmentFlags[0];
    }

    public boolean isAsteroids() {
        return environmentFlags[1];
    }

    public boolean isSpaceDust() {
        return environmentFlags[2];
    }

    public boolean isIceFields() {
        return environmentFlags[3];
    }

}
