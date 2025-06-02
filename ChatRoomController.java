package com.example.demo;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.ArrayList;

import java.util.List;
@Controller
public class ChatRoomController {

	public static SimpMessageHeaderAccessor bcz;
	@Autowired
	SimpMessagingTemplate sr;
	
	//@Autowired
	//SendMessage sm;
	
	@MessageMapping("/topic")//topic
	//@SubscribeMapping("/message1")
	//@SendTo("/topic/content")
	//@ResponseBody
	public void chat(@Payload Message1 message ) {
		
		List<SimpMessageHeaderAccessor> a =Con6.li;
		
		if(bcz!=null) {
			//sr.convertAndSendToUser(bcz.getSessionId(), "/topic/content"+bcz.getSessionId(), message
			//		,bcz.getMessageHeaders());
			sr.convertAndSend( "/topic/content"+bcz.getSessionId(), message
					);
			System.out.println("get0"+bcz.getSessionId());
		}
		//if(a.get(1)!=null) {
//		sr.convertAndSendToUser(a.get(1).getSessionId(), "/topic/content", message
//				,a.get(1).getMessageHeaders());
//		System.out.println("get1"+a.get(1).getSessionId());
		//}
		//return message;
		
	}
	
	@MessageMapping("/gyc")
	//@SubscribeMapping("/message1")
	@SendTo("/gyc/content")
	//@ResponseBody
	public Message1 chat2(@Payload Message1 message ) {
		return message;
		
	}
	
	
	
	
	@GetMapping(path="/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter serverSentEvents(){
	    SseEmitter emitter = new SseEmitter(5000l);
	    Thread th =new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
					
						emitter.send("Hello, MaxRoll!");
					//throw new Exception("aa");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
				
			}
		});
	    th.start();
	    // 发送消息
	    
	    return emitter;
	}

	
}
