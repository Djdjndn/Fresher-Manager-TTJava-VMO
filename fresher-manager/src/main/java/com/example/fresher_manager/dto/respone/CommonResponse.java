package com.example.fresher_manager.dto.respone;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse <T>{
    private int code;
    private String message;
    private T data;
}