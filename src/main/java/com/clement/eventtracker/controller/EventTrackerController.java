package com.clement.eventtracker.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clement.eventtracker.dto.ParticipationStats;
import com.clement.eventtracker.service.ParticipationDaoImpl;
import com.clement.eventtracker.service.ParticipationRepository;

@RestController
public class EventTrackerController {

	@Resource
	private ParticipationRepository participationRepository;

	@Resource
	private ParticipationDaoImpl participationDaoImpl;

	@RequestMapping("/ws-participation-stats")
	public ParticipationStats getParticipationStats() throws Exception {
		ParticipationStats participationStats = new ParticipationStats();
		participationStats.setNbChild(participationDaoImpl.getChildNb());
		participationStats.setNbVegetarian(participationDaoImpl.getVegetarianNb());
		participationStats.setNbWomen(participationDaoImpl.getFemaleNb());
		return participationStats;
	}

}