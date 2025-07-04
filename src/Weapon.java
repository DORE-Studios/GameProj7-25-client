public class Weapon{
    protected int accuracy;
    protected boolean shieldEffective;

    public Weapon(int accuracy, boolean bool){
        this.accuracy = accuracy;
        this.shieldEffective = bool;
    }

    protected int does_hit(int evasion){
        int chance = (int)(Math.random()*100)+1;
        if(accuracy > evasion){
            return 2;
        }else if(chance > evasion){
            return 1;
        }else{
            return 0;
        }
    }

    protected int roll_dmg(int min, int max){
        return (int)(Math.random()*(max-min+1))+min;
    }

    public void attack(Ship S){}

    protected void doDamage(Ship S, int dmg){
        if(S.getShield() > 0){
            if(shieldEffective){
                S.reduceShield(dmg*2);
            }else{
                S.reduceShield(dmg);
            }
        }else{
            S.reduceHealth(dmg);
        }
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
        super(20, false);
    }
    
    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg(25, 40);
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg(25, 40);
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Railgun extends Weapon{
    int dmg = 0;
    public Railgun(){
        super(50, false);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) < 0){
            dmg = roll_dmg(250, 350);
            doDamage(S, dmg);
        }
    }
}

class Sniper_Rifle extends Weapon{
    int dmg;
    public Sniper_Rifle(){
        super(40, false);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) < 0){
            dmg = roll_dmg(200, 300);
            doDamage(S, dmg);
        }
    }
}

class Chaingun extends Weapon{
    int shots = 15, dmg = 0;

    public Chaingun(){
        super(25, false);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg(30, 45);
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg(35, 45);
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Flak_Cannon extends Weapon{
    int shots = 50, dmg = 0;
    
    public Flak_Cannon(){
        super(20, false);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg(4, 8);
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg(4, 8);
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Laser_Beam extends Weapon{
    int shots = 20, dmg = 0;

    public Laser_Beam(){
        super(40, true);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg(10, 15);
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg(10, 15);
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Arc_Launcher extends Weapon{
    int dmg;
    public Arc_Launcher(){
        super(60, true);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) < 0){
            dmg = 300;
            doDamage(S, accuracy);
        }
    }
}

class Plasma_Blast extends Weapon{
    int shots = 10, dmg = 0;

    public Plasma_Blast(){
        super(20, true);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = roll_dmg(20, 30);
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = roll_dmg(20, 30);
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Microwave_Ray extends Weapon{
    int shots = 40, dmg = 0;
    public Microwave_Ray(){
        super(20, true);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) == 2){
            for(int i = 0; i < shots; i++){
                dmg = 6;
                doDamage(S, dmg);
            }
        }else{
            for(int i = 0; i < shots; i++){
                if(does_hit(S.getEvasion()) == 1){
                    dmg = 6;
                    doDamage(S, dmg);
                }
            }
        }
    }
}

class Hard_Light_Cannon extends Weapon{
    public Hard_Light_Cannon(){
        super(0, true);               //tbd
    }
}

class EMP extends Weapon{
    public EMP(){
        super(100, false);             //uhhh ill do that later
    }
}

class BFG extends Weapon{
    int dmg;
    public BFG(){
        super(100, false);
    }

    public void attack(Ship S){
        dmg = roll_dmg(1200, 1500);
        doDamage(S, dmg);
    }
}

class Missle extends Weapon{
    int dmg;
    public Missle(){
        super(100, false);
    }

    public void attack(Ship S){
        dmg = 100;
        doDamage(S, dmg);
    }
}

class Torpedo extends Weapon{
    int dmg;
    public Torpedo(){
        super(65, false);
    }

    public void attack(Ship S){
        if(does_hit(S.getEvasion()) < 0){
            dmg = 200;
            doDamage(S, dmg);
        }
    }
}

class Nuke extends Weapon{
    int dmg;
    public Nuke(){
        super(100, false);
    }

    public void attack(Ship S){
        dmg = 500;
        doDamage(S, dmg);
    }
}