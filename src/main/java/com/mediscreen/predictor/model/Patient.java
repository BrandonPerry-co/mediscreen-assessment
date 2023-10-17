package com.mediscreen.predictor.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String id;
    private String address;
    private String dob;
    private String family;
    private String given;
    private String sex;
    private String phone;
}

