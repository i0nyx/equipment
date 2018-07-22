package by.onyx.common.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "received_repair")
@Data
public class ReceivedRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "whose")
    private String whose;

    @Column(name = "comment")
    private String comment;

    @Column(name = "state", columnDefinition = "BIT default false")
    private boolean state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Equipment.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Support.class)
    private Support support;

    @OneToOne(mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private CartridgeRepair cartridgeRepair;

    @OneToOne(mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private ComputerRepair computerRepair;

    @OneToOne(mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private PrinterRepair printerRepair;

}
