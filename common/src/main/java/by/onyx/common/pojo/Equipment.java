package by.onyx.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

import static by.onyx.common.pojo.Equipment.EquipmentType.*;

@Entity
@Table(name = "equipments")
@ToString
@EqualsAndHashCode
public class Equipment {

    public enum EquipmentType{
        CARTRIDGE, PRINTER, COMPUTER, NOTEBOOK, ACCESSORIES, OTHER
    }
    public static HashMap<EquipmentType, String> equipmentTypeHashMap = new HashMap<EquipmentType, String>(){{
        put(CARTRIDGE, "картридж");
        put(COMPUTER, "компьютер");
        put(NOTEBOOK, "ноутбук");
        put(PRINTER, "принтер");
        put(ACCESSORIES, "комплектующие");
        put(OTHER, "другое");
    }};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    private String brand;
    @Getter
    @Setter
    private String model;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EquipmentType type;
    @Getter
    @Setter
    @Column(name = "code", unique = true)
    private String code;
    @Getter
    @Setter
    @Column(name = "cabinet")
    private String cabinet;
    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<ReceivedRepair> receivedRepair;

    public Equipment(){}
}
