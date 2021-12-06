package com.company.cursach;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String surname;
    private Position position;

    public Teacher(String surname, Position position) {
        this.surname = surname;
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return surname + " " + position.getName();
    }
}