package ru.maybeqeafie.jpalessons.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Personal")
public class Personal {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "salary")
    public int salary;

    @Column(name = "age")
    public int age;
}
