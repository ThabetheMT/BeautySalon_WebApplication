package org.unclecodes.mysalonsystem.main_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unclecodes.mysalonsystem.appointment.Appointment;
import org.unclecodes.mysalonsystem.client.Client;
import org.unclecodes.mysalonsystem.main_service.MainService;
import org.unclecodes.mysalonsystem.stylist.Stylist;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salon")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
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
        return ResponseEntity.ok(mainService.getClient(email).get());
    }

    @GetMapping("/client-id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(mainService.getClient(id).get());
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
        return ResponseEntity.ok(mainService.getStylist(email).get());
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

}
