package com.aldahirzamora.personal_manager_expense_backend.expense.entity;

import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String sum_rest_sign;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Long user_owner;
}