package org.unclecodes.mysalonsystem.client;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

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
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
}
