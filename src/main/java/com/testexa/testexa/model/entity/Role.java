package com.testexa.testexa.model.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private Long id;
    @Column(name = "RoleName", nullable = false, length = 50)
    private String roleName;
}
