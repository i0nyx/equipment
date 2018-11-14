package by.onyx.common.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "cartridge_repair")
@Data
public class CartridgeRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "type_work")
    @Enumerated(EnumType.STRING)
    private TypeWork typeWork;

    @Column(name = "who")
    private String who;

    @Column(name = "part")
    private String part;

    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;
}
