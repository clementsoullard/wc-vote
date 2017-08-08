package com.clement.poll.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import com.clement.poll.dto.AggregationSum;
import com.clement.poll.dto.Caisse;
import com.clement.poll.dto.CaisseSummary;
import com.clement.poll.dto.LineCaisse;

/**
 * This object is handling the object related to the caisse.
 * 
 * @author Clement_Soullard
 *
 */
@Component
public class LineCaisseService {
	@Autowired
	LineCaisseRepository lineCaisseRepository;

	@Autowired
	BillBinaryRepository billlBinaryRepository;

	static final Logger LOG = LoggerFactory.getLogger(LineCaisseService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public Caisse getCaisse() {
		Caisse caisse = new Caisse();
		caisse.setCaisseSummary(getSumDebitCredit());
		caisse.setLineCaisses(lineCaisseRepository.findAll());
		return caisse;
	}

	public CaisseSummary getSumDebitCredit() {
		CaisseSummary caisseSummary = new CaisseSummary();
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum("debit").as("total"));
		AggregationResults<AggregationSum> cashSummary = mongoTemplate.aggregate(aggregation, LineCaisse.class,
				AggregationSum.class);
		Float debit = cashSummary.getUniqueMappedResult().getTotal();
		aggregation = Aggregation.newAggregation(Aggregation.group().sum("credit").as("total"));
		cashSummary = mongoTemplate.aggregate(aggregation, LineCaisse.class, AggregationSum.class);
		Float credit = cashSummary.getUniqueMappedResult().getTotal();
		Float total = credit - debit;
		caisseSummary.setCredit(credit);
		caisseSummary.setDebit(debit);
		caisseSummary.setTotal(total);
		return caisseSummary;
	}

	public byte[] getImage(String id) {
		return billlBinaryRepository.findOne(id).getImage();
	}
}
