package org.unclecodes.mysalonsystem.ratings;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    @SequenceGenerator(
            name = "sequence_rating",
            sequenceName = "sequence_rating",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_rating"
    )
    private Integer id;
    @Column(
            length = 1
    )
    private int rate;
    private String review;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            referencedColumnName = "email"
    )
    private Client client;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            referencedColumnName = "email"
    )
    private Stylist stylist;
    private Date creationDate;

}
