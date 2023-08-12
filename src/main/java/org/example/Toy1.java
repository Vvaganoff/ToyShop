package org.example;

import java.util.Comparator;
import java.util.Scanner;

public class Toy1 extends Toy implements Comparable<Toy1> {


    public Toy1(int toyId, String toyName, int count) {
        this.toyId  = toyId;
        this.toyName = toyName;
        this.count = count;
    }

    public Toy1(int toyId, String toyName, int count, double frequency) {
        this.toyId  = toyId;
        this.toyName = toyName;
        this.count = count;
        this.frequency = frequency;
    }

    public static Toy1 NewToy(Scanner scaner, int id){
        System.out.println("Введите новую игрушку:");
        int toyId = id;
        System.out.println("Введите название:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("Введите количество:");
        int count = Integer.parseInt(scanner.next());
        Toy1 toy1 = new Toy1(toyId, name, count);
        return toy1;
    }

    public String getToyName(){
        return this.toyName;
    }

    public int getCount(){
        return this.count;
    }

    public int getToyId(){
        return this.toyId;
    }

    public void setToyName(String toyName){
        this.toyName = toyName;
    }

    public void setToyId(int toyId){
        this.toyId = toyId;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setFrequency(double frequency) {this.frequency = frequency;}



    public void toyfrequency(int sum) {
        double countDouble = this.count;
        double sumDouble = sum;
        this.frequency = countDouble / sumDouble* 100 ;
    }

    @Override
    public String toString() {
        return  toyId +
                " " + toyName + ", количество: " + count +
                ", частота: " + frequency;
    }

    @Override
    public int compareTo(Toy1 o) {
        int difference = (int) (frequency*10000 - o.frequency*10000);
        return difference;
    }
}
