package model;

public enum Genre {
    SCIENCE_FICTION("Science-fiction"),
    POLICIER("Policier"),
    FANTASY("Fantasy"),
    CLASSIQUE("Classique");

    private final String displayName;

    private Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
