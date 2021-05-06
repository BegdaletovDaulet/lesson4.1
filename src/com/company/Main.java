package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 250};
    public static String[] heroesNames = {"Lu Kang ", "jax ", "Scorpion", "Medic"};
    public static int[] heroesStrike = {20, 15, 25, 0};

    public static String bossName = "Shao Khan ";
    public static int bossHealth = 650;
    public static int bossStrike = 60;
    public static int roundNumber = 0;

    public static String bossDefence = "";

    public static void main(String[] args) {
        printStatistics();
        System.out.println("---------The game started----------");
        while (!isGameFinished()) {
            round();
        }
    }


    public static void round() {
        roundNumber++;
        System.out.println("----Round " + roundNumber + " ----");
        bossDefence = getSuperStrikeHero();
        bossHits();
        heroesHits();
        printStatistics();
        healingHeroes();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " + "Mortal Kombat finished");
            return true;
        }
        boolean allHeroesDead = true;

        for (int heroHelth : heroesHealth) {
            if (heroHelth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName + " Won!! Mortal Kombat Finished");
        }
        return allHeroesDead;

    }


    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesStrike.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesNames[i]) {
                    bossHealth = bossHealth - heroesStrike[i] * coeff;
                    System.out.println("Super strike damage " + bossDefence + " " + (heroesStrike[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesStrike[i];
                }

            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossStrike;
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
        }
    }

    public static String getSuperStrikeHero() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }

    public static void printStatistics() {
        System.out.println(bossName + "= health " + bossHealth + " Strike [" + bossStrike + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " = health " + heroesHealth[i] + " Strike [" + heroesStrike[i] + "]");

        }
    }

    public static void healingHeroes(){
        Random randomMedic = new Random();
        int help = randomMedic.nextInt(100) + 50;
        if (heroesHealth[3] > 0){
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] != heroesHealth[3]){
                    heroesHealth[i] += help;
                    System.out.println("Медик вылечил " + heroesNames[i] + " На " + help);
                    break;
                }
            }
        }
    }
}

