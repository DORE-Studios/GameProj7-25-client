import java.util.*;

public class Ship
{
    public enum ShipType
    {
        RECON, CARGO, COMBAT, BIO, ROBOT
    }

    // Ship stats as records
    private static final java.util.Map<ShipType, ShipStats> SHIP_STATS = new HashMap<>();
    {
        SHIP_STATS.put(ShipType.RECON, new ShipStats("Recon", 0, 40, 000, 75, 4, 900, 600, 800, 0, 0.0));
        SHIP_STATS.put(ShipType.CARGO, new ShipStats("Combat", 0, 30, 000, 100, 6, 1200, 800, 1200, 0, 0.0));
        SHIP_STATS.put(ShipType.COMBAT, new ShipStats("Cargo", 0, 20, 000, 125, 8, 1500, 1200, 1800, 0, 0.0));
        SHIP_STATS.put(ShipType.BIO, new ShipStats("Bio", 0, 30, 000, 0, 0, 0, 0, 3000, 0, 0.0));
        SHIP_STATS.put(ShipType.ROBOT, new ShipStats("Robot", 0, 30, 000, 150, 0, 2100, 1600, 1600, 0, 0.0));
    }

    //the variables:
    private int crew; //
    private double storage; //tbd
    private int fuel_tank; //
    private int shield_health; //
    private int health; //
    private int currency; //
    private int x = 0, y = 0; //position >:-)

    public Ship(ShipType shipType)
    {
        ShipStats baseStats = SHIP_STATS.get(shipType);

        this.crew = baseStats.crewCapacity == 0 ? 0 : 2;
        this.storage = 0; //tbd
        this.fuel_tank = baseStats.initialFuel();
        this.shield_health = baseStats.shieldCapacity();
        this.health = baseStats.maxHealth();

        // switch (ship_type)
        // {
        // case "Recon": //#0
        //     this.ship_num = 0;
        //     this.crew = 2;
        //     this.storage = 0; //tbd
        //     this.fuel_tank = 750;
        //     this.shield_health = this.stats[0][5];
        //     this.health = this.stats[0][6];
        //     break;
        // case "Combat": //#1
        //     this.ship_num = 1;
        //     this.crew = 2;
        //     this.storage = 0; //tbd
        //     this.fuel_tank = 800;
        //     this.shield_health = this.stats[1][5];
        //     this.health = this.stats[1][6];
        //     break;
        // case "Cargo": //#2
        //     this.ship_num = 2;
        //     this.crew = 2;
        //     this.storage = 0; //tbd
        //     this.fuel_tank = 1000;
        //     this.shield_health = this.stats[2][5];
        //     this.health = this.stats[2][6];
        //     break;
        // case "Bio": //#3
        //     this.ship_num = 3;
        //     this.crew = 0; //no crew
        //     this.storage = 0; //tbd
        //     this.fuel_tank = 0; //no fuel
        //     this.shield_health = this.stats[3][5];
        //     this.health = this.stats[3][6];
        //     break;
        // case "Robot": //#4
        //     this.ship_num = 4;
        //     this.crew = 0; //no crew
        //     this.storage = 0; //tbd
        //     this.fuel_tank = 1200;
        //     this.shield_health = this.stats[4][5];
        //     this.health = this.stats[4][6];
        //     break;
        // }
    }

    private final record ShipStats(String name, int armor, int evasion, int initialFuel, int fuelConsumption, int crewCapacity, int fuelTankCapacity, int shieldCapacity, int maxHealth, int foodConsumption, double storageCapacity)
    {}
}
