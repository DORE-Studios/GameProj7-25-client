import java.util.*;

public abstract class Module {}

class Upgrade extends Module{
    public enum UpgradeType{
        STORAGE, FUEL, THRUSTER, RADAR
    }

    private final record StatBoosts(String name, int fuelTank, double storage, double combatChance, int higherMovementConsumption, int evasionBoost){}

    private static final java.util.Map<UpgradeType, StatBoosts> STAT_BOOST = new HashMap<>();{
        STAT_BOOST.put(UpgradeType.STORAGE, new StatBoosts("Storage", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.FUEL, new StatBoosts("Fuel", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.THRUSTER, new StatBoosts("Thruster", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.RADAR, new StatBoosts("Radar", 0, 0.0, 0.0, 0, 0));
    }

    private UpgradeType upgradeType;
    private StatBoosts stats = STAT_BOOST.get(upgradeType);

    //constructor
    public Upgrade(UpgradeType UT){
        this.upgradeType = UT;
    }

    //returns type of upgrade module
    public UpgradeType getType(){
        return upgradeType;
    }

    //returns the fuel tank modifier
    public int getFuelBoost(){
        return stats.fuelTank();
    }

    //returns the storage modifier
    public double getStorageBoost(){
        return stats.storage();
    }

    //returns the chance to encounter a combat modifier
    public double getCombatChanceBoost(){
        return stats.combatChance();
    }

    //returns the movement consumption modifier
    public int getMovementModifier(){
        return stats.higherMovementConsumption();
    }

    //returns the evasion modifier
    public int getEvasionBoost(){
        return stats.evasionBoost();
    }
}

class ShieldGenerator extends Module{
    private Crew crew;
    private boolean isCrewed = false;;

    public ShieldGenerator(){}

    public void addCrew(Crew crew){
        this.crew = crew;
        this.isCrewed = true;
    }

    public void removeCrew(Crew crew){
        if(this.crew == crew){
            this.crew = null;
            this.isCrewed = false;
        }
    }

    public boolean isCrewed(){
        return isCrewed;
    }
}