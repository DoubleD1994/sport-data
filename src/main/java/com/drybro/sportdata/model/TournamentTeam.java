package com.drybro.sportdata.model;

import com.drybro.sportdata.model.constants.Round;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class TournamentTeam {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Team team;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Tournament tournament;

	@NotNull
	@Column(name = "round")
	private Round round;

	@Nullable
	@Column(name = "group")
	private String group;

	@Nullable
	@Column(name = "group_games_played")
	private Integer groupGamesPlayed;

	@Nullable
	@Column(name = "group_games_won")
	private Integer groupGamesWon;

	@Nullable
	@Column(name = "group_games_draw")
	private Integer groupGamesDraw;

	@Nullable
	@Column(name = "group_points_scored")
	private Integer groupPointsScored;

	@Nullable
	@Column(name = "group_points_against")
	private Integer groupPointsAgainst;

	@NotNull
	@Column(name = "eliminated")
	private Boolean eliminated;

}
