package uk.co.a1dutch.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.a1dutch.football.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {
	// no implementation required
}
