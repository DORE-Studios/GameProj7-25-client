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
        SHIP_STATS.put(ShipType.RECON, new ShipStats("Recon", 1, 10, 40, 750, 75, 3, 900, 600, 800, 0, 0.0));
        SHIP_STATS.put(ShipType.CARGO, new ShipStats("Combat", 3, 10, 30, 800, 100, 5, 1200, 800, 1200, 0, 0.0));
        SHIP_STATS.put(ShipType.COMBAT, new ShipStats("Cargo", 4, 10, 20, 1200, 125, 7, 1500, 1200, 1800, 0, 0.0));
        SHIP_STATS.put(ShipType.BIO, new ShipStats("Bio", 6, 10, 30, 000, 0, 0, 0, 0, 3000, 0, 0.0));
        SHIP_STATS.put(ShipType.ROBOT, new ShipStats("Robot", 2, 10, 30, 1600, 150, 0, 2100, 1600, 1600, 0, 0.0));
    }

    //the variables:
    private ShipType shipType;
    private ShipStats stats = SHIP_STATS.get(shipType);
    private double storage;    //tbd
    private int fuel_tank;     //
    private int shield_health; //
    private int health;        //
    private int currency;      //
    private int x = 0, y = 0;  //position >:-)
    private int armourLevel;
    private int weaponCount;
    private int fuel_tank_capacity;
    private double storage_capacity;
    private Module[] modules = new Module[5];
    private java.util.Map<Item.ItemType, Integer> itemsInStorage = new HashMap<>();{
        itemsInStorage.put(Item.ItemType.FOOD, 0);
    }
    private java.util.List<Crew> shipCrew = new ArrayList<>();

    //constructor
    public Ship(ShipType ST){
        this.shipType = ST;
        this.storage = 0;                                   //tbd
        this.fuel_tank = stats.initialFuel();
        this.shield_health = stats.shieldCapacity();
        this.health = stats.maxHealth();
        this.armourLevel = stats.initialArmour();
        this.weaponCount = 0;
        this.fuel_tank_capacity = stats.fuelTankCapacity();;
        this.storage_capacity = stats.storageCapacity();
        if(stats.crewCapacity != 0){
            shipCrew.add(new Crew());
        }
    }


    /*
     * EVERYTHING TO DO WITH HEALTH, SHIELD, DAMAGE, ETC
     */

    //to shoot at the enemy :)
    public void shootAt(Ship S, Weapon wpn){
        if(!wpn.isCrewed()){return;}
        if(wpn instanceof EMP){
            wpn.attack(this);
        }
        S.shotAt(wpn);
    }

    //the enemy has shot at you :(
    public void shotAt(Weapon wpn){ //not to be confused with "shootAt()"
        wpn.attack(this);
    }

    //returns shield health
    public int getShield(){
        return this.shield_health;
    }

    //returns hull health
    public int getHealth(){
        return this.health;
    }

    //the shield is taking damage
    private void reduceShield(int damage){
        this.shield_health -= damage;
        if(this.shield_health < 0){
            this.shield_health = 0;
        }
    }

    //the hull is taking damage
    private void reduceHealth(int damage){
        int reducedDamage;
        reducedDamage = (int)((damage*((this.armourLevel) * 0.05))-this.armourLevel);
        this.health -= reducedDamage;
    }

    //general method for taking damage, determines where the damage should go
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

    //for effects that eliminate the shield entirely
    public void noShield(){
        shield_health = 0;
    }

    //for things that can ignore the shield and damage the hull directly
    public void AP(int damage){
        reduceHealth(damage);
    }

    /* HEALTH, SHIELD, DAMAGE, ETC FUNCTIONS END */


    /* 
     * EVERYTHING TO DO WITH THE FUEL AND CARGO
    */

    //returns max storage capacity
    private double getStorageCapacity(){
        double tot = stats.storageCapacity();
        for(Module m: modules){
            if(m instanceof Upgrade){
                Upgrade u = (Upgrade) m;
                Upgrade.UpgradeType ut = u.getType();
                if(ut == Upgrade.UpgradeType.STORAGE){
                    tot += u.getStorageBoost();
                }
            }
        }
        return tot;
    }
    //fgh
    //to add cargo
    public void addCargo(Item item){
        double weight = item.getItemWeight();
        int count = itemsInStorage.get(item.getItemType());
        if((this.storage + weight) > this.storage_capacity){
            return;
        }else{
            this.storage += weight;
            itemsInStorage.put(item.getItemType(), count+1);
        }
    }

    //to remove cargo
    public void removeCargo(Item item){
        int count = itemsInStorage.get(item.getItemType());
        if(count == 0){
            return;
        }else{
            itemsInStorage.put(item.getItemType(), count-1);
        }
        double weight = item.getItemWeight();
        this.storage -= weight;
        if(this.storage < 0){
            this.storage = 0;
        }
    }

    //returns max fuel tank capacity
    private int getFuelCapacity(){
        int tot = stats.fuelTankCapacity();
        for(Module m: modules){
            if(m instanceof Upgrade){
                Upgrade u = (Upgrade) m;
                Upgrade.UpgradeType ut = u.getType();
                if(ut == Upgrade.UpgradeType.FUEL){
                    tot += u.getFuelBoost();
                }
            }
        }
        return tot;
    }

    //refuel the ship
    public void refuel(){
        if(fuel_tank == fuel_tank_capacity){
            return;
        }else{
            fuel_tank += 50;
            if(fuel_tank > fuel_tank_capacity){
                fuel_tank = fuel_tank_capacity;
            }
        }
    }

    /* FUEL & STORAGE FUNCTIONS END */


    /*
     * Crew FUNCTIONS
     */

    //returns the number of crew that are not assigned a station
    public int getUnemployedCount(){
        int count = 0;
        for(Crew c: shipCrew){
            if(c.getStation() == null){
                count++;
            }
        }
        return count;
    }

    //adds a crew to the ship :)
    public void addCrewToShip(Crew c){
        shipCrew.add(c);
    }

    //removes a crew from the ship :(
    public void removeCrewFromShip(Crew c){
        shipCrew.remove(c);
    }

    //returns the amount of crew in the ship
    public int getCrewCount(){
        return shipCrew.size();
    }

    public void assignCrew(Crew c, Module m){
        if(m instanceof Weapon && c.getStation() == null){
            Weapon w = (Weapon) m;
            if(!w.isCrewed()){
                w.addCrew(c);
                c.addToStation(w);
            }
        }else if(m instanceof ShieldGenerator){
            ShieldGenerator sg = (ShieldGenerator) m;
            if(!sg.isCrewed()){
                sg.addCrew(c);
                c.addToStation(sg);
            }
        }
    }

    public void unassignCrew(Crew c, Module m){
        if(m instanceof Weapon && c.getStation() == null){
            Weapon w = (Weapon) m;
            if(!w.isCrewed()){
                w.removeCrew(c);
                c.removeFromStation(w);
            }
        }else if(m instanceof ShieldGenerator){
            ShieldGenerator sg = (ShieldGenerator) m;
            if(!sg.isCrewed()){
                sg.removeCrew(c);
                c.removeFromStation(sg);
            }
        }
    }

    /* CREW FUNCTIONS END */


    /*
     * OTHER FUNCTIONS
     */

    //returns evasion
    public int getEvasion(){
        int tot = stats.evasion();
        for(Module m: modules){
            if(m instanceof Upgrade){
                Upgrade u = (Upgrade) m;
                Upgrade.UpgradeType ut = u.getType();
                if(ut == Upgrade.UpgradeType.THRUSTER){
                    tot += u.getEvasionBoost();
                }
            }
        }
        return tot;
    }

    //to add a module to the ship
    public void addModule(Module toAdd){
        if((toAdd instanceof Weapon) && (weaponCount >= 3)){
            return;
        }
        for(Module m : modules){
            if(m == null){
                m = toAdd;
                if(toAdd instanceof Weapon){
                    weaponCount++;
                }
                break;
            }
        }
        if(toAdd instanceof Upgrade){
            fuel_tank_capacity = getFuelCapacity();
            storage_capacity = getStorageCapacity();
        }
    }

    //to remove a module from the ship
    public void removeModule(Module toRemove){
        for(Module m: modules){
            if(m == toRemove){
                m = null;
                break;
            }
        }
        if(toRemove instanceof Upgrade){
            fuel_tank_capacity = getFuelCapacity();
            storage_capacity = getStorageCapacity();
        }
    }
}