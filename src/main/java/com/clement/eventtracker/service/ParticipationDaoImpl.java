package com.clement.eventtracker.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.clement.eventtracker.dto.Participation;

@Repository
public class ParticipationDaoImpl {

	final static float DISTRIBUTION_FACTOR = 2F;

	static final Logger LOG = LoggerFactory.getLogger(ParticipationDaoImpl.class);

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
	}	/**
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
	
	void createFile(){
	    FileWriter fileWriter = null;
	    
	    	         
	    
	    	        CSVPrinter csvFilePrinter = null;
	    
	    	         
	    
	    	        //Create the CSVFormat object with "\n" as a record delimiter
	    
	    	        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
	    
	    	                 
	    
	    	        try {
	    
	    	             
	    
	    	            //initialize FileWriter object
	    
	    	            fileWriter = new FileWriter(fileName);
	    
	    	             
	    
	    	            //initialize CSVPrinter object
	    
	    	            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	    
	    	             
	    
	    	            //Create CSV file header
	    
	    	            csvFilePrinter.printRecord(FILE_HEADER);
	    
	    	             
	    
	    	            //Write a new student object list to the CSV file
	    
	    	            for (Participation student : students) {
	    
	    	                List studentDataRecord = new ArrayList();
	    
	    	                studentDataRecord.add(String.valueOf(student.getId()));
	    
	    	                studentDataRecord.add(student.getFirstName());
	    
	    	                studentDataRecord.add(student.getLastName());
	    
	    	                studentDataRecord.add(student.getGender());
	    
	    	                studentDataRecord.add(String.valueOf(student.getAge()));
	    
	    	                csvFilePrinter.printRecord(studentDataRecord);
	    
	    	            }
	    
	    	 
	    
	    	            System.out.println("CSV file was created successfully !!!");
	    
	    	             
	    
	    	        } catch (Exception e) {
	    
	    	            System.out.println("Error in CsvFileWriter !!!");
	    
	    	            e.printStackTrace();
	    
	    	        } finally {
	    
	    	            try {
	    
	    	                fileWriter.flush();
	    
	    	                fileWriter.close();
	    
	    	                csvFilePrinter.close();
	    
	    	            } catch (IOException e) {
	    
	    	                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
	    
	    	                e.printStackTrace();
	    
	    	            }
	    
	    	        }
	    
	    	    }
	}

}