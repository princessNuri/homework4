package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {270, 260, 250,280,1000,250,260,280};
    public static int[] heroesDamage = {25, 20, 15,0, 10, 15,20,20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic","Golem","Lucky","Berserk","Thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }


    public static void round() {
        round_number++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        medic();
        golem();
        lucky();
        berserk();
        thor();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefence);

    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _____________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] <= 0) {
                continue;
            }
            if (heroesHealth[i] - bossDamage < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth <= 0 || heroesHealth[i] <= 0) {
                continue;
            }
            if (heroesAttackType[i] == bossDefence) {
                Random random = new Random();
                int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                int criticalDamage = heroesDamage[i] * coeff;
                if (bossHealth - criticalDamage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - criticalDamage;
                }
                System.out.println("Critical damage: " + criticalDamage);
            } else {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }
    public static void medic(){
        for (int i = 0; i <heroesHealth.length ; i++) {
            if(heroesHealth[i]>0 && heroesHealth[i]<100){
                if(heroesHealth[3]>0 && heroesHealth[3]<100){
                    heroesHealth[3]=heroesHealth[3]-bossDamage;
                }
                else {
                    int drugs=40;
                    heroesHealth[i]=heroesHealth[i]+drugs;
                }

            }
            else if(heroesHealth[3]<0){
                heroesHealth[3]=0;
                heroesHealth[i]-=bossDamage;
            }

        }
    }
    public static void golem(){
        int golemSaved=bossDamage/5;
        int playersNumber=0;
        for (int i = 0; i <heroesHealth.length ; i++) {
            if(heroesHealth[i]>0){
                if (heroesHealth[4] > 0){
                    continue;
                }else {
                    playersNumber++;
                    heroesHealth[i] = heroesHealth[i] - (bossDamage - golemSaved);
                    heroesHealth[4] = heroesHealth[4] - bossDamage - (golemSaved * playersNumber);
                }
            }
            else if(heroesHealth[4]<0){
                heroesHealth[4]=0;
            }
        }
    }
    public static void lucky(){
        Random random = new Random();
        boolean luckyBoolean= random.nextBoolean();
        int luckyDamage=0;
        if(heroesHealth[5]>0 && luckyBoolean){
            heroesHealth[5]=heroesHealth[5]-luckyDamage;
        }
        else if(heroesHealth[5]<0){
            heroesHealth[5]=0;
        }
        else{
            heroesHealth[5]=heroesHealth[5]-bossDamage;
        }
    }
    public static void berserk(){
        int berserkDamage= bossDamage/2;
        heroesHealth[6]=heroesHealth[6]-berserkDamage;
        heroesDamage[6]=heroesDamage[6]+berserkDamage;
        if(heroesHealth[6]<0){
            heroesHealth[6]=0;
        }
    }
    public static void thor(){
        Random random =new Random();
        boolean thorBoolean = random.nextBoolean();
        if (heroesHealth[7]>0 && thorBoolean){
            bossDamage=0;
        }
        if(heroesHealth[7]<0){
            heroesHealth[7]=0;
        }
        else {
            bossDamage=50;
        }
    }



}

