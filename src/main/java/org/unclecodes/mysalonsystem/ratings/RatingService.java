package org.unclecodes.mysalonsystem.ratings;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RatingService {

    private final RatingDAO ratingDAO;

    public RatingService(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    public void createRating(Rating rating){
//        List<Integer> id = new ArrayList<>();
//
//        for(Rating rating1: allRatings()){
//            id.add(rating1.getId());
//        }
//
//        Collections.sort(id);
//        if(!id.isEmpty()){
//            int newId =  id.getFirst() + 1;
//            id.add(newId);
//            rating.setId(newId);
//        }

        if(rating.getCreationDate() == null){
            rating.setCreationDate(new Date());
        }
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
        ratingDAO.deleteAll(ratingDAO.findRatingsByClient_Email(email));
    }

    public void deleteRatingsByStylistEmail(String email){
        ratingDAO.deleteRatingsByStylist_Email(email);
    }

    public double ratingPercentage(String email){
        int sum = 0;
        List<Rating> ratingList = findRatingsByStylist(email);
        for(Rating rating: ratingList){
            double rate = (double) rating.getRate() /5 *100;
            sum += rate;
        }

        return sum/ratingList.size();
    }
}
