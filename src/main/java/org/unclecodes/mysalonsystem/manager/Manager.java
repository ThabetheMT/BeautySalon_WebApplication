package org.unclecodes.mysalonsystem.manager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    @Column(
            unique = true
    )
    private String email;
    private String password;
    private String role;
}
