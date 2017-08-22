package com.clement.poll.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.clement.poll.dto.Poll;

@Repository
public class VoteDaoImpl {

	final static float DISTRIBUTION_FACTOR = 2F;

	final static String NEW_LINE_SEPARATOR = "\n";

	static final Logger LOG = LoggerFactory.getLogger(VoteDaoImpl.class);

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat dfHour = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	@Autowired
	PollService eventService;

	@Autowired
	private MongoTemplate mongoTemplate;

	
	
	
	final static String FILE_HEADER[] = { "First Name", "Last Name", "Email", "Child", "Age", "Vegetarian", "Gender" };

	/**
	 * 
	 * @param idEvent
	 */
	public File exportCSVFileParticipation(String idEvent) throws IOException {
		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;
		File file = File.createTempFile("tmp", "csv");
		// Create the CSVFormat object with "\n" as a record delimiter

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			// initialize FileWriter object
			fileWriter = new FileWriter(file);
			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			Poll event = eventService.getPoll(idEvent);
			// Create CSV file header
			csvFilePrinter.printRecord(
					"Participation à l'évenement " + event.getName() + " du " + df.format(event.getDateMaxVote()));

		
			//csvFilePrinter.printRecord(participationDataRecord);

			
			LOG.info("CSV file was created successfully in " + file);
		} catch (

		Exception e) {
			LOG.error("Error in CsvFileWriter !!!", e);
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				LOG.error("Error while flushing/closing fileWriter/csvPrinter !!!", e);
			}

		}
		return file;

	}

	public void payEvent(String idEvent, String idParticipation, Boolean pay) {
		Query query = Query
				.query(Criteria.where("_id").is(idEvent).and("participations._id").is(new ObjectId(idParticipation)));
		Update update = new Update().set("participations.$.paid", pay);
		mongoTemplate.updateFirst(query, update, Poll.class);
	}

}
