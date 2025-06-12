package com.testexa.testexa.model.entity;

import com.testexa.testexa.model.enums.PermissionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Permission", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PermissionType", nullable = false)
    private PermissionType permissionType;

    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", foreignKey = @ForeignKey(name = "fk_permission_role"))
    private Role role;
}
