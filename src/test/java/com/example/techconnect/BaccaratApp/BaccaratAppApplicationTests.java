package com.example.techconnect.BaccaratApp;

import com.example.techconnect.BaccaratApp.classes.baccarat.Baccarat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BaccaratAppApplicationTests {

	@Autowired
	Baccarat baccarat;

	@Test
	void contextLoads() {
	}
}

//	@Test
//	public void canPlayBaccarat(){
//		assertEquals(BetChoice.PLAYER, baccarat.getChoice());
//		assertEquals(0, baccarat.getBetAmount());
//		}
//
//	}





