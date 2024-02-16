package com.drybro.sportdata.model.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Round {

	GROUP(0),
	LAST16(4),
	QUARTERFINAL(3),
	SEMIFINAL(2),
	FINAL(1);

	private final int numberOfRounds;

	Round( final int numberOfRounds ) {this.numberOfRounds = numberOfRounds;}

	@JsonValue
	public int getNumberOfRounds() {return numberOfRounds; }

	@JsonCreator
	public static Round getRoundFromValue( int numberOfRounds ) {
		for( final Round round : Round.values() ) {
			if(round.numberOfRounds == numberOfRounds){
				return round;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + numberOfRounds + "'");
	}
}
