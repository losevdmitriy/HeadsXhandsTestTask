package org.losev.game;

public class Player extends Creature {
    private int selfHealCount;
    private static final double MAX_HEAL_MODIFIER = 0.3;


    public Player(String name, int attack, int defense, int health, int minDamage,int maxDamage) {
        super(name, attack, defense, health, minDamage, maxDamage);
        selfHealCount = 4;
    }


    public void heal() {

        if(!isAlive()){
            System.out.println(this.getName() + " мертв");
        } else if (selfHealCount > 0) {
            int roundedHealAmount = (int) Math.round(MAX_HEAL_MODIFIER * getMaxHealth());
            setHealth(getHealth() + roundedHealAmount);

            selfHealCount--;
            System.out.println(this.getName() + " исцелил себя на " + roundedHealAmount + " здоровья.");
        } else {
            System.out.println("У " + this.getName() + " закончились исцеления.");
        }
    }
}
