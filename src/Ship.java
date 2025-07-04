import java.util.*;

public class Ship{
    private final record ShipStats(String name, int armor, int evasion, int initialFuel,
        int fuelConsumption, int crewCapacity, int fuelTankCapacity, int shieldCapacity,
        int maxHealth, int foodConsumption, double storageCapacity){}

    public enum ShipType{
        RECON, CARGO, COMBAT, BIO, ROBOT
    }

    // Ship stats as records
    private static final java.util.Map<ShipType, ShipStats> SHIP_STATS = new HashMap<>();{
        SHIP_STATS.put(ShipType.RECON, new ShipStats("Recon", 0, 40, 750, 75, 4, 900, 600, 800, 0, 0.0));
        SHIP_STATS.put(ShipType.CARGO, new ShipStats("Combat", 0, 30, 800, 100, 6, 1200, 800, 1200, 0, 0.0));
        SHIP_STATS.put(ShipType.COMBAT, new ShipStats("Cargo", 0, 20, 1200, 125, 8, 1500, 1200, 1800, 0, 0.0));
        SHIP_STATS.put(ShipType.BIO, new ShipStats("Bio", 0, 30, 000, 0, 0, 0, 0, 3000, 0, 0.0));
        SHIP_STATS.put(ShipType.ROBOT, new ShipStats("Robot", 0, 30, 1600, 150, 0, 2100, 1600, 1600, 0, 0.0));
    }

    //the variables:
    private ShipType thisShipType;
    private int crew;          //
    private double storage;    //tbd
    private int fuel_tank;     //
    private int shield_health; //
    private int health;        //
    private int currency;      //
    private int x = 0, y = 0;  //position >:-)

    public Ship(ShipType shipType){
        thisShipType = shipType;
        ShipStats baseStats = SHIP_STATS.get(shipType);

        this.crew = baseStats.crewCapacity == 0 ? 0 : 2;
        this.storage = 0;                                   //tbd
        this.fuel_tank = baseStats.initialFuel();
        this.shield_health = baseStats.shieldCapacity();
        this.health = baseStats.maxHealth();
    }

    public void shootAt(Ship S, Weapon W){
        S.shotAt(W);
    }

    public void shotAt(Weapon W){
        W.attack(this);
    }

    public int getEvasion(){
        return SHIP_STATS.get(thisShipType).evasion();
    }

    public int getShield(){
        return this.shield_health;
    }

    public int getHealth(){
        return this.health;
    }

    public void reduceShield(int damage){
        this.shield_health -= damage;
        if(this.shield_health < 0){
            this.shield_health = 0;
        }
    }

    public void reduceHealth(int damage){
        this.health -= damage;
    }
}
