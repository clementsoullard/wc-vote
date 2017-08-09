package com.clement.poll.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.clement.poll.dto.Vote;
import com.clement.poll.service.VoteDaoImpl;
import com.clement.poll.service.VoteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ParticipationTest {

	@Resource
	VoteDaoImpl participationDaoImpl;
	@Resource
	VoteRepository participationRepository;

	public void insertParticipation() {
		participationRepository.deleteAll();
		participationRepository.save(new Vote("Lady", "Di", "lady_di@infosys.com", "Coucou"));
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
