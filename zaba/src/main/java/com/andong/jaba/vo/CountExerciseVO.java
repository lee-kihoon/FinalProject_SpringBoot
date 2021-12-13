package com.andong.jaba.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
@Getter
@Setter
@ToString
public class CountExerciseVO implements Serializable{
    private int id;
    private String Date;
    private String UserName;
    private int Squat;
    private int Plank;
    private int Pushup;
    private int Lunge;
    private int Situp;
}
