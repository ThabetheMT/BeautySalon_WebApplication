package org.unclecodes.mysalonsystem.stylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.Optional;

@Repository
public interface StylistDAO extends JpaRepository<Stylist, Integer> {

    Optional<Stylist> findStylistByEmail(String email) throws User_NotFound_Exception;
    void deleteStylistByEmail(String email) throws User_NotFound_Exception;
}
