package com.clement.eventtracker.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clement.eventtracker.dto.Participation;
import com.clement.eventtracker.service.ParticipationDaoImpl;
import com.clement.eventtracker.service.ParticipationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ParticipationTest {

	@Resource
	ParticipationDaoImpl bonPointDaoImpl;
	@Resource
	ParticipationRepository participationRepository;

	public void insertParticipation() {
		participationRepository.deleteAll();
		participationRepository.save(new Participation("Lady", "Di", "lady_di@infosys.com", true, true, false,"Cououc"));
	}

	@Test
	public void testInsertParticipation() {
		insertParticipation();
	}

}
