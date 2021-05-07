package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 250, 550, 190, 210, 220};
    public static String[] heroesNames = {"Lu Kang ", "jax ", "Scorpion", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int[] heroesStrike = {20, 15, 25, 0, 5, 10, 15, 20};
    public static boolean isStunds;
    public static String bossName = "Shao Khan ";
    public static int bossHealth = 1500;
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
        golemsHits();
        luckyHits();
        berserkHits();
        thorHits();
        isStunds = false;
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

    public static void healingHeroes() {
        Random randomMedic = new Random();
        int help = randomMedic.nextInt(100) + 50;
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] != heroesHealth[3]) {
                    heroesHealth[i] += help;
                    System.out.println("Медик вылечил " + heroesNames[i] + " На " + help);
                    break;
                }
            }
        }
    }

    public static void golemsHits() {
        if (heroesHealth[4] > 100) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] != heroesHealth[4]){
                    heroesHealth[i] += bossStrike / 5;
                    heroesHealth[4] -= bossStrike / 5;
                }
            }
        }
    }

    public static void luckyHits(){
        Random random = new Random();
        int randomLucky = random.nextInt(2);
        if (heroesHealth[5] > 0){
            if (randomLucky == 1){
                heroesHealth[5] += bossStrike;
                System.out.println(heroesNames[5] + " Уклонился от удара " + bossName);
            }
        }
    }

    public static void berserkHits (){
        if (heroesHealth[6] > 0 && !isStunds){
            heroesHealth[6] += bossStrike / 2;
            heroesStrike[6] += bossStrike / 2;
            System.out.println("Контер атака берсерка");
        }
    }

    public static void thorHits (){
        Random random = new Random();
        int randomThor = random.nextInt();
        if (randomThor == 1 && heroesHealth[7] > 0){
            bossStrike = 0;
            isStunds = false;
            System.out.println(heroesNames[7] + "потрясающий босс");
        } else {
            bossStrike = 100;
            isStunds = false;
            System.out.println(heroesNames[7] + " не оглушил удар");
        }
    }
}

