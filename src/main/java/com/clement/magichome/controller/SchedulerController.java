package com.clement.magichome.controller;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clement.magichome.FileService;
import com.clement.magichome.TvCheckScheduler;
import com.clement.magichome.dto.CreditResult;
import com.clement.magichome.dto.MinutesPerChannel;
import com.clement.magichome.object.TVStatus;
import com.clement.magichome.service.LogRepositoryImpl;

@RestController
public class SchedulerController {

	@Resource
	TvCheckScheduler tvCheckScheduler;

	@Resource
	FileService fileService;

	@Resource
	LogRepositoryImpl logRepositoryImpl;

	@RequestMapping("/credit")
	public CreditResult credit(@RequestParam(value = "value", defaultValue = "90") Integer value) throws Exception {
		TVStatus tvStatus = tvCheckScheduler.getStandByState();
		CreditResult creditResult = new CreditResult("Ok");
		if (fileService.writeCountDown(value)) {
			// The value is written in the file, we assume that it is properly
			// propagated the the C schedulers
			tvStatus.setRemainingSecond(value);
		} else {
			creditResult.setContent("KO");
		}
		creditResult.setStatus(tvStatus);
		return creditResult;
	}

	@RequestMapping("/tvstatus")
	public TVStatus tvStatus() throws Exception {
		TVStatus tvStatus = tvCheckScheduler.getStandByState();
		return tvStatus;
	}

	@RequestMapping("/test")
	public void test() throws Exception {
		AggregationResults<MinutesPerChannel> mpc = logRepositoryImpl.getMinutesPerChannel();
		System.out.println();
	}
}