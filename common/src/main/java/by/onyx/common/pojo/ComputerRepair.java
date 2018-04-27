package by.onyx.common.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "computer_repair")
@ToString
@EqualsAndHashCode
public class ComputerRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Getter
    @Setter
    @Column(name = "description_of_work")
    private String descriptionWork;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Getter
    @Setter
    @Column(name = "who")
    private String who;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;

    public ComputerRepair() {
    }
}
