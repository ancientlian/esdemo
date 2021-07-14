package com.example.esdemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemValue {

    private String testNumber;

    private String testDesp;

    private String lowLimit;

    private String upLimit;

    private String unit;

    private String value;

    private Boolean isOver;
}
