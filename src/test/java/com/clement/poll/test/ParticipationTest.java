package com.clement.poll.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clement.poll.dto.Participation;
import com.clement.poll.service.ParticipationDaoImpl;
import com.clement.poll.service.ParticipationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ParticipationTest {

	@Resource
	ParticipationDaoImpl participationDaoImpl;
	@Resource
	ParticipationRepository participationRepository;

	public void insertParticipation() {
		participationRepository.deleteAll();
		participationRepository
				.save(new Participation("Lady", "Di", "lady_di@infosys.com", true, true, false, "Cououc"));
	}

	@Test
	public void testExportParticipationCSV() throws Exception {
		participationDaoImpl.exportCSVFileParticipation("582347803de8d70678b5b342");
	}

	@Test
	public void testPayParticipation() throws Exception {
		participationDaoImpl.payEvent("582490e03de8d720dc85d312", "582491253de8d720dc85d315", true);
	}

	@Test
	public void testInsertParticipation() {
		insertParticipation();
	}

}
