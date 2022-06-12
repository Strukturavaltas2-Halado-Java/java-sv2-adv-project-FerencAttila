package com.training360.rollernestboxes.nesting.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNestingCommand {

    @NotBlank(message = "When add new notes on nesting it cannot be blank.")
    @Schema(description = "Notes on nesting", example = "On starling nest base")
    private String notesOnNesting;
}
