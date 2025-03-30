package com.sportradar.worldcup.service;

import com.sportradar.worldcup.exception.MatchNotFoundException;
import com.sportradar.worldcup.model.Match;
import com.sportradar.worldcup.validation.ScoreboardValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorldCupScoreboardService {
    private final Map<String, Match> matches;

    public WorldCupScoreboardService() {
        this.matches = new HashMap<>();
    }

    private String generateMatchKey(String homeTeam, String awayTeam) {
        return homeTeam + "||" + awayTeam;
    }

    public Match startMatch(String homeTeam, String awayTeam) {
        ScoreboardValidator.validateTeamNames(homeTeam, awayTeam);

        String matchKey = generateMatchKey(homeTeam, awayTeam);

        if (matches.containsKey(matchKey)) {
            throw new IllegalArgumentException("Match between " + homeTeam + " and " + awayTeam + " already exists");
        }

        Match newMatch = new Match(homeTeam, awayTeam);
        matches.put(matchKey, newMatch);
        return newMatch;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

        String matchKey = generateMatchKey(homeTeam, awayTeam);

        Match match = matches.get(matchKey);
        if (match == null) {
            throw new MatchNotFoundException("Match between " + homeTeam + " and " + awayTeam + " not found");
        }

        ScoreboardValidator.validateScores(homeScore, awayScore);

        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {

        String matchKey = generateMatchKey(homeTeam, awayTeam);

        if (!matches.containsKey(matchKey)) {
            throw new MatchNotFoundException("Match between " + homeTeam + " and " + awayTeam + " not found");
        }

        matches.remove(matchKey);
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted((match1, match2) -> {
                    int scoreComparison = Integer.compare(match2.getTotalScore(), match1.getTotalScore());
                    if (scoreComparison != 0) {
                        return scoreComparison;
                    }

                    return match2.getStartTime().compareTo(match1.getStartTime());
                })
                .collect(Collectors.toList());
    }

    public List<Match> getOngoingMatches() {
        return new ArrayList<>(matches.values());
    }
}