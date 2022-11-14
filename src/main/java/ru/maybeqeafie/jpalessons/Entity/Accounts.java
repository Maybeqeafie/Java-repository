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
@Table(name = "Accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    public String name;

    @Column(name = "text")
    public String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_users")
    public Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_buyer")
    public Users buyer;

    @Column(name = "price")
    public int price;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "isBuy")
    public Boolean isBuy;
}
