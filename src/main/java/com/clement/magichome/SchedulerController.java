package com.clement.magichome;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;

@RestController
public class SchedulerController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/credit")
	public ActionResult greeting(
			@RequestParam(value = "value", defaultValue = "90") Integer value)
			throws Exception {
		SchedulerApplication.writeCountDown(value);
		return new ActionResult(counter.incrementAndGet(), "Ok");
	}
}