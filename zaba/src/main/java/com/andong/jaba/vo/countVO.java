package com.andong.jaba.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
@Getter
@Setter
@ToString
public class countVO implements Serializable{
    private int registerCount;
    private int trainerCount;
}
