package controller;

public class HighscoreEntry {
    private String playerName;
    private int highScore;

    public HighscoreEntry(String playerName, int highScore){
        this.playerName = playerName;
        this.highScore = highScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
