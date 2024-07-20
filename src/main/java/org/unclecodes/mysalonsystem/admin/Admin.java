package org.unclecodes.mysalonsystem.admin;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @SequenceGenerator(
            name = "sequence_client",
            sequenceName = "sequence_client",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_client"
    )
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
