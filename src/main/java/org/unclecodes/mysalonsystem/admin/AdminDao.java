package org.unclecodes.mysalonsystem.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
    void deleteAdminByEmail(String email);
    Optional<Admin> findByEmail(String email);
}
