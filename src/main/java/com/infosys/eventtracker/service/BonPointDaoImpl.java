package com.infosys.eventtracker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.infosys.eventtracker.object.BonPoint;

@Repository
public class BonPointDaoImpl {

	final static float DISTRIBUTION_FACTOR = 2F;

	static final Logger LOG = LoggerFactory.getLogger(BonPointDaoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ParticipationRepository bonPointRepository;

	public List<BonPoint> findBonPointsAvailable() {
		try {
			BasicQuery query = new BasicQuery("{pointConsumed: {$ne: 0}}");
			LOG.debug("Construction de la requete effectuée");
			List<BonPoint> bonPoints = mongoTemplate.find(query, BonPoint.class);
			return bonPoints;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public List<BonPoint> findPostiveBonPointsAvailable() {
		try {
			BasicQuery query = new BasicQuery("{pointConsumed: {$gt: 0}}");
			LOG.debug("Construction de la requete effectuée");
			List<BonPoint> bonPoints = mongoTemplate.find(query, BonPoint.class);
			return bonPoints;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public List<BonPoint> findNegativeBonPointsAvailable() {
		try {
			BasicQuery query = new BasicQuery("{pointConsumed: {$lt: 0}}");
			LOG.debug("Construction de la requete effectuée");
			List<BonPoint> bonPoints = mongoTemplate.find(query, BonPoint.class);
			return bonPoints;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public Integer sumBonPoint() {
		Integer sum = 0;
		List<BonPoint> bonPoints = findBonPointsAvailable();
		for (BonPoint bonPoint : bonPoints) {
			sum += bonPoint.getPointConsumed();
		}
		return sum;
	}

	/**
	 * This is called by the scheduler to compute the number of point to
	 * distribute and propagate into the timer computation. Note: This does not
	 * take the point off the Bon Point, in rder to do this, you have to call
	 * the method
	 * 
	 * @see removePunition()
	 * @param min
	 * @param max
	 * @return
	 */
	public Integer pointToDistribute(Integer min, Integer max) {
		Integer sum = sumBonPoint();
		Integer pointToDistribute = Math.round(sum.floatValue() / DISTRIBUTION_FACTOR + (1 * Math.signum(sum)));
		if (pointToDistribute < min) {
			pointToDistribute = min;
		} else if (pointToDistribute > max) {
			pointToDistribute = max;
		}
		return pointToDistribute;
	}


}