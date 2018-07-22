package by.onyx.common.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "computer_repair")
@Data
public class ComputerRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description_of_work")
    private String descriptionWork;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "who")
    private String who;

    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;

}
