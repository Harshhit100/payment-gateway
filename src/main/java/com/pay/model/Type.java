package com.pay.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"name"})
public class Type {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "type_seq_gen"
    )
    @SequenceGenerator(
            name = "type_seq_gen",
            sequenceName = "type_seq",
            allocationSize = 5
    )
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50)
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setType(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setType(null);
    }
}
