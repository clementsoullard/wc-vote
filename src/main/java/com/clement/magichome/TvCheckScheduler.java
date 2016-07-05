package com.clement.magichome;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.clement.magichome.object.TVWrapper;
import com.clement.magichome.service.LogService;
import com.google.gson.Gson;

@Configuration
@EnableScheduling
/**
 * This class credit some minutes for the TV.
 * 
 * @author Clément
 *
 */
public class TvCheckScheduler {

	@Resource
	LogService logService;

	Gson gson = new Gson();

	Boolean tvStatus = false;

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "*/15 * * * * *")
	public void closeTv() throws IOException {

		String uri = "http://192.168.1.12:8080/remoteControl/cmd?operation=10";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		InputStreamReader xml = new InputStreamReader(connection.getInputStream());
		TVWrapper tvWrapper = gson.fromJson(xml, TVWrapper.class);
		Integer activeStandbyState = Integer.parseInt(tvWrapper.getResult().getData().getActiveStandbyState());
		System.out.println("Statut de la tele " + activeStandbyState);

		if (activeStandbyState == 1) {
			SchedulerApplication.writeCountDown(-4);
			System.out.println("Passage en pause");

		}
		else if(!tvStatus){
			SchedulerApplication.writeCountDown(-5);
			System.out.println("Resume");
		}

	}

}
