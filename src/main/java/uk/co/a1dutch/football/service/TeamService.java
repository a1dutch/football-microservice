package uk.co.a1dutch.football.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import uk.co.a1dutch.football.entity.Team;
import uk.co.a1dutch.football.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

	private TeamRepository teamRepository;

	@Autowired
	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public List<Team> findAll(String order, Direction direction) {
		if (order == null) {
			logger.info("finding teams with natural ordering");
			return teamRepository.findAll();
		}

		logger.info("finding teams with order: {}, direction: {}", order, direction == null ? null : direction.name());
		return teamRepository.findAll(new Sort(direction, order));
	}

	public void save(Team team) {
		logger.info("saving team: {}", team);
		teamRepository.saveAndFlush(team);
	}

	public Team find(String name) {
		logger.info("finding team with name: {}", name);
		return teamRepository.findOne(name);
	}

}
