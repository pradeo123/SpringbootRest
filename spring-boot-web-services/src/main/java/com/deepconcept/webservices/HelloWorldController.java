package com.deepconcept.webservices;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// internationalization  
	//good.morning.message
	@GetMapping("/hello-world")
	public String retriveAllUser() {
		
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message",null,"Default Message", locale);
		//return "Hello World";
	}
}
