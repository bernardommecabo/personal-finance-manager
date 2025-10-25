package com.finance.personal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    //@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private CategoryEntity category;
}
