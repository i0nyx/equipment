package by.onyx.common.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "printer_repair")
@EqualsAndHashCode
@ToString
public class PrinterRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Getter
    @Setter
    @Column(name = "description_of_work")
    private String descriptionWork;
    @Getter
    @Setter
    @Column(name = "who")
    private String who;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;

    public PrinterRepair(){}
}
