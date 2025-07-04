import java.util.*;

public class Ship{
    private final record ShipStats(String name, int initialArmour, int maxArmour, int evasion, int initialFuel,
        int fuelConsumption, int crewCapacity, int fuelTankCapacity, int shieldCapacity,
        int maxHealth, int foodConsumption, double storageCapacity){}

    public enum ShipType{
        RECON, CARGO, COMBAT, BIO, ROBOT
    }

    // Ship stats as records
    private static final java.util.Map<ShipType, ShipStats> SHIP_STATS = new HashMap<>();{
        SHIP_STATS.put(ShipType.RECON, new ShipStats("Recon", 1, 10, 40, 750, 75, 4, 900, 600, 800, 0, 0.0));
        SHIP_STATS.put(ShipType.CARGO, new ShipStats("Combat", 3, 10, 30, 800, 100, 6, 1200, 800, 1200, 0, 0.0));
        SHIP_STATS.put(ShipType.COMBAT, new ShipStats("Cargo", 4, 10, 20, 1200, 125, 8, 1500, 1200, 1800, 0, 0.0));
        SHIP_STATS.put(ShipType.BIO, new ShipStats("Bio", 6, 10, 30, 000, 0, 0, 0, 0, 3000, 0, 0.0));
        SHIP_STATS.put(ShipType.ROBOT, new ShipStats("Robot", 2, 10, 30, 1600, 150, 0, 2100, 1600, 1600, 0, 0.0));
    }

    //the variables:
    private ShipType shipType;
    private ShipStats stats = SHIP_STATS.get(shipType);
    private int crew;          //
    private double storage;    //tbd
    private int fuel_tank;     //
    private int shield_health; //
    private int health;        //
    private int currency;      //
    private int x = 0, y = 0;  //position >:-)
    private int armour;

    public Ship(ShipType ST){
        shipType = ST;

        this.crew = stats.crewCapacity == 0 ? 0 : 2;
        this.storage = 0;                                   //tbd
        this.fuel_tank = stats.initialFuel();
        this.shield_health = stats.shieldCapacity();
        this.health = stats.maxHealth();
        this.armour = stats.initialArmour();
    }

    public void shootAt(Ship S, Weapon wpn){
        if(wpn instanceof EMP){
            wpn.attack(this);
        }
        S.shotAt(wpn);
    }

    public void shotAt(Weapon wpn){ //not to be confused with "shootAt()"
        wpn.attack(this);
    }

    public int getEvasion(){
        return stats.evasion();
    }

    public int getShield(){
        return this.shield_health;
    }

    public int getHealth(){
        return this.health;
    }

    private void reduceShield(int damage){
        this.shield_health -= damage;
        if(this.shield_health < 0){
            this.shield_health = 0;
        }
    }

    private void reduceHealth(int damage){
        int reducedDamage;
        reducedDamage = (int)((damage*((this.armour) * 0.05))-this.armour);
        this.health -= reducedDamage;
    }

    public void takeDamage(boolean SE, int damage){
        if(shield_health > 0){
            if(SE){
                reduceShield(damage*2);
            }else{
                reduceShield(damage);
            }
        }else{
            reduceHealth(damage);
        }
    }

    public void noShield(){
        shield_health = 0;
    }

    public void AP(int damage){
        reduceHealth(damage);
    }
}
