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
import jakarta.persistence.ManyToOne;
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
public class TournamentTeam {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "team_id")
	@NotNull
	private Team team;

	@ManyToOne
	@JoinColumn(name = "tournament_id")
	@NotNull
	private Tournament tournament;

	@NotNull
	@Column(name = "teamRound")
	private Round teamRound;

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
}
