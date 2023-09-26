package org.losev.game;

import java.util.Random;

abstract class Creature {
    private static final int MIN_ATTACK_AND_DAMAGE_VALUE = 1;
    private static final int MAX_ATTACK_AND_DAMAGE_VALUE = 30;
    private static final int DICE_SIDES = 6;
    private static final int MIN_SUCCESS_ROLL = 5;
    private final Random random = new Random();

    private final String name;
    private final int attack;
    private final int defense;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private final int maxHealth;

    public Creature(String name, int attack, int defense, int health, int minDamage, int maxDamage) {
        if (isValidAttributeValue(attack) && isValidAttributeValue(defense)) {
            this.name = name;
            this.attack = attack;
            this.defense = defense;
            this.health = health;
            this.minDamage = minDamage;
            this.maxDamage = maxDamage;
            this.maxHealth = health;
        } else {
            throw new IllegalArgumentException("Неверное значение атаки или защиты. Оно должно быть между "
                    + MIN_ATTACK_AND_DAMAGE_VALUE + " и " + MAX_ATTACK_AND_DAMAGE_VALUE + ".");
        }
    }

    public void attack(Creature target) {
        int attackModifier = calculateAttackModifier(target);
        if (isAttackSuccessful(attackModifier)) {
            int damage = calculateDamage();
            System.out.println(name + " атакует и наносит " + damage + " урона " + target.getName() + ".");
            target.takeDamage(damage);
        } else {
            System.out.println(name + " попытался атаковать, но не нанес урона " + target.getName() + ".");
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " получает " + damage + " урона и остается с " + health + " здоровья.");
    }

    public boolean isAlive() {
        return health > 0;
    }

    protected String getName() {
        return name;
    }

    protected int getHealth() {
        return health;
    }

    protected int getMaxHealth() {
        return maxHealth;
    }

    protected void setHealth(int health) {
        this.health = health;
    }


    protected int calculateAttackModifier(Creature target) {
        return attack - target.defense + 1;
    }

    protected boolean isAttackSuccessful(int attackModifier) {
        int numDice = Math.max(attackModifier, 1);
        for (int i = 0; i < numDice; i++) {
            int roll = random.nextInt(DICE_SIDES) + 1;
            if (roll >= MIN_SUCCESS_ROLL) {
                return true;
            }
        }
        return false;
    }

    protected int calculateDamage() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    private boolean isValidAttributeValue(int value) {
        return value >= MIN_ATTACK_AND_DAMAGE_VALUE && value <= MAX_ATTACK_AND_DAMAGE_VALUE;
    }
}