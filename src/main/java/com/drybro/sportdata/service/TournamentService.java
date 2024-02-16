package com.drybro.sportdata.service;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.repository.TournamentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class TournamentService {

	private final TournamentRepository tournamentRepository;

	public Tournament findTournamentById( final Long tournamentId ) {
		try {
			return tournamentRepository.findById( tournamentId ).orElseThrow();
		} catch ( final NoSuchElementException nsee ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND,
					"Tournament with ID " + tournamentId + "  not found", nsee );
		}
	}

}
