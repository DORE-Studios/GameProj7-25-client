public class Weapon extends Module{
    private int accuracy, shots;
    private boolean shieldEffective;
    protected int min, max;

    public Weapon(int accuracy, boolean bool, int min, int max, int shots){
        this.accuracy = accuracy;
        this.shieldEffective = bool;
        this.shots = shots;
        this.min = min;
        this.max = max;
    }

    /* if it will hit 100% of the time -> 2
     * if it hit ->1    if it missed ->0    */
    private int does_hit(int evasion){
        int chance = (int)(Math.random()*100)+1;
        if(accuracy > evasion){
            return 2;
        }else if(chance > evasion){
            return 1;
        }else{
            return 0;
        }
    }

    //determines how much damage will be dealt
    private int roll_dmg(){
        if(min == max){
            return min;
        }else{
            return (int)(Math.random()*(max-min+1))+min;
        }
    }

    /* doesn't roll hit chance for every bullet if accuracy is higher than evasion
     * will roll hit chance if accuracy is lower than evasion
     */
    public void attack(Ship S){
        int dmg;
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg();
                S.takeDamage(shieldEffective, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg();
                    S.takeDamage(shieldEffective, dmg);
                }
            }
        }
    }
}

class Minigun extends Weapon{
    public Minigun(){
        super(20, false, 25, 40, 10);
    }
}

class Railgun extends Weapon{
    int dmg = 0;
    public Railgun(){
        super(50, false, 250, 350, 1);
    }
}

class Sniper_Rifle extends Weapon{
    public Sniper_Rifle(){
        super(40, false, 200, 300, 1);
    }
}

class Chaingun extends Weapon{
    public Chaingun(){
        super(25, false, 30, 45, 15);
    }
}

class Flak_Cannon extends Weapon{
    public Flak_Cannon(){
        super(20, false, 4, 8, 50);
    }
}

class Laser_Beam extends Weapon{
    public Laser_Beam(){
        super(40, true, 10, 15, 20);
    }
}

class Arc_Launcher extends Weapon{
    int dmg;
    public Arc_Launcher(){
        super(60, true, 300, 300, 1);
    }
}

class Plasma_Blast extends Weapon{
    public Plasma_Blast(){
        super(20, true, 20, 30, 10);
    }
}

class Microwave_Ray extends Weapon{
    public Microwave_Ray(){
        super(20, true, 6, 6, 40);
    }
}
//fgh
class Hard_Light_Cannon extends Weapon{
    public Hard_Light_Cannon(){
        super(30, true, 400, 400, 1);
    }

    public void attack(Ship S){
        min /= 2; max /=2;          //halves damage so "super.attack(S);" doesn't deal double the damage it should
        S.AP(min);                  //half (400/2) will go directly to hull
        super.attack(S);            //half will go through normal process
    }
}

class EMP extends Weapon{
    public EMP(){
        super(101, false, 0, 0, 1);
    }

    public void attack(Ship S){
        S.noShield();
    }
}

class BFG extends Weapon{
    public BFG(){
        super(101, false, 1200, 1500, 1);
    }
}

class Missle extends Weapon{
    public Missle(){
        super(101, false, 100, 100, 1);
    }
}

class Torpedo extends Weapon{
    public Torpedo(){
        super(65, false, 200, 200, 1);
    }
}

class Nuke extends Weapon{
    public Nuke(){
        super(100, false, 500, 500, 1);
    }

    public void attack(Ship S){
        if(S.getShield() == 0){
            min /= 2; max /= 2;
        }
        super.attack(S);
    }
}