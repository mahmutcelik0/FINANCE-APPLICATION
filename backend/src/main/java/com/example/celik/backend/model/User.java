package com.example.celik.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID,generator = "UUID")
//    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "user_id")
    private Long id;

    private String name;
    @Column(name = "email", unique=true)
    private String email;
    private LocalDate birth;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER) // table in generate edildiği yerde eager olması gerekli - lazily exception atar diğer durumda
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "wealth_id")
    )
    private Set<Wealth> wealths;

}
