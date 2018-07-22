package by.onyx.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

import static by.onyx.common.pojo.EquipmentType.*;

@Entity
@Table(name = "equipments")
@Data
@ToString(exclude = "receivedRepair")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EquipmentType type;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "cabinet")
    private String cabinet;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<ReceivedRepair> receivedRepair;

    public static HashMap<EquipmentType, String> equipmentTypeHashMap = new HashMap<EquipmentType, String>() {{
        put(CARTRIDGE, "картридж");
        put(COMPUTER, "компьютер");
        put(NOTEBOOK, "ноутбук");
        put(PRINTER, "принтер");
        put(ACCESSORIES, "комплектующие");
        put(OTHER, "другое");
    }};

}
