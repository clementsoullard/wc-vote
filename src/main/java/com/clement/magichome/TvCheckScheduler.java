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

import com.clement.magichome.object.TVStatus;
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

	static final Logger LOG = LoggerFactory.getLogger(TvCheckScheduler.class);

	@Resource
	LogService logService;

	private Gson gson = new Gson();

	TVWrapper tvWrapper;
	Boolean tvStatusRelay;

	/**
	 * Every 15 sec. check the status of the TV and .
	 */
	@Scheduled(cron = "*/15 * * * * *")
	public void updateSandbyStatus() throws IOException {
		InputStreamReader xml = new InputStreamReader(getStreamStanbyStateFromLivebox());
		tvWrapper = gson.fromJson(xml, TVWrapper.class);
		Integer activeStandbyState = Integer.parseInt(tvWrapper.getResult().getData().getActiveStandbyState());
		Boolean standbyState = (activeStandbyState == 1);
		SchedulerApplication.writeStandby(standbyState);
		Boolean relayStatus = getTvStatusRelay();
		tvWrapper.getResult().setRelayStatus(relayStatus);
		if (!relayStatus && !standbyState) {
			pressOnOffButton();
			// http://192.168.1.12:8080/remoteControl/cmd?operation=01&key=116&mode=0
		}
		LOG.debug("Standby=" + standbyState + ", getTvStatusRelay=" + getTvStatusRelay());

		if (getTvStatusRelay()) {

		}
	}

	/**
	 * 
	 */
	private void pressOnOffButton() {
		try {
			String uri = "http://192.168.1.12:8080/remoteControl/cmd?operation=01&key=116&mode=0";
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
			connection.getInputStream().read();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
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
			LOG.error(e.getMessage(), e);
		}
		LOG.debug("Close state detected at relay level");
		return false;
	}

	/**
	 * Contact the livebox and get the status of it.
	 * 
	 * @return
	 * @throws IOException
	 */
	private InputStream getStreamStanbyStateFromLivebox() throws IOException {
		String uri = "http://192.168.1.12:8080/remoteControl/cmd?operation=10";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		return connection.getInputStream();
	}

	public TVStatus getStandByState() {
		return tvWrapper.getResult();
	}

}
