package com.drybro.sportdata.model;

import com.drybro.sportdata.model.constants.Round;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TournamentTeam {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	@NotNull
	private Team team;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tournament_id", referencedColumnName = "id")
	@NotNull
	private Tournament tournament;

	@NotNull
	@Column(name = "round")
	private Round round;

	@Nullable
	@Column(name = "tournament_group")
	private String tournamentGroup;

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
	@Column(name = "group_games_lost")
	private Integer groupGamesLost;

	@Nullable
	@Column(name = "group_score_for")
	private Integer groupScoreFor;

	@Nullable
	@Column(name = "group_score_against")
	private Integer groupScoreAgainst;

	@Nullable
	@Column(name = "group_score_difference")
	private Integer groupScoreDifference;

	@Nullable
	@Column(name = "group_points")
	private Integer groupPoints;

	@NotNull
	@Column(name = "eliminated")
	private Boolean eliminated;

	public TournamentTeam( final Team team, final Tournament tournament, final Round round,
			@Nullable final Integer groupGamesPlayed, @Nullable final Integer groupGamesWon,
			@Nullable final Integer groupGamesDraw, @Nullable final Integer groupGamesLost, @Nullable final Integer groupScoreFor,
			@Nullable final Integer groupScoreAgainst, @Nullable final Integer groupScoreDifference, @Nullable final Integer groupPoints, final Boolean eliminated ) {
		this.team = team;
		this.tournament = tournament;
		this.round = round;
		this.groupGamesPlayed = groupGamesPlayed;
		this.groupGamesWon = groupGamesWon;
		this.groupGamesDraw = groupGamesDraw;
		this.groupGamesLost = groupGamesLost;
		this.groupScoreFor = groupScoreFor;
		this.groupScoreAgainst = groupScoreAgainst;
		this.groupScoreDifference = groupScoreDifference;
		this.groupPoints = groupPoints;
		this.eliminated = eliminated;
	}
}
