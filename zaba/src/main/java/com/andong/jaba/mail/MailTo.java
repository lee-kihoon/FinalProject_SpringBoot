package com.andong.jaba.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailTo {

    private String address;
    private String FromAddress;
    private String title;
    private String message;
    
    private String fileName;
    private String filePath;
    
}