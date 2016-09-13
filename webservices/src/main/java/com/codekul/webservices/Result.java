package com.codekul.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by aniruddha on 13/9/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "alpha2_code")
    private String alphaTwoCode;

    @JsonProperty(value = "alpha3_code")
    private String alphaThreeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", alphaTwoCode='" + alphaTwoCode + '\'' +
                ", alphaThreeCode='" + alphaThreeCode + '\'' +
                '}';
    }
}
