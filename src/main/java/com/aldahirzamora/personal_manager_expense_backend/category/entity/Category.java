package com.aldahirzamora.personal_manager_expense_backend.category.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long budget_Limit;

    @Column()
    private String icon;

    @Column()
    private String text_color;

    @Column()
    private String color;
}
