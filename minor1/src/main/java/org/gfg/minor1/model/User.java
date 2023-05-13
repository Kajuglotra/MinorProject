package org.gfg.minor1.model;

import lombok.*;
import org.gfg.minor1.service.UserService;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updateOn;

    private String name;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(unique = true)
    private String mobile;

    private UserState status;

//    @OneToMany(mappedBy = "user")
//    private List<TransactionDetails> txns;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<ExpenseDetails> expenseDetails;

}
