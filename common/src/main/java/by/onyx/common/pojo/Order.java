package by.onyx.common.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "orders")
@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_start")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "date_end")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "whom")
    private String whom;

    @Column(name = "descriptions", nullable = false)
    private String description;

    @Column(name = "state", columnDefinition = "BIT default false")
    private boolean isState;

}
