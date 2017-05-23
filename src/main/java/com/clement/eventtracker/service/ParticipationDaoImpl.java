package com.clement.eventtracker.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.clement.eventtracker.dto.Event;
import com.clement.eventtracker.dto.Participation;
import com.clement.eventtracker.dto.Question;

@Repository
public class ParticipationDaoImpl {

	final static float DISTRIBUTION_FACTOR = 2F;

	final static String NEW_LINE_SEPARATOR = "\n";

	static final Logger LOG = LoggerFactory.getLogger(ParticipationDaoImpl.class);

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat dfHour = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	@Autowired
	EventService eventService;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Return the number of child
	 * 
	 * @return
	 */
	public Integer getChildNb() {
		try {
			BasicQuery query = new BasicQuery("{isChild: {$eq: true}}");
			LOG.debug("Construction de la requete effectuée");
			Long enfantCount = mongoTemplate.count(query, Participation.class);
			return enfantCount.intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Return the number of vegatarians
	 * 
	 * @return
	 */
	public Integer getVegetarianNb() {
		try {
			BasicQuery query = new BasicQuery("{vegetarian: {$eq: true}}");
			LOG.debug("Construction de la requete effectuée");
			Long vegetarianCount = mongoTemplate.count(query, Participation.class);
			return vegetarianCount.intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Return the number of vegatarians
	 * 
	 * @return
	 */
	public Integer getFemaleNb() {
		try {
			BasicQuery query = new BasicQuery("{gender: 'female'}");
			LOG.debug("Construction de la requete effectuée");
			Long femaleCount = mongoTemplate.count(query, Participation.class);
			return femaleCount.intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

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

			Event event = eventService.getEvent(idEvent);
			// Create CSV file header
			csvFilePrinter.printRecord(
					"Participation à l'évenement " + event.getName() + " du " + df.format(event.getDate()));

			List<Participation> participations = event.getParticipations();
			List participationDataRecord = new ArrayList();
			participationDataRecord.add("First name");
			participationDataRecord.add("Last name");
			participationDataRecord.add("Email");
			participationDataRecord.add("children");
			participationDataRecord.add("age");
			participationDataRecord.add("Végetarien");
			participationDataRecord.add("Gender");
			participationDataRecord.add("Registration date");
			participationDataRecord.add("Paid");

			List<Question> additionalQuestion = event.getAdditionalQuestions();
			for (Question question : additionalQuestion) {
				participationDataRecord.add(question.getName());
			}

			csvFilePrinter.printRecord(participationDataRecord);

			for (Participation participation : participations) {
				participationDataRecord = new ArrayList();
				participationDataRecord.add(participation.getFirstName());
				participationDataRecord.add(participation.getLastName());
				participationDataRecord.add(participation.getEmail());
				participationDataRecord.add(participation.isChild());
				participationDataRecord.add(participation.getAge());
				participationDataRecord.add(participation.isVegetarian());
				participationDataRecord.add(participation.getGender());
				participationDataRecord.add(dfHour.format(participation.getRegistrationDate()));
				participationDataRecord.add(participation.isPaid());
					if (participation.getQuestions() != null && participation.getQuestions().size() > 0) {
					for (int i = 0; i < participation.getQuestions().size(); i++) {
						participationDataRecord.add(participation.getQuestions().get(Integer.toString(i)));
					}
				}

				csvFilePrinter.printRecord(participationDataRecord);
			}
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
		mongoTemplate.updateFirst(query, update, Event.class);
	}

}
