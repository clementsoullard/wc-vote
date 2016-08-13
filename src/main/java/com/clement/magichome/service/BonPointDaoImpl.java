package com.clement.magichome.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.clement.magichome.object.BonPoint;

@Repository
public class BonPointDaoImpl {

	static final Logger LOG = LoggerFactory.getLogger(BonPointDaoImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<BonPoint> findBonPointsAvailable() {
		try {
			BasicQuery query = new BasicQuery("{pointConsumed: {$ne: 0}}");
			LOG.debug("Construction de la requete effectuée");
			List<BonPoint> bonPoints = mongoTemplate.find(query, BonPoint.class);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

}