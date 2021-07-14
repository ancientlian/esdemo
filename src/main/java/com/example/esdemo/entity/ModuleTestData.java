package com.example.esdemo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.HashMap;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "product_db")
public class ModuleTestData {

    @Id
    private String id;
    @Field
    private String tpId;
    @Field
    private String testTime;
    @Field
    private String sbin;
    @Field
    private String passFail;
    @Field
    private String barcode;
    @Field
    private String site;
    @Field
    private String firstFail;
    @Field
    private HashMap<String, ItemValue> itemValue;


}
