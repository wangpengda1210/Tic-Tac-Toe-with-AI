package tictactoe;

public enum GameLevel {

    EASY("easy"), MEDIUM("medium"), HARD("hard"), USER("user"), NULL("");

    private final String level;
    GameLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static GameLevel findLevel(String level) {
        for (GameLevel gameLevel : GameLevel.values()) {
            if (gameLevel.getLevel().equals(level.toLowerCase())) {
                return gameLevel;
            }
        }
        return NULL;
    }

}
