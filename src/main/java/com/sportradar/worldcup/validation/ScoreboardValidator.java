package com.sportradar.worldcup.validation;

import com.sportradar.worldcup.model.Match;

public class ScoreboardValidator {
    public static void validateMatch(Match match) {
        validateTeamNames(match.getHomeTeam(), match.getAwayTeam());
    }

    public static void validateTeamNames(String homeTeam, String awayTeam) {
        if (isNullOrEmpty(homeTeam) || isNullOrEmpty(awayTeam)) {
            throw new ScoreboardValidationException("Team names cannot be null or empty");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new ScoreboardValidationException("Home and away teams must be different");
        }
    }

    public static void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new ScoreboardValidationException("Scores cannot be negative");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}