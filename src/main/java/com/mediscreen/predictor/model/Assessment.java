package com.mediscreen.predictor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {
    private int patId;
    private int age;
    private String assessment;
}
