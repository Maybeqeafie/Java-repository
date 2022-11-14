package ru.maybeqeafie.jpalessons.Entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Users")
public class Users  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "isAdmin")
    public Boolean isAdmin;

    @Column(name = "balance")
    public int balance;

    @Column(name = "countreport")
    public int countReport;

    @Column(name = "isban")
    public Boolean isBan;

    @Column(name = "isbanSales")
    public Boolean isBanSales;

}
