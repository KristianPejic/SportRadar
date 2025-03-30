package com.sportradar.worldcup;

import java.util.List;
import com.sportradar.worldcup.model.Match;
import com.sportradar.worldcup.service.WorldCupScoreboardService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the World Cup Scoreboard!");
        WorldCupScoreboardService scoreboard = new WorldCupScoreboardService();

        // Start matches
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        // Update scores
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        // Print ongoing matches
        System.out.println("Ongoing Matches:");
        List<Match> ongoingMatches = scoreboard.getOngoingMatches();
        ongoingMatches.forEach(match -> System.out.println(match.getHomeTeam() + " " + match.getHomeScore() +
                " - " + match.getAwayTeam() + " " + match.getAwayScore()));

        // Print match summary
        System.out.println("\nMatch Summary:");
        List<Match> summary = scoreboard.getSummary();
        for (int i = 0; i < summary.size(); i++) {
            Match match = summary.get(i);
            System.out.println((i + 1) + ". " + match.getHomeTeam() + " " + match.getHomeScore() +
                    " - " + match.getAwayTeam() + " " + match.getAwayScore());
        }

        // Finish a match (example)
        scoreboard.finishMatch("Mexico", "Canada");
        System.out.println("\nAfter finishing Mexico-Canada match:");
        summary = scoreboard.getSummary();
        for (int i = 0; i < summary.size(); i++) {
            Match match = summary.get(i);
            System.out.println((i + 1) + ". " + match.getHomeTeam() + " " + match.getHomeScore() +
                    " - " + match.getAwayTeam() + " " + match.getAwayScore());
        }
    }
}