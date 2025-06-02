package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@EnableScheduling
public class SendMessage {

	@Autowired
	SimpMessagingTemplate sr;
	
	//@Scheduled(fixedDelay = 3000l)
	public Message3 send2() {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		Message3 m3=new Message3("KOIJIWA255", time);
		sr.convertAndSend("/message/con3",m3);
		System.out.println(time );
		return m3;
	}
	
	//@Scheduled(fixedDelay = 3000l)
	public String send3() {
		// TODO Auto-generated method stub
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		//System.out.println("hello"+time );
		return time;
	}
}
