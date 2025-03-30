package com.sportradar.worldcup.service;

import com.sportradar.worldcup.exception.MatchNotFoundException;
import com.sportradar.worldcup.model.Match;
import com.sportradar.worldcup.validation.ScoreboardValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WorldCupScoreboardServiceTest {
    private WorldCupScoreboardService scoreboard;

    @BeforeEach
    public void setUp() {
        scoreboard = new WorldCupScoreboardService();
    }

    @Test
    public void testStartMatch() {
        Match match = scoreboard.startMatch("Mexico", "Canada");

        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testUpdateScore() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 2, 1);

        Match match = scoreboard.getOngoingMatches().get(0);
        assertEquals(2, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
    }

    @Test
    public void testFinishMatch() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");

        scoreboard.finishMatch("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Spain", summary.get(0).getHomeTeam());
    }

    @Test
    public void testGetSummary() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);

        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore("Spain", "Brazil", 10, 2);

        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore("Germany", "France", 2, 2);

        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);

        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = scoreboard.getSummary();

        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString());
        assertEquals("Spain 10 - Brazil 2", summary.get(1).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(2).toString());
        assertEquals("Argentina 3 - Australia 1", summary.get(3).toString());
        assertEquals("Germany 2 - France 2", summary.get(4).toString());
    }

    @Test
    public void testInvalidMatchOperations() {
       
        assertThrows(ScoreboardValidationException.class,
                () -> scoreboard.startMatch("", "Canada"));

        assertThrows(ScoreboardValidationException.class,
                () -> scoreboard.startMatch("Mexico", "Mexico"));

       
        scoreboard.startMatch("Mexico", "Canada");

        
        assertThrows(ScoreboardValidationException.class,
                () -> scoreboard.updateScore("Mexico", "Canada", -1, 0));

        
        assertThrows(MatchNotFoundException.class,
                () -> scoreboard.finishMatch("Unknown", "Team"));
    }

    @Test
    public void testDuplicateMatchPrevention() {
        scoreboard.startMatch("Mexico", "Canada");

        assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch("Mexico", "Canada"));
    }
}