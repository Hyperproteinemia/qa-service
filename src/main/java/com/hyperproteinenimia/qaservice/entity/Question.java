package com.hyperproteinenimia.qaservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    @NotNull
    private String username;

    @NotNull
    @NotEmpty
    private String text;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Answer> answers = new HashSet<>();
}
