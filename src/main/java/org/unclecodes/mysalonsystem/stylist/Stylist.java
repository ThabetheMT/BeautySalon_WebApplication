package org.unclecodes.mysalonsystem.stylist;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    @Column(
            unique = true
    )
    private String email;
    private String password;
}

