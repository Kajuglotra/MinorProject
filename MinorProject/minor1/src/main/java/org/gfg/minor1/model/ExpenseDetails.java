package org.gfg.minor1.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name ="expense")
public class ExpenseDetails {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String createdBy;

    @Column(length = 20)
    private String expenseType;

    @CreationTimestamp
    private Date creationTime;

    @OneToMany(mappedBy ="expenseDetails")
    private List<TransactionDetails> listOfTxn;


}
