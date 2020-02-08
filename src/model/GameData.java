package model;

public class GameData {
    private String name;
    private String description;
    private String path;
    private String[] images;

    public GameData(String name, String description, String path, String[] images){
        this.name = name;
        this.description = description;
        this.path = path;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String[] getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }
}
