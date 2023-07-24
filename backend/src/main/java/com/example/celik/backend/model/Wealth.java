package com.example.celik.backend.model;

import com.example.celik.backend.constants.LOCATION;
import com.example.celik.backend.constants.UNIT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "WEALTH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wealth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "wealth_id")
    private Long id;

    private String name;

    private String longName;

    @Enumerated
    private UNIT unit;

    @ManyToMany(mappedBy = "wealths",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

}
