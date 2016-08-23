package com.infosys.eventtracker.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.eventtracker.dto.graph.Wrapper;
import com.infosys.eventtracker.service.LogRepositoryImpl;
import com.infosys.eventtracker.service.ParticipationRepository;

@RestController
public class EventTrackerController {


	@Resource
	private ParticipationRepository bonPointRepository;

	@Autowired
	LogRepositoryImpl logRepositoryImpl;


	@RequestMapping("/chart-channel")
	public Wrapper chartChannelCahrt() throws Exception {
		Wrapper jsChart = logRepositoryImpl.getMinutesPerChannel();
		return jsChart;
	}


}