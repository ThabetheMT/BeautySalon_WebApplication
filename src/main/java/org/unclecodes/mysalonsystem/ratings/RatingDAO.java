package org.unclecodes.mysalonsystem.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingDAO extends JpaRepository<Rating, Integer> {

    // by client email
    List<Rating> findRatingsByClient_Email(String email);
    void deleteRatingsByClient_Email(String email);

    //by stylist email
    List<Rating> findRatingsByStylist_Email(String email);
    void deleteRatingsByStylist_Email(String email);

}
