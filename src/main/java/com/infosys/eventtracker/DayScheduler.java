package com.clement.magichome;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.clement.magichome.service.BonPointDaoImpl;

@Configuration
@EnableScheduling
/**
 * This class credit some minutes for the TV.
 * 
 * @author Clément
 *
 */
@Repository
public class DayScheduler {

	static final Logger LOG = LoggerFactory.getLogger(DayScheduler.class);

	@Resource
	BonPointDaoImpl bonPointDaoImpl;

	@Resource
	FileService fileService;

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "0 1 1 * * MON-FRI")
	public void closeTv() throws IOException {
		fileService.writeCountDown(-1);
	}

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "1 0 11 * * MON-TUE", zone = "Europe/Paris")
	public void creditTvVacances() throws IOException {
		Integer minutes = bonPointDaoImpl.pointToDistribute(-60, 30);
		fileService.writeCountDown(60 * (60 + minutes));
		bonPointDaoImpl.removePunition(minutes);
	}

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "1 0 11 * * THU-FRI", zone = "Europe/Paris")
	public void creditTvVacances2() throws IOException {
		Integer minutes = bonPointDaoImpl.pointToDistribute(-60, 30);
		fileService.writeCountDown(60 * (60 + minutes));
		bonPointDaoImpl.removePunition(minutes);
	}

	/**
	 * Credited on Wednesday.
	 */
	@Scheduled(cron = "0 1 14 * * WED", zone = "Europe/Paris")
	public void giveCreditForWednesday() throws IOException {
		Integer minutes = bonPointDaoImpl.pointToDistribute(-60, 30);
		fileService.writeCountDown(60 * (60 + minutes));
		bonPointDaoImpl.removePunition(minutes);
	}

	/**
	 * Credited on Saturday.
	 */
	@Scheduled(cron = "0 1 11 * * SAT", zone = "Europe/Paris")
	public void giveCreditForWeekEnd() throws IOException {
		Integer minutes = bonPointDaoImpl.pointToDistribute(-60, 30);
		fileService.writeCountDown(60 * (60 + minutes));
		bonPointDaoImpl.removePunition(minutes);
	}

	/**
	 * Credited on Saturday.
	 */
	@Scheduled(cron = "0 1 11 * * SUN", zone = "Europe/Paris")
	public void giveCreditForWeekEndSunday() throws IOException {
		Integer minutes = bonPointDaoImpl.pointToDistribute(-60, 30);
		fileService.writeCountDown(60 * (60 + minutes));
		bonPointDaoImpl.removePunition(minutes);
	}

}
