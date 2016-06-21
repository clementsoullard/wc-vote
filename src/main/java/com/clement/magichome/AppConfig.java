package com.clement.magichome;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AppConfig {
	
	

	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void doSomething() {
	  System.out.println("Coucou");
	}

}