package uk.co.a1dutch.football.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.a1dutch.football.entity.Team;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamRepositoryTest {

	private static final String LIVERPOOL = "Liverpool";
	private static final String MANCHESTER_UNITED = "Manchester United";
	private static final String CHELSEA = "Chelsea";

	@Autowired
	private TeamRepository teamRepository;

	@Before
	public void setup() {
		teamRepository.save(new Team(MANCHESTER_UNITED, "Manchester", "Michael Glazer", 90000, "", 200, new Date()));
		teamRepository.save(new Team(CHELSEA, "Manchester", "Michael Glazer", 80000, "", 200, new Date()));
		teamRepository.save(new Team(LIVERPOOL, "Manchester", "Michael Glazer", 100000, "", 200, new Date()));
	}

	@Test
	public void find_all_teams() throws Exception {
		List<Team> teams = teamRepository.findAll();

		assertThat(teams.size(), is(3));
		assertThat(teams.get(0).getName(), is(CHELSEA));
		assertThat(teams.get(1).getName(), is(LIVERPOOL));
		assertThat(teams.get(2).getName(), is(MANCHESTER_UNITED));
	}

	@Test
	public void find_all_teams_sorted() throws Exception {
		List<Team> teams = teamRepository.findAll(new Sort(Sort.Direction.DESC, "capacity"));

		assertThat(teams.size(), is(3));
		assertThat(teams.get(0).getName(), is(LIVERPOOL));
		assertThat(teams.get(1).getName(), is(MANCHESTER_UNITED));
		assertThat(teams.get(2).getName(), is(CHELSEA));
	}

}
