package org.unclecodes.mysalonsystem.ratings;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingDAO ratingDAO;

    public RatingService(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    public void createRating(Rating rating){
        ratingDAO.save(rating);
    }

    public List<Rating> allRatings(){
        return ratingDAO.findAll();
    }

    public List<Rating> findRatingsByClient(String email){
        return ratingDAO.findRatingsByClient_Email(email);
    }

    public List<Rating> findRatingsByStylist(String email){
        return ratingDAO.findRatingsByStylist_Email(email);
    }

    public void deleteRatings(){
        ratingDAO.deleteAll();
    }

    public void deleteRatingsByClientEmail(String email){
        ratingDAO.deleteRatingsByClient_Email(email);
    }

    public void deleteRatingsByStylistEmail(String email){
        ratingDAO.deleteRatingsByStylist_Email(email);
    }
}
