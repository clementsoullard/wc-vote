package com.clement.magichome.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clement.magichome.object.BonPoint;
import com.clement.magichome.service.BonPointDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BonPointTest {

	@Resource
	BonPointDaoImpl bonPointDaoImpl;

	@Test
	public void testFindBonPoint() {
		List<BonPoint> bonPoints = bonPointDaoImpl.findBonPointsAvailable();
	}
}
