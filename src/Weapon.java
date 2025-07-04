public class Weapon{
    protected int accuracy;

    public Weapon(int accuracy){
        this.accuracy = accuracy;
    }

    public int does_hit(int evasion){
        int chance = (int)(Math.random()*100)+1;
        if(accuracy > evasion){
            return 2;
        }else if(chance > evasion){
            return 1;
        }else{
            return 0;
        }
    }

    public int roll_dmg(int min, int max){
        return (int)(Math.random()*(max-min+1))+min;
    }
}

/*
 * compare accuracy and evasion
 * if accuracy is greater than evasion
 *  -> hits 100% of the time
 * if accuracy is less than evasion
 *  -> each shot has to roll to hit
 * when hit, each shot has to roll damage
 */

class Minigun extends Weapon{
    int shots = 10, dmg = 0;

    public Minigun(){
        super(20);
    }
    
    public int attack(int evasion){
        if(does_hit(evasion) == 2){
            for(int i = 0; i < shots; i++){
                dmg += roll_dmg(25, 40);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(evasion) == 1){
                    dmg += roll_dmg(25, 40);
                }
            }
        }
        return dmg;
    }
}

class Railgun extends Weapon{
    public Railgun(){
        super(50);
    }

    public int attack(int evasion){
        if(does_hit(evasion) < 0){
            return roll_dmg(250, 350);
        }else{
            return 0;
        }
    }
}

class Sniper_Rifle extends Weapon{
    public Sniper_Rifle(){
        super(40);
    }

    public int attack(int evasion){
        if(does_hit(evasion) < 0){
            return roll_dmg(200, 300);
        }else{
            return 0;
        }
    }
}

class Chaingun extends Weapon{
    int shots = 15, dmg = 0;

    public Chaingun(){
        super(25);
    }

    public int attack(int evasion){
        if(does_hit(evasion) == 2){
            for(int i = 0; i < shots; i++){
                dmg += roll_dmg(30, 45);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(evasion) == 1){
                    dmg += roll_dmg(30, 45);
                }
            }
        }
        return dmg;
    }
}

class Flak_Cannon extends Weapon{
    int shots = 50, dmg = 0;
    
    public Flak_Cannon(){
        super(20);
    }

    public int attack(int evasion){
        if(does_hit(evasion) == 2){
            dmg += 4*shots;
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(evasion) == 1){
                    dmg += 4;
                }
            }
        }
        return dmg;
    }
}

class Laser_Beam extends Weapon{
    int shots = 20, dmg = 0;

    public Laser_Beam(){
        super(40);
    }

    public int attack(int evasion){
        if(does_hit(evasion) == 2){
            for(int i = 0; i < shots; i++){
                dmg += roll_dmg(10, 15);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(evasion) == 1){
                    dmg += roll_dmg(10, 15);
                }
            }
        }
        return dmg;
    }
}

class Arc_Launcher extends Weapon{
    public Arc_Launcher(){
        super(60);
    }

    public int attack(int evasion){
        if(does_hit(evasion) < 0){
            return 300;
        }else{
            return 0;
        }
    }
}

class Plasma_Blast extends Weapon{
    int shots = 10, dmg = 0;

    public Plasma_Blast(){
        super(20);
    }

    public int attack(int evasion){
        if(does_hit(evasion) == 2){
            for(int i = 0; i < shots; i++){
                dmg += roll_dmg(20, 30);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(evasion) == 1){
                    dmg += roll_dmg(20, 30);
                }
            }
        }
        return dmg;
    }
}

class Microwave_Ray extends Weapon{
    public Microwave_Ray(){
        super(20);
    }

    public int attack(int evasion){
        if(does_hit(evasion) < 0){
            return 8;
        }else{
            return 0;
        }
    }
}

class Hard_Light_Cannon extends Weapon{
    public Hard_Light_Cannon(){
        super(0);               //tbd
    }
}

class EMP extends Weapon{
    public EMP(){
        super(100);             //uhhh ill do that later
    }
}

class BFG extends Weapon{
    public BFG(){
        super(100);
    }

    public int attack(int evasion){
        return roll_dmg(1200, 1500);
    }
}

class Missle extends Weapon{
    public Missle(){
        super(100);
    }

    public int attack(int evasion){
        return 100;
    }
}

class Torpedo extends Weapon{
    public Torpedo(){
        super(65);
    }

    public int attack(int evasion){
        if(does_hit(evasion) < 0){
            return 200;
        }else{
            return 0;
        }
    }
}

class Nuke extends Weapon{
    public Nuke(){
        super(100);
    }

    public int attack(int evasion){
        return 500;
    }
}