package org.unclecodes.mysalonsystem.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Integer> {

      //to get an appointment by client's email
      Appointment findAppointmentByClient_Email(String email) throws User_NotFound_Exception;

      //client viewing his/her appointments
      List<Appointment> findAppointmentsByClient_Email(String email);

      //client to cancel appointment
      void deleteAppointmentsByClient_Email(String email);

      //Appointments to be attended by a particular stylist
      List<Appointment> findAppointmentsByStylist_Email(String email);

      //pending appointments
      @Query("SELECT a FROM Appointment a WHERE a.isAttended = false ORDER BY a.date ASC")
      List<Appointment> pendingAppointments();

      //attended appointments
      @Query("SELECT a FROM Appointment a WHERE a.isAttended = true ORDER BY a.date ASC")
      List<Appointment> attendedAppointments();


}
