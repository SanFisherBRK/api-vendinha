package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private String cpf;

    @Column (nullable = false)
    private String cnpj;

    @Column (nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<Produto> produtos;
}
