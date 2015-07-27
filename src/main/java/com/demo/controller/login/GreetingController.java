package com.demo.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.bean.HelloMessage;

@Controller
public class GreetingController {

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public HelloMessage greeting(HelloMessage message) throws Exception {
		Thread.sleep(3000); // simulated delay
		return message;
	}

	@RequestMapping(value = "/sendMessage")
	@ResponseBody
	public HelloMessage sendMessage() throws Exception {
		this.template.convertAndSend("/topic/greetings", new HelloMessage(
				(int) Math.random(), "This is Send From Server"));
		return new HelloMessage((int) Math.random(), "This is Send From Server");
	}

}
