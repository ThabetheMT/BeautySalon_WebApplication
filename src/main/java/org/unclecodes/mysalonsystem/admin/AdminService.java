package org.unclecodes.mysalonsystem.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;

import java.util.List;

@Service
public class AdminService {

    private final AdminDao adminDao;

    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void addAdmin(Admin admin){
        adminDao.save(admin);
    }

    public void deleteAdmin(String email){
        adminDao.deleteAdminByEmail(email);
    }

    public ResponseEntity<Admin> getAdmin(String email){
        return ResponseEntity.ok(
                adminDao.findByEmail(email)
                        .orElseThrow(()-> new User_NotFound_Exception("User with email: " + email + " does not exist."))
        );
    }

    public void editAdmin(String email, Admin newAdmin){
        Admin admin = getAdmin(email).getBody();
        assert admin != null;
        admin.setPassword(newAdmin.getPassword());
        admin.setPassword(newAdmin.getPassword());
        adminDao.save(admin);
    }

    public List<Admin> allAdmins(){
        return adminDao.findAll();
    }
}
