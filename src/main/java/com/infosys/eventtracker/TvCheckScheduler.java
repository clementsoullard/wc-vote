package com.infosys.eventtracker;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.infosys.eventtracker.object.Channel;
import com.infosys.eventtracker.object.LogEntry;
import com.infosys.eventtracker.object.TVStatus;
import com.infosys.eventtracker.object.TVWrapper;
import com.infosys.eventtracker.service.ChannelRepository;
import com.infosys.eventtracker.service.LogRepository;

@Configuration
@EnableAutoConfiguration
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
	private LogRepository logRepository;

	@Resource
	private ChannelRepository channelRepository;

	@Resource
	private PropertyManager propertyManager;

	private Gson gson = new Gson();

	private TVWrapper tvWrapper = new TVWrapper();

	private Map<Integer, Float> minutesPerChannel = new HashMap<Integer, Float>();

	private Date from = new Date();

	private Date to;

	@Resource
	private FileService fileService;


	/** *Cache map for channel name */
	Map<Integer, String> channelNameCache = new HashMap<>();

	/**
	 * Every 15 sec. check the status of the TV and .
	 */
	@Scheduled(cron = "7 */5 * * * *")
	public void putInDb() throws IOException {
		to = new Date();
		for (Integer channel : minutesPerChannel.keySet()) {
			Float minutes = minutesPerChannel.get(channel);
			String channelName = getChannelName(channel);
			logRepository.save(new LogEntry("TV", channel, channelName, minutes, from, to));
		}
		from = new Date();
		minutesPerChannel.clear();
	}

	/**
	 * Get the channel name and manage a cache.
	 * 
	 * @param channel
	 * @return
	 */
	private String getChannelName(Integer channelId) {
		String channelName = channelNameCache.get(channelId);
		if (channelName == null) {
			List<Channel> channels = channelRepository.findByEpgId(channelId.toString());
			LOG.debug("Nombre de chaine " + channels.size());
			if (channels.size() > 0) {
				channelName = channels.get(0).getName();
				LOG.debug("Chanine name " + channelName);
			} else {
				channelName = "Chaine inconnue";
				LOG.debug("Chaine inconnue " + channelName);
			}
			channelNameCache.put(channelId, channelName);
		}
		return channelName;
	}




	/**
	 * Get the tv status
	 * 
	 * @param withRefresh
	 *            if with refresh is required, there is a specifi
	 * @return
	 */
	public TVStatus getStandByState() {
		return tvWrapper.getResult();
	}

}
