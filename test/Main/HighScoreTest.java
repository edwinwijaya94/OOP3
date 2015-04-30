package Main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HighScoreTest {
	
	@Before
    public void setUp() {
		HighScore.getInstance().getHighScoreFromFile();
    }
	
	@Test
	public void testGetHighScore() {
		assertNotNull(HighScore.getInstance().getHighScore());
	}
	
	@Test
	public void testRemoveAllHighScore() {
		assertTrue(HighScore.getInstance().getHighScore().isEmpty());
	}
	
	@Test
	public void testAddHighScore() {
		HighScore.getInstance().addHighScore("Typer Animal", 9999);
		assertFalse(HighScore.getInstance().getHighScore().isEmpty());
	}
	
	@Test
	public void testGetHighScoreFromFile() {
		assertNotNull(HighScore.getInstance().getHighScore());
	}
	
	@Test
	public void testWriteHighScoretoFile() {
		try
		{
			HighScore.getInstance().writeHighScoretoFile();
		}catch(Exception e)
		{
			fail("WHOOPS! Threw Exception" + e);
		}
	}
	
}
