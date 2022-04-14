package ru.maybeqeafie.jpalessons.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Department")
public class Department {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "name")
    public String name;
}
