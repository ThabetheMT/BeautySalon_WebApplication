package org.unclecodes.mysalonsystem.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String email) throws User_NotFound_Exception;
    void deleteClientByEmail(String email) throws User_NotFound_Exception;
}
