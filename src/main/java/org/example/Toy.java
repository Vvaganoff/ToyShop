package org.example;

public abstract class Toy implements toys{
    protected  int toyId;
    protected String toyName;
    protected int count;
    protected double frequency;



    @Override
    public abstract void toyfrequency(int sum);


}
