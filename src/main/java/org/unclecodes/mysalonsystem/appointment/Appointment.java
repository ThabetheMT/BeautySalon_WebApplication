package org.unclecodes.mysalonsystem.appointment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unclecodes.mysalonsystem.client.Client;
import org.unclecodes.mysalonsystem.stylist.Stylist;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @SequenceGenerator(
            name = "sequence_appointment",
            sequenceName = "sequence_appointment",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_appointment"
    )
    private Integer id;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            referencedColumnName = "id"
    )
    private Stylist stylist;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            referencedColumnName = "id"
    )
    private Client client;
    private String type;
    private Date date;
    private boolean isAttended;
}
