package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import jakarta.servlet.http.HttpSession;

public class MessageInterceptor extends HttpSessionHandshakeInterceptor{

	//@Autowired
	SimpMessageHeaderAccessor sa=SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		String se2=null;
		if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest
             = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest
              .getServletRequest().getSession(false);
            attributes.put("sessionId", session.getAttribute(".cred"));//session.getAttribute(".cred")
           if(session!=null&&sa!=null) {
        	   int i=0;
        	   
        	   //while(i<Con6.li.size()) {
        		  // if(Con6.li.get(i)!=session.getId()) {
        			   //sa.setSessionId(Con6.li.get(0));
        			   //se2=Con6.li.get(0);
        			//   break;
        		   //}
        		  //// i++;
        	   //}
        	   sa.setLeaveMutable(true);
        	   System.out.println("OK"+session.getAttribute(".cred").toString()+se2);
           }
        }
            return true;
	}
	
	

}
