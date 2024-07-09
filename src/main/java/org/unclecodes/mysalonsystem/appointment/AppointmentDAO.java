package org.unclecodes.mysalonsystem.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Integer> {

      Appointment findAppointmentByClient_Email(String email) throws User_NotFound_Exception;
//   Optional<Appointment> findAppointmentByStylist_Email(String Email) throws User_NotFound_Exception;
      List<Appointment> findAppointmentsByClient_Email(String email);
      void deleteAppointmentsByClient_Email(String email);
      List<Appointment> findAppointmentsByStylist_Email(String email);
}
