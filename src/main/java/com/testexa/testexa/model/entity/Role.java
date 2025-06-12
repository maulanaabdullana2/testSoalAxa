package com.testexa.testexa.model.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long id;
    @Column(name = "rolename", nullable = false, length = 50)
    private String roleName;
}
