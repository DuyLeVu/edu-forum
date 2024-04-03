package com.edu.forum.application.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CreateCategoryRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    private String description;
//    private byte[] message;
}
