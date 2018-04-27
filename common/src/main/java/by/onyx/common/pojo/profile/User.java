package by.onyx.common.pojo.profile;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Column(name = "register_date", nullable = false)
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;
    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;
    @Column(nullable = false)
    @Getter
    @Setter
    private String position;
    @Column
    @Getter
    @Setter
    private String cabinetNumber;
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String phoneNumber;
    @Column(nullable = false)
    @Getter
    @Setter
    private String password;
    @Column(name = "avatar")
    @Getter
    @Setter
    private String avatar;

    public User(){}


}
