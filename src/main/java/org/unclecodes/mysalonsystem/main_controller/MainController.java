package org.unclecodes.mysalonsystem.main_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.unclecodes.mysalonsystem.appointment.Appointment;
import org.unclecodes.mysalonsystem.client.Client;
import org.unclecodes.mysalonsystem.client.ClientService;
import org.unclecodes.mysalonsystem.main_service.MainService;
import org.unclecodes.mysalonsystem.ratings.Rating;
import org.unclecodes.mysalonsystem.stylist.Stylist;

import java.util.List;

//@Controller
//@RestController
//@RequestMapping("/api/v1/salon")
public class MainController {

    private final MainService mainService;
    private final ClientService clientService;

    public MainController(MainService mainService, ClientService clientService) {
        this.mainService = mainService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("clients", clientService.allClients());
        modelAndView.setViewName("index.html");
        return modelAndView ;
    }

    //client
    @GetMapping("/all-clients")
    public List<Client> allClients(){
        return mainService.allClients();
    }

    @PostMapping("/add-client")
    public void addClient(@RequestBody Client client){
        mainService.addClient(client);
    }

    @GetMapping("/client/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email){
        return ResponseEntity.ok(mainService.getClient(email));
    }

    @GetMapping("/client-id/{id}")
    public Client getClientById(@PathVariable Long id){
        return ResponseEntity.ok(mainService.getClient(id)).getBody();
    }

    @DeleteMapping("/remove-client/{email}")
    public void removeClient(@PathVariable String email){
       mainService.removeClient(email);
    }

    //Stylist
    @GetMapping("/all-stylists")
    public List<Stylist> allStylist(){
        return mainService.allStylist();
    }

    @PostMapping("/add-stylist")
    public void addStylist(@RequestBody Stylist stylist){
        mainService.saveStylist(stylist);
    }

    @GetMapping("/stylist/{email}")
    public ResponseEntity<Stylist> getStylist(@PathVariable String email){
        return ResponseEntity.ok(mainService.getStylist(email));
    }

    @GetMapping("/stylist-id/{id}")
    public ResponseEntity<Stylist> getStylist(@PathVariable Integer id){
        return ResponseEntity.ok(mainService.getStylist(id).get());
    }

    @DeleteMapping("/remove-stylist/{email}")
    public void removeStylist(@PathVariable String email){
        mainService.removeStylist(email);
    }

    //Appointments
    @PostMapping("/add-appointment")
    public void addAppointment(@RequestBody Appointment appointment){
        mainService.addAppointment(appointment);
    }

    @GetMapping("/all-appointments")
    public List<Appointment> allAppointments(){
        return mainService.allAppointments();
    }

    @GetMapping("/count")
    public int countAppointments(){
        return mainService.countAppointments();
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Integer id){
        return ResponseEntity.ok(mainService.getAppointment(id).get());
    }

    @GetMapping("/appointments-todo/{email}")
    public List<Appointment> appointmentsTodo(@PathVariable String email){
        return mainService.appointmentsTodo(email);
    }

    @GetMapping("/client-appointments/{email}")
    public List<Appointment> clientAppointments(@PathVariable String email){
        return mainService.clientAppointments(email);
    }

    @DeleteMapping("/cancel-appointment/{email}")
    public void cancelAppointments(@PathVariable String email){
        mainService.cancelAppointment(email);
    }

    @PutMapping("/attended-client/{id}")
    public void attendedClient(@PathVariable Integer id){
         mainService.attendClient(id);
    }

    @GetMapping("/pending-appointments")
    public List<Appointment> pendingAppointments(){
        return mainService.pendingAppointments();
    }

    @GetMapping("/attended-appointments")
    public List<Appointment> attendedAppointments(){
        return mainService.attendedAppointments();
    }

    @GetMapping("/count-pending")
    public int countPendingAppointment(){
        return mainService.countPendingAppointment();
    }

    @GetMapping("/count-attended")
    public int countAttendedAppointment(){
        return mainService.countAttendedAppointments();
    }

    //ratings
    @PostMapping("/add-rating")
    public void createRating(@RequestBody Rating rating){
        mainService.createRating(rating);
    }

    @GetMapping("/all-ratings")
    public List<Rating> allRatings(){
        return mainService.allRatings();
    }

    @GetMapping("/client-ratings/{email}")
    public List<Rating> findRatingByClientEmail(@PathVariable String email){
        return mainService.findRatingByClientEmail(email);
    }

    @GetMapping("/stylist-ratings/{email}")
    public List<Rating> findRatingsByStylistEmail(@PathVariable String email){
        return mainService.findRatingByStylistEmail(email);
    }

    @GetMapping("/count-ratings")
    public int countRatings(){
        return mainService.countRatings();
    }

    @DeleteMapping("/delete-ratings")
    public void deleteRatings(){
        mainService.deleteRatings();
    }

    @DeleteMapping("/delete-ratings/{email}")
    public void deleteRatingsByClientEmail(@PathVariable String email){
        mainService.deleteRatingsByClientEmail(email);
    }

    @GetMapping("/average-rating/{email}")
    public double averageRating(@PathVariable String email){
        return mainService.averageRating(email);
    }

}
