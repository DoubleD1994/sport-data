package com.drybro.sportdata.service;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.repository.TeamRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class TeamService {

	private final TeamRepository teamRepository;

	public Team findTeamById( final Long teamId ) {
		try {
			return teamRepository.findById( teamId ).orElseThrow();
		} catch ( final NoSuchElementException nsee ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND,
					"Team with ID " + teamId + "  not found", nsee );
		}
	}


}
