package com.clement.magichome;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clement.magichome.service.LogService;

@RestController
public class TracerController {

	@Resource
	LogService logService;


	@RequestMapping("/trace")
	public void trace() throws Exception {

		logService.insertSomtething();
	}
}
