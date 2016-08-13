package com.clement.magichome.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clement.magichome.object.BonPoint;
import com.clement.magichome.service.BonPointDaoImpl;
import com.clement.magichome.service.BonPointRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BonPointTest {

	@Resource
	BonPointDaoImpl bonPointDaoImpl;
	@Resource
	BonPointRepository bonPointRepository;

	@Test
	public void testFindBonPoint() {
		List<BonPoint> bonPoints = bonPointDaoImpl.findBonPointsAvailable();
		org.junit.Assert.assertEquals(bonPoints.size(), 3);
	}

	@Test
	public void testInsertBonPoint() {
		bonPointRepository.save(new BonPoint(10, 10, new Date(), "10"));
	}

}
