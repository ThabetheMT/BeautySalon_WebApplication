package org.unclecodes.mysalonsystem.stylist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.List;
import java.util.Optional;

@Service
public class StylistService {

    private final StylistDAO stylistDAO;

    public StylistService(StylistDAO stylistDAO) {
        this.stylistDAO = stylistDAO;
    }

    public void saveStylist(Stylist stylist){
        stylistDAO.save(stylist);
    }

    public List<Stylist> allStylists(){
        return stylistDAO.findAll();
    }

    public Optional<Stylist> getStylistByEmail(String email){
        return  Optional.ofNullable(stylistDAO.findStylistByEmail(email)
                .orElseThrow(() -> new User_NotFound_Exception(" Stylist with the email: "+email+ " " +
                                "does not exist")));
    }

    public Optional<Stylist> getStylistByEmail(Integer id){
        return  Optional.ofNullable(stylistDAO.findById(id)
                .orElseThrow(() -> new User_NotFound_Exception(" Stylist with the email: "+id+ " " +
                        "does not exist")));
    }

    public void deleteStylistByEmail(String email){
        Stylist stylist = stylistDAO.findStylistByEmail(email).orElseThrow(
                () -> new User_NotFound_Exception( "Stylist with the email: "+email+ " " +
        "does not exist")
        );
        stylistDAO.deleteStylistByEmail(email);
    }
}
