package by.onyx.common.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode
@ToString
@Entity
@Table(name = "received_repair")
public class ReceivedRepair {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    @Getter
    @Setter
    @Column(name = "whose")
    private String whose;
    @Getter
    @Setter
    @Column(name = "comment")
    private String comment;
    @Getter
    @Setter
    @Column(name = "state", columnDefinition = "BIT default false")
    private boolean state;
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Equipment.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Support.class)
    private Support support;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private CartridgeRepair cartridgeRepair;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private ComputerRepair computerRepair;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "receivedRepair", cascade = CascadeType.REMOVE)
    private PrinterRepair printerRepair;

    public ReceivedRepair(){}

}
