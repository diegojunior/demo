package br.com.cielo;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cielo.json.Greeting;

@RestController
@RequestMapping("/greetings/v1/greeting")
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping("/ola")
	public HttpEntity<Greeting> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name))
				.withSelfRel());
		

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

}
