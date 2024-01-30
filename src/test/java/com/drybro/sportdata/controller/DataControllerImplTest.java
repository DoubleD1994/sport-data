package com.drybro.sportdata.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drybro.sportdata.controller.data.DataController;
import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.model.constants.Sport;

@SpringBootTest
public class DataControllerImplTest {

	@Autowired
	private DataController dataController;

	@Test
	public void getRounds_HappyPath() {
		final List<Round> roundList = dataController.getRounds();
		assertThat( roundList.size() ).isEqualTo( 5 );
		assertThat( roundList ).contains( Round.GROUP );
		assertThat( roundList ).contains( Round.FINAL );
	}

	@Test
	public void getFormat_HappyPath() {
		final List<Format> formatList = dataController.getFormats();
		assertThat( formatList.size() ).isEqualTo( 3 );
		assertThat( formatList ).contains( Format.GROUPS_AND_KNOCKOUT );
	}

	@Test
	public void getSport_HappyPath() {
		final List<Sport> sportList = dataController.getSports();
		assertThat( sportList.size() ).isEqualTo( 2 );
		assertThat( sportList ).contains( Sport.FOOTBALL );
		assertThat( sportList ).contains( Sport.AMERICAN_FOOTBALL );
	}



}
