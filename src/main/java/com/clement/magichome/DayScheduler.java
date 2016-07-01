package com.clement.magichome;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DayScheduler {

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "0 1 1 * * MON-FRI")
	public void closeTv() throws IOException {
		SchedulerApplication.writeCountDown(-1);
	}

	/**
	 * Credited on Wednesday.
	 */
	@Scheduled(cron = "0 1 1 * * WED")
	public void giveCreditForWednesday() throws IOException {
		SchedulerApplication.writeCountDown(30 * 60);
	}

	/**
	 * Credited on Saturday.
	 */
	@Scheduled(cron = "0 1 1 * * SAT")
	public void giveCreditForWeekEnd() throws IOException {
		SchedulerApplication.writeCountDown(60 * 60);
	}

	/**
	 * Credited on Saturday.
	 */
	@Scheduled(cron = "0 1 1 * * SUN")
	public void giveCreditForWeekEndSunday() throws IOException {
		SchedulerApplication.writeCountDown(60 * 60);
	}


}
