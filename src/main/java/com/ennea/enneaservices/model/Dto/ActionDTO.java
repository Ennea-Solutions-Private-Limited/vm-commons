package com.ennea.enneaservices.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO implements Serializable {

    private int id;

    private String title;
}
