package com.testexa.testexa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testexa.testexa.model.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
