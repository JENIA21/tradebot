package org.example;

import java.sql.SQLException;

public class User {
    private int id;
    private String  name;
    private double rubles;
    private double dollars;
    private double euros;
    private int state = 0;

    public User(){}


    public User(int id, String name, double rubles, double dollars, double euros, int state) throws SQLException {
        this.id = id;
        this.name = name;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
        this.state = state;
    }

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }


    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public double getRubles() {return rubles;}

    public void setRubles(double rubles) {
        this.rubles = rubles;
    }

    public double getDollars() {return dollars;}

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }

    public double getEuros() {return euros;}

    public void setEuros(double euros) {
        this.euros = euros;
    }

    public int getState() {return state;}

    public void setState(int state) {this.state = state;}

    @Override
    public String toString() {
        return String.format("Имя: %s |Рубли: %s | Баксы: %s | Евро: %s" ,
                this.name, this.rubles, this.dollars, this.euros);
    }
}
