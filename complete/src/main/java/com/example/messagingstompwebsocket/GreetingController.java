package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	private final SimpMessagingTemplate template;

	public GreetingController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/rooms/{roomId}")
	public void greeting(@DestinationVariable String roomId, HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		Greeting greeting = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
		template.convertAndSend("/chatting-response/" + roomId, greeting);
	}
}
