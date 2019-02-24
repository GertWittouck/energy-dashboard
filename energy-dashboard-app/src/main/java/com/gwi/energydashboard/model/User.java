package com.gwi.energydashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "dashboard_user")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "GenerationType.AUTO")
    private Long id;

    @NotNull
    private String userName;

    private String firstName;

    private String lastName;

    @NotNull
    private String email;
}
