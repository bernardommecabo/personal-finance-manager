package com.finance.personal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTORequest {
    private Long id;

    @NotBlank (message = "Category name must not be null")
    private String name;

}
