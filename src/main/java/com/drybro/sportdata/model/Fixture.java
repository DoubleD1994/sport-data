package com.drybro.sportdata.model;

import java.time.LocalDateTime;

import com.drybro.sportdata.model.constants.Round;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fixture {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "fixture_id")
	private Long fixtureId;

	@ManyToOne
	@NotNull
	private Tournament tournament;

	@ManyToOne
	@NotNull
	private Team homeTeam;

	@ManyToOne
	@NotNull
	private Team awayTeam;

	@Nullable
	@Column(name = "home_team_points")
	private Integer homeTeamPoints;

	@Nullable
	@Column(name = "away_team_points")
	private Integer awayTeamPoints;

	@NotNull
	@Column(name = "start_time")
	private LocalDateTime startTime;

	@NotNull
	@Column(name = "location")
	private String location;

	@NotNull
	@Column(name = "round")
	private Round round;

}
