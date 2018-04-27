package by.onyx.common.pojo;

import by.onyx.common.pojo.profile.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "support")
@EqualsAndHashCode
@ToString
public class Support {

    public enum SupportType{
        PRINTER, COMPUTER
    }

    public enum SupportStatus{
        PENDING, PROCESSING, FULFILLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Column(name = "support_type")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private SupportType supportType;
    @Column(name = "support_status")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private SupportStatus supportStatus;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date date;
    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;
    @Column(name = "cabinet")
    @Getter
    @Setter
    private String cabinet;
    @Column(name = "comment")
    @Getter
    @Setter
    private String comment;
    @Column(name = "urgently", columnDefinition = "BIT default false")
    @Getter
    @Setter
    private boolean isUrgently;
    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /*@Column(name = "status", columnDefinition = "BIT default false")
    @Getter
    @Setter
    private boolean isStatus;*/
    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "support", cascade = CascadeType.ALL)
    private ReceivedRepair receivedRepair;*/

    public Support(){}
}
