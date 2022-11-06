package com.example.carrental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentType;
    private Date paymentDate;
    private String note;

    @OneToOne
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
