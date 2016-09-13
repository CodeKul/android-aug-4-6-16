package com.codekul.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

/**
 * Created by aniruddha on 13/9/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse {

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<Result> result = new ArrayList<>();

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "messages=" + messages +
                ", result=" + result +
                '}';
    }
}
