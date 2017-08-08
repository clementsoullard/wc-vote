package com.clement.poll.controller;

import java.util.Date;

import javax.websocket.server.PathParam;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clement.poll.dto.BillBinary;
import com.clement.poll.dto.Caisse;
import com.clement.poll.dto.LineCaisse;
import com.clement.poll.service.BillBinaryRepository;
import com.clement.poll.service.LineCaisseRepository;
import com.clement.poll.service.LineCaisseService;
import com.clement.poll.service.storage.StorageService;

@RestController
public class CashierController {

	@Autowired
	private LineCaisseRepository lineCaisseRepository;
	@Autowired
	private BillBinaryRepository billlBinaryRepository;
	
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
			BillBinary billBinary=new BillBinary();
			billBinary.setImage(image);
			billlBinaryRepository.save(billBinary);
			lineCaisse.setImageId(billBinary.getId());
		}
		lineCaisse.setDateOfSumission(new Date());
		lineCaisseRepository.save(lineCaisse);
	}

	@GetMapping(value = "/ws-line-caisse-summary")
	public Caisse sommeCaisse() {
		return lineCaisseService.getCaisse();
	}

	/**
	 * This return the image of a bill.
	 * 
	 * @return
	 */
	@GetMapping(value = "/ws-caisse-image/{id}", produces = "application/pdf")

	public @ResponseBody byte[] imageBill(@PathVariable(value = "id") String id) {
		return lineCaisseService.getImage(id);
	}

}