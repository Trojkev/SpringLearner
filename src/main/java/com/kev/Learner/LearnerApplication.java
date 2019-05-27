package com.kev.Learner;

import com.kev.Learner.controllers.BuildIsoMessage;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;


@SpringBootApplication
public class LearnerApplication {

@Autowired
BuildIsoMessage buildIsoMessage;
	public static void main(String[] args) {
		SpringApplication.run(LearnerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runBuildIsoMessage() throws IOException, ISOException {
		buildIsoMessage.createMessage();
	}


}
