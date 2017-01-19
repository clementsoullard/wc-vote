package com.clement.eventtracker.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clement.eventtracker.dto.Caisse;
import com.clement.eventtracker.dto.LineCaisse;
import com.clement.eventtracker.service.LineCaisseRepository;
import com.clement.eventtracker.service.LineCaisseService;
import com.clement.eventtracker.service.storage.StorageService;

@RestController
public class CashierController {

	@Autowired
	private LineCaisseRepository lineCaisseRepository;
	@Autowired
	private LineCaisseService lineCaisseService;
	@Autowired
	private StorageService storageService;

	/**
	 * 
	 * @param idImage
	 *            if the the idImage is -1 then the content is not stored, but
	 *            the image is saved.
	 * @param lineCaisse
	 * @throws Exception
	 */
	@PostMapping(value = "/ws-line-caisse/{idImage}")
	public void unregister(@PathVariable("idImage") String idImage, @RequestBody LineCaisse lineCaisse)
			throws Exception {
		if (!idImage.equals("-1")) {
			byte image[] = IOUtils.toByteArray(storageService.loadAsResource(idImage).getInputStream());
			lineCaisse.setImage(image);
		}
		lineCaisseRepository.save(lineCaisse);
	}

	@GetMapping(value = "/ws-line-caisse-summary")
	public Caisse sommeCaisse() {
		return lineCaisseService.getCaisse();
	}

}