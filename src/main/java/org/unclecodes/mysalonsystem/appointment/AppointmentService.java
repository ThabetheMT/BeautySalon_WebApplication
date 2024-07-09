package org.unclecodes.mysalonsystem.appointment;

import org.springframework.stereotype.Service;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    public AppointmentService(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public void addAppointment(Appointment appointment){
        appointment.setDate(creationDate());
        appointmentDAO.save(appointment);
    }

    private Date creationDate(){
        return new Date();
    }

    public List<Appointment> allAppointments(){
        return appointmentDAO.findAll();
    }

    public List<Appointment> clientsAppointments(String email){
        return appointmentDAO.findAppointmentsByClient_Email(email);
    }

    public List<Appointment> appointmentsToDo(String email){
        return appointmentDAO.findAppointmentsByStylist_Email(email);
    }

    public void cancelAppointments(String email){
        List<Appointment> appointments = appointmentDAO.findAppointmentsByClient_Email(email);
        appointmentDAO.deleteAll(appointments);
    }

    public int countAppointments(){
        return (int) appointmentDAO.count();
    }

    public Optional<Appointment> getAppointment(Integer id){
        return Optional.ofNullable(appointmentDAO.findById(id)
                .orElseThrow(()-> new User_NotFound_Exception("Appointment not found")));
    }
}
