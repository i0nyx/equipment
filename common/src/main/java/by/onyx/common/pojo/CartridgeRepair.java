package by.onyx.common.pojo;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

import static by.onyx.common.pojo.CartridgeRepair.TypeWork.FUELING;
import static by.onyx.common.pojo.CartridgeRepair.TypeWork.SUBSTITUTION;
import static by.onyx.common.pojo.CartridgeRepair.TypeWork.SUBSTITUTION_FUELING;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cartridge_repair")
public class CartridgeRepair {

    public enum TypeWork { //тип работы
        SUBSTITUTION, FUELING, SUBSTITUTION_FUELING
        //замена, заправка
    }
    public static HashMap<TypeWork, String> typeWorkHashMap = new HashMap<TypeWork, String>(){{
        put(SUBSTITUTION, "замена");
        put(FUELING, "заправка");
        put(SUBSTITUTION_FUELING, "замена и заправка");
    }};

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Getter
    @Setter
    @Column(name = "type_work")
    @Enumerated(EnumType.STRING)
    private TypeWork typeWork;
    @Getter
    @Setter
    @Column(name = "who")
    private String who;//кто выполнил
    @Getter
    @Setter
    @Column(name = "part")
    private String part;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private ReceivedRepair receivedRepair;

    public CartridgeRepair(){

    }


}
