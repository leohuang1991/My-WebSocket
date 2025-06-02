package com.example.demo;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.session.data.redis.RedisIndexedSessionRepository.RedisSession;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class Con6 {
	
	public static List<SimpMessageHeaderAccessor> li=new ArrayList<SimpMessageHeaderAccessor>();
	
	@PostMapping(path="/dlisten",consumes="application/json;charset=UTF-8",produces="application/json;charset=UTF-8")
	public Message1 dat(@RequestBody Message2 m,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie=new Cookie(".cred",m.getSessionname()); //建立採用Hash 雜湊值
		//設定Cookie的有效時間
		cookie.setMaxAge(60*10); //一天有效
		//Cookie 採用HttpOnly安全性
		cookie.setHttpOnly(true);
		//限制同一個Site有效 跟Response Header有關
		cookie.setPath("/");
		
		//透過Response加入這一個Cookie
		response.addCookie(cookie);
		//Session Enabled ??? 配合前端具有Cookie Container容器
		//進行後端狀端管理 採用Http Session(配合前端請求Request 看看要產生新的或者既有的Session)
		HttpSession session=request.getSession(); //有 問出既有的 沒有 就產生一個新的
		//將Session id 設定到Cookie
		session.setAttribute(".cred",m.getSessionname());
		System.out.println(session.getId());
		System.out.println(session.getAttribute(".cred"));
		//if(li.size()<2)
			//li.add(session.getId());
			//li.add(m.getSessionname());
			//System.out.println(li.get(0));
		SimpMessageHeaderAccessor a=SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		a.setSessionId(session.getId());
		a.setLeaveMutable(true);
		//MessageHeaders az2=a.getMessageHeaders();
		li.add(a);
		for (int i = 0; i <li.size(); i++) {
			System.out.println(li.get(i).getSessionId());
		}
		ChatRoomController.bcz=a;
		Message1 m1=new Message1();
		m1.setContent((String)session.getId());
		m1.setName("name");
		return m1;
	}
}