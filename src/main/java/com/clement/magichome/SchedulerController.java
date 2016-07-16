package com.clement.magichome;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clement.magichome.dto.CreditResult;
import com.clement.magichome.object.TVStatus;

@RestController
public class SchedulerController {

	@Resource
	TvCheckScheduler tvCheckScheduler;

	@Resource
	FileService fileService;

	@RequestMapping("/credit")
	public CreditResult credit(@RequestParam(value = "value", defaultValue = "90") Integer value) throws Exception {
		fileService.writeCountDown(value);
		return new CreditResult("Ok");
	}

	@RequestMapping("/tvstatus")
	public TVStatus tvStatus() throws Exception {
		TVStatus tvStatus = tvCheckScheduler.getStandByState();
		return tvStatus;
	}
}