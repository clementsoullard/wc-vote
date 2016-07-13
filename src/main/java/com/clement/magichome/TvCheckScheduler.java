package com.clement.magichome;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final int PAUSE_CODE = -4;

	private static final int RESUME_CODE = -5;

	static final Logger LOG = LoggerFactory.getLogger(TvCheckScheduler.class);

	Boolean pauseMode = false;
	@Resource
	LogService logService;

	Gson gson = new Gson();

	/**
	 * Every day the TV stops at midnight.
	 */
	@Scheduled(cron = "*/15 * * * * *")
	public void closeTv() throws IOException {
		InputStreamReader xml = new InputStreamReader(getStatusStream());
		TVWrapper tvWrapper = gson.fromJson(xml, TVWrapper.class);
		Integer activeStandbyState = Integer.parseInt(tvWrapper.getResult().getData().getActiveStandbyState());
		LOG.debug("Standby=" + (activeStandbyState == 1) + ", tvStatusJava=" + pauseMode + ", getTvStatusRelay="
				+ getTvStatusRelay());
		// If the TV relay is open
		if (getTvStatusRelay()) {
			// If the tv is in standby mode.
			if (activeStandbyState == 1) {
				if (!pauseMode) {
					LOG.debug("Pause");
					SchedulerApplication.writeCountDown(PAUSE_CODE);
				}
				pauseMode = true;
			} else {
				if (pauseMode) {
					LOG.debug("Resume");
					SchedulerApplication.writeCountDown(RESUME_CODE);
				}
				pauseMode = false;
			}
		}
	}

	/**
	 * Return the TV status stored in the file /tmp/scheduler/ST
	 * 
	 * @return true is the TV is on at the scheduler level.
	 */
	boolean getTvStatusRelay() {
		File file = new File("/tmp/scheduler/ST");
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				byte buffer[] = new byte[5];
				IOUtils.read(fis, buffer);
				String valueRead = new String(buffer).trim();
				// If 1 is read from the livebox then it is in standby
				// (therefore not consuming seconds)
				LOG.debug("Value read at relay level " + valueRead);
				if (valueRead != null && valueRead.equals("1")) {
					LOG.debug("Open state detected at relay level");
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.debug("Close state detected at relay level");
		return false;
	}

	InputStream getStatusStream() throws IOException {
		String uri = "http://192.168.1.12:8080/remoteControl/cmd?operation=10";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		return connection.getInputStream();
	}

}
