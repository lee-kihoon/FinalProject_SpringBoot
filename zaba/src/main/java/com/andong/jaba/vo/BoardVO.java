package com.andong.jaba.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
@Getter
@Setter
@ToString
public class BoardVO implements Serializable{
    private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
    private String tdate;
    private String roomcode;
}
