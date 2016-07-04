package com.clement.magichome;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;

import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clement.magichome.object.TVStatus;
import com.clement.magichome.object.TVWrapper;
import com.clement.magichome.service.LogService;
import com.google.gson.Gson;

@RestController
public class TracerController {

	@Resource
	LogService logService;

	Gson gson = new Gson();

	@RequestMapping("/trace")
	public void trace() throws Exception {
		String uri = "http://192.168.1.12:8080/remoteControl/cmd?operation=10";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		InputStreamReader xml = new InputStreamReader(connection.getInputStream());

		TVWrapper tvStatus = gson.fromJson(xml, TVWrapper.class);

		logService.insertSomtething();
	}
}
