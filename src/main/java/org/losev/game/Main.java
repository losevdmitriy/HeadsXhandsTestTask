package org.losev.game;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Игрок", 20, 15, 150, 10, 20);
        Monster monster = new Monster("Монстр", 19, 12, 200, 8, 23);

        System.out.println("Бой начинается!");
        System.out.println();

        while (player.isAlive() && monster.isAlive()) {
            player.attack(monster);
            if (monster.isAlive()) {
                monster.attack(player);
            }
            player.heal();
            System.out.println();
        }

        if (player.isAlive()) {
            System.out.println("Игрок победил!");
        } else {
            System.out.println("Монстр победил!");
        }
    }
}
