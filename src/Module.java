import java.util.*;

public abstract class Module {}

class Upgrade extends Module{
    public enum UpgradeType{
        STORAGE, FUEL, TRUSTER, RADAR
    }

    private final record StatBoosts(String name, int fuelTank, double storage, double combatChance, int higherMovementConsumption, int evasionBoost){}

    private static final java.util.Map<UpgradeType, StatBoosts> STAT_BOOST = new HashMap<>();{
        STAT_BOOST.put(UpgradeType.STORAGE, new StatBoosts("Storage", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.FUEL, new StatBoosts("Fuel", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.TRUSTER, new StatBoosts("Thruster", 0, 0.0, 0.0, 0, 0));
        STAT_BOOST.put(UpgradeType.RADAR, new StatBoosts("Radar", 0, 0.0, 0.0, 0, 0));
    }

    private UpgradeType upgradeType;
    private StatBoosts stats = STAT_BOOST.get(upgradeType);

    public Upgrade(UpgradeType UT){
        this.upgradeType = UT;
    }

    public int getFuelBoost(){
        return stats.fuelTank();
    }

    public double getStorageBoost(){
        return stats.storage();
    }

    public double getCombatChanceBoost(){
        return stats.combatChance();
    }

    public int getMovementModifier(){
        return stats.higherMovementConsumption();
    }

    public int getEvasionBoost(){
        return stats.evasionBoost();
    }
}