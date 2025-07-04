public class Ship{
    //the constants:
    final String[] name = {"Recon", "Combat", "Cargo", "Bio", "Robot"};
    final int[][] stats = {//armour(tbd), evasion, fuel_consumption, max_crew_size, 
                           //max_fuel_tank_size, max_shield_health, max_health, food_consumption(tbd)
                            {0, 40, 75, 4, 0, 900, 600, 800, 0},
                            {0, 30, 100, 6, 0, 1200, 800, 1200, 0},
                            {0, 20, 125, 8, 0, 1500, 1200, 1800, 0},
                            {0, 30, 0, 0, 0, 0, 0, 3000, 0},
                            {0, 30, 150, 0, 0, 2100, 1600, 1600, 0}};
    final double[] max_storage_space = {0, 0, 0, 0, 0}; //tbd
    //the variables:
    int ship_num;
    int crew;                       //
    double storage;                 //tbd
    int fuel_tank;                  //
    int shield_health;              //
    int health;                     //
    int currency;                   //
    int x = 0, y = 0;               //position :)

    public Ship(String ship_type){
        switch (ship_type) {
            case "Recon":   //#0
                this.ship_num = 0;
                this.crew = 2;
                this.storage = 0; //tbd
                this.fuel_tank = 750;
                this.shield_health = this.stats[0][5];
                this.health = this.stats[0][6];
                break;
            case "Combat":  //#1
                this.ship_num = 1;
                this.crew = 2;
                this.storage = 0; //tbd
                this.fuel_tank = 800;
                this.shield_health = this.stats[1][5];
                this.health = this.stats[1][6];
                break;
            case "Cargo":   //#2
                this.ship_num = 2;
                this.crew = 2;
                this.storage = 0; //tbd
                this.fuel_tank = 1000;
                this.shield_health = this.stats[2][5];
                this.health = this.stats[2][6];
                break;
            case "Bio":     //#3
                this.ship_num = 3;
                this.crew = 0; //no crew
                this.storage = 0; //tbd
                this.fuel_tank = 0; //no fuel
                this.shield_health = this.stats[3][5];
                this.health = this.stats[3][6];
                break;
            case "Robot":   //#4
                this.ship_num = 4;
                this.crew = 0; //no crew
                this.storage = 0; //tbd
                this.fuel_tank = 1200; 
                this.shield_health = this.stats[4][5];
                this.health = this.stats[4][6];
                break;
        }
    }

    void takeDamage(String gun_name){
        int damage = 1;
        switch (gun_name) {
            case "Minigun":
                damage = Weapon.Minigun();
            case "Cannon":
                damage = Weapon.Cannon();
            case "Laser":
                damage = Weapon.Laser();
            case "BFG":
                damage = Weapon.BFG();
        }

        if(shield_health == 0){
            health -= (damage - damage*this.stats[ship_num][0]);
        }else{
            shield_health -= damage;
            if(shield_health < 0){
                shield_health = 0;
            }
        }
    }

    public void shotAt(String gun_name){
        int shots;
        switch (gun_name) {
            case "Minigun":
                shots = 10;
            case "Laser":
                shots = 20;
            default:
                shots = 1;
        }
        for(int i = 0; i < shots; i++){
            int rand = (int)(Math.random() * 100) + 1;
            if(rand > this.stats[ship_num][1]){
                takeDamage(gun_name);
            }
        }
    }
}
