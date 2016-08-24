package uk.co.a1dutch.football.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.a1dutch.football.entity.Team;
import uk.co.a1dutch.football.service.TeamService;

import java.util.List;

@RestController
public class TeamRestController {

	@Autowired
	private TeamService teamService;

	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Team team) {
		teamService.save(team);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/teams/{name}", method = RequestMethod.GET)
	public ResponseEntity<Team> team(@PathVariable String name) {
		return new ResponseEntity<>(teamService.find(name), HttpStatus.OK);
	}

	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	public ResponseEntity<List<Team>> teams(@RequestParam(required = false) String sort,
			@RequestParam(required = false) String direction) {
		return new ResponseEntity<>(teamService.findAll(sort, toDirection(direction)), HttpStatus.OK);
	}

	private Direction toDirection(String direction) {
		if (direction == null) {
			return null;
		}
		if ("desc".equalsIgnoreCase(direction)) {
			return Direction.DESC;
		}
		if ("asc".equalsIgnoreCase(direction)) {
			return Direction.ASC;
		}
		throw new UnknownSortDirectionException();
	}
}
