package uk.co.a1dutch.football.web;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.co.a1dutch.football.entity.Team;
import uk.co.a1dutch.football.service.TeamService;

@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@SpyBean
	private TeamService teamService;

	private static String newcastle;

	private static String london;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void adds_london() throws Exception {
		Team team = new Team("London", "London", "Me", 10, "FA Cup", 10, null);
		london = new ObjectMapper().writeValueAsString(team);

		mockMvc.perform(post("/teams").content(london).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());

		verify(teamService).save(Mockito.any(Team.class));
	}

	@Test
	public void adds_newcastle() throws Exception {
		Team team = new Team("Newcastle", "London", "Me", 10, "FA Cup", 10, null);
		newcastle = new ObjectMapper().writeValueAsString(team);

		mockMvc.perform(post("/teams").content(newcastle).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());

		verify(teamService).save(Mockito.any(Team.class));
	}

	@Test
	public void find_newcastle() throws Exception {
		mockMvc.perform(get("/teams/Newcastle")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().json(newcastle));
	}

	@Test
	public void find_all_teams() throws Exception {
		mockMvc.perform(get("/teams")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().json("[" + newcastle + "," + london + "]"));
	}

	@Test
	public void find_all_teams_sorted_by_name_asc() throws Exception {
		mockMvc.perform(get("/teams?sort=name&direction=asc")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().json("[" + london + "," + newcastle + "]"));
	}

	@Test
	public void find_all_teams_sorted_by_name_desc() throws Exception {
		mockMvc.perform(get("/teams?sort=name&direction=desc")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string("[" + newcastle + "," + london + "]"));
	}

}
