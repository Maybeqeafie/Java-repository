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
@Table(name = "Operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_buyer")
    public Users buyer;

    @Column(name = "admin_check")
    public Boolean adminOpinion;

    @Column(name = "user_check")
    public Boolean userOpinion;

    @Column(name = "name")
    public String name;

    @Column(name = "text")
    public String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    public Users user;

    @Column(name = "price")
    public int price;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "isreported")
    public Boolean isReported;
}
