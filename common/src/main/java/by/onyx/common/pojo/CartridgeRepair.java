package by.onyx.common.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

import static by.onyx.common.pojo.TypeWork.FUELING;
import static by.onyx.common.pojo.TypeWork.SUBSTITUTION;
import static by.onyx.common.pojo.TypeWork.SUBSTITUTION_FUELING;


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
    private String who;//кто выполнил

    @Column(name = "part")
    private String part;

    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;

    public static HashMap<TypeWork, String> typeWorkHashMap = new HashMap<TypeWork, String>() {{
        put(SUBSTITUTION, "замена");
        put(FUELING, "заправка");
        put(SUBSTITUTION_FUELING, "замена и заправка");
    }};

}
