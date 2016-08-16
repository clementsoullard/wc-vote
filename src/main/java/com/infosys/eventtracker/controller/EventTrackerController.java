package com.infosys.eventtracker.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.eventtracker.service.ParticipationRepository;
import com.infosys.eventtracker.FileService;
import com.infosys.eventtracker.TvCheckScheduler;
import com.infosys.eventtracker.dto.CreditResult;
import com.infosys.eventtracker.dto.Participation;
import com.infosys.eventtracker.dto.PunitionResult;
import com.infosys.eventtracker.dto.graph.Wrapper;
import com.infosys.eventtracker.object.BonPoint;
import com.infosys.eventtracker.object.LogEntry;
import com.infosys.eventtracker.object.TVStatus;
import com.infosys.eventtracker.service.LogRepository;
import com.infosys.eventtracker.service.LogRepositoryImpl;

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