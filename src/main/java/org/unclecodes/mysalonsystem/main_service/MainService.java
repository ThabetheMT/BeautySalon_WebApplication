package org.unclecodes.mysalonsystem.main_service;

import jakarta.persistence.Column;
import org.springframework.stereotype.Service;
import org.unclecodes.mysalonsystem.User.User;
import org.unclecodes.mysalonsystem.admin.Admin;
import org.unclecodes.mysalonsystem.admin.AdminService;
import org.unclecodes.mysalonsystem.appointment.Appointment;
import org.unclecodes.mysalonsystem.appointment.AppointmentService;
import org.unclecodes.mysalonsystem.client.Client;
import org.unclecodes.mysalonsystem.client.ClientService;
import org.unclecodes.mysalonsystem.ratings.Rating;
import org.unclecodes.mysalonsystem.ratings.RatingService;
import org.unclecodes.mysalonsystem.stylist.Stylist;
import org.unclecodes.mysalonsystem.stylist.StylistService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MainService {

    private final StylistService stylistService;
    private final ClientService clientService;
    private final AppointmentService appointmentService;
    private final RatingService ratingService;
    private final AdminService adminService;

    public MainService(StylistService stylistService,
                       ClientService clientService,
                       AppointmentService appointmentService,
                       RatingService ratingService,
                       AdminService adminService) {
        this.stylistService = stylistService;
        this.clientService = clientService;
        this.appointmentService = appointmentService;
        this.ratingService = ratingService;
        this.adminService = adminService;
    }

    //client
    public void addClient(Client client){
        clientService.addClient(client);
    }

    public Client getClient(String email){
        return clientService.getClient(email).getBody();
    }

    public Client getClient(Long id){
        return clientService.getClient(id).getBody();
    }

    public List<Client> allClients(){
        return clientService.allClients();
    }

    public void removeClient(String email){
        clientService.deleteClient(email);
    }

    //Stylist
    public void saveStylist(Stylist stylist){
        stylistService.saveStylist(stylist);
    }

    public Stylist getStylist(String email){
        return stylistService.getStylistByEmail(email).getBody();
    }

    public Optional<Stylist> getStylist(Integer id){
        return stylistService.getStylistByEmail(id);
    }

    public List<Stylist> allStylist(){
        return stylistService.allStylists();
    }

    public void removeStylist(String email){
        stylistService.deleteStylistByEmail(email);
    }

    //appointments
    public void addAppointment(Appointment appointment){
        appointmentService.addAppointment(appointment);
    }

    public List<Appointment> appointmentsTodo(String email){
        return appointmentService.appointmentsToDo(email);
    }

    public List<Appointment> clientAppointments(String email){
        return appointmentService.clientsAppointments(email);
    }

    public List<Appointment> allAppointments(){
        return appointmentService.allAppointments();
    }

    public void cancelAppointment(String email){
        appointmentService.cancelAppointments(email);
    }

    public int countAppointments(){
        return appointmentService.countAppointments();
    }

    public Optional<Appointment> getAppointment(Integer id){
        return appointmentService.getAppointment(id);
    }

    public void attendClient(Integer id){
        appointmentService.attendClient(id);
    }

    public List<Appointment> attendedAppointments(){
        return appointmentService.attendedAppointment();
    }

    public List<Appointment> pendingAppointments(){
        return appointmentService.pendingAppointment();
    }

    public int countPendingAppointment(){
        return appointmentService.pendingAppointment().size();
    }

    public int countAttendedAppointments(){
        return appointmentService.attendedAppointment().size();
    }

    //ratings
    public void createRating(Rating rating){
        ratingService.createRating(rating);
    }

    public List<Rating> allRatings(){
        return ratingService.allRatings();
    }

    public int countRatings(){
        return allRatings().size();
    }

    public List<Rating> findRatingByClientEmail(String email){
        return  ratingService.findRatingsByClient(email);
    }

    public List<Rating> findRatingByStylistEmail(String email){
        return  ratingService.findRatingsByStylist(email);
    }

    public void deleteRatings(){
        ratingService.deleteRatings();
    }

    public void deleteRatingsByClientEmail(String email){
        ratingService.deleteRatingsByClientEmail(email);
    }

    public void deleteRatingsByStylistEmail(String email){
        ratingService.deleteRatingsByStylistEmail(email);
    }

    public double averageRating(String email){
        return ratingService.ratingPercentage(email);
    }

    //Admin
    public void addAdmin(Admin admin){
        adminService.addAdmin(admin);
    }

    public Admin getAdmin(String email){
        return adminService.getAdmin(email).getBody();
    }

    public void deleteAdmin(String email){
        adminService.deleteAdmin(email);
    }

    public void editAdmin(String email, Admin admin){
        adminService.editAdmin(email, admin);
    }

    public List<Admin> allAdmins(){
        return adminService.allAdmins();
    }

    //adding a user
    public void addUser(User user){
        String firstName = user.firstName();
        String lastName = user.lastName();
        String email = user.email();
        String phone = user.phone();;
        String password = user.password();
        String role = user.role();

        switch (role){
            case "admin" : adminService.addAdmin(Admin.builder()
                            .email(email)
                            .firstName(firstName)
                            .lastName(lastName)
                            .role(role)
                            .password(password)
                            .phone(phone)
                    .build());
            break;
            case "client" : addClient(Client.builder()
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .role(role)
                    .password(password)
                    .phone(phone)
                    .build());
            break;
            case "stylist" : saveStylist(Stylist.builder()
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .role(role)
                    .password(password)
                    .phone(phone)
                    .build());
                break;
        }
    }

    //user logging in
    public String userLog(User user){
        String email = user.email();
        String found = "";

        if(adminService.getAdmin(email).getBody() != null){
            found = adminService.getAdmin(email).getBody().getRole();
        }
        if(clientService.getClient(email).getBody() != null ){
            found = adminService.getAdmin(email).getBody().getRole();
        }
        if(stylistService.getStylistByEmail(email).getBody() != null){
            found = adminService.getAdmin(email).getBody().getRole();
        }

        return found;
    }

}

