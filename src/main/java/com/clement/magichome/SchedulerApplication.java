package com.clement.magichome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * This is the main class that launch the spring application.
 * 
 * @author Clement_Soullard
 *
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

	public static void writeCountDown(int value) {
		File file = new File(Constant.path);
		PrintStream ps;
		try {
			ps = new PrintStream(file);
			ps.print(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}