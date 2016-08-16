package com.infosys.eventtracker.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infosys.eventtracker.dto.Participation;
import com.infosys.eventtracker.service.BonPointDaoImpl;
import com.infosys.eventtracker.service.ParticipationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParticipationTest {

	@Resource
	BonPointDaoImpl bonPointDaoImpl;
	@Resource
	ParticipationRepository participationRepository;

	public void insertParticipation() {
		participationRepository.deleteAll();
		participationRepository.save(new Participation("John", "Doe", "john_doe@infosys.com", true, true, false));
	}

	@Test
	public void testInsertParticipation() {
		insertParticipation();
	}

}
