package com.Erigeo.CaptureDex.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String subject;
    private  String body;
    private  String receiver;

}
