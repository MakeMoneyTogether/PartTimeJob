package com.partjob.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WeiChatController {
	@RequestMapping("MP_verify_pfWfJsZAFlMS4TN6.txt")
	public ResponseEntity<byte[]> weicheck() throws IOException{
		String path = "/root/wechat/MP_verify_pfWfJsZAFlMS4TN6.txt";
		File file=new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName="MP_verify_pfWfJsZAFlMS4TN6.txt";
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}
}
