package com.example.practice.entity;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    // define fields

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="wage")
    private String wage;

    public Employee() {

    }

    public Employee(int id, String name, String wage) {
        this.id = id;
        this.name = name;
        this.wage = wage;
    }

    public Employee(String wage) {
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wage='" + wage + '\'' +
                '}';
    }
}
