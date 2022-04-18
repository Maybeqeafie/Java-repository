package ru.maybeqeafie.jpalessons.Entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
    Department department;

}
