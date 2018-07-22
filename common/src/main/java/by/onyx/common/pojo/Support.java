package by.onyx.common.pojo;

import by.onyx.common.pojo.profile.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "support")
@Data
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "support_type")
    @Enumerated(value = EnumType.STRING)
    private SupportType supportType;

    @Column(name = "support_status")
    @Enumerated(value = EnumType.STRING)
    private SupportStatus supportStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "cabinet")
    private String cabinet;

    @Column(name = "comment")
    private String comment;

    @Column(name = "urgently", columnDefinition = "BIT default false")
    private boolean isUrgently;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
