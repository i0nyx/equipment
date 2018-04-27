package by.onyx.common.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "orders")
@Entity
@EqualsAndHashCode
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @Column(name = "date_start")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate; //дата заказа
    @Getter
    @Setter
    @Column(name = "date_end")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate; //дата исполнения заказа
    @Getter
    @Setter
    @Column(name = "whom")
    private String whom; //для кого заказываемая запчасть
    @Getter
    @Setter
    @Column(name = "descriptions", nullable = false)
    private String description;
    @Getter
    @Setter
    @Column(name = "state", columnDefinition = "BIT default false")
    private boolean isState;


    public Order(){}

}
