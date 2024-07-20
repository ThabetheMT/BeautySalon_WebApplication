package org.unclecodes.mysalonsystem.stylist;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stylist{
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

