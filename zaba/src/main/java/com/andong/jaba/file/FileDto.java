package com.andong.jaba.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDto {
	private String uuid;
	private String fileName;
	private String contentType;
}
