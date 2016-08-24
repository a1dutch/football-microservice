package uk.co.a1dutch.football.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.a1dutch.football.repository.TeamRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceTest {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamService teamService;

	@Test
	public void find_all_sorted() throws Exception {
		teamService.findAll("capacity", Direction.ASC);

		ArgumentCaptor<Sort> sortCapture = ArgumentCaptor.forClass(Sort.class);
		verify(teamRepository).findAll(sortCapture.capture());
		assertThat(sortCapture.getValue().getOrderFor("capacity").getDirection(), is(Direction.ASC));
	}

	@Configuration
	public static class Config {
		@Bean
		public TeamRepository teamRepository() {
			return Mockito.mock(TeamRepository.class);
		}

		@Bean
		public TeamService teamService() {
			return new TeamService(teamRepository());
		}
	}
}
