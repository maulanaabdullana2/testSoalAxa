package com.testexa.testexa.model.entity;

import com.testexa.testexa.model.enums.PermissionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionid")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissiontype", nullable = false)
    private PermissionType permissionType;

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "roleid", foreignKey = @ForeignKey(name = "fk_permission_role"))
    private Role role;
}
