package com.mediscreen.predictor.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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

    public CharSequence getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate currentDate = LocalDate.now();
        int ageInYears = (int) ChronoUnit.YEARS.between(birthDate, currentDate);
        return String.valueOf(ageInYears);
    }
}

