package model;

import java.util.ArrayList;

public class GameData {
    private String name;
    private String description;
    private String path;
    private ArrayList<String> images;

    public GameData(String name, String description, String path, ArrayList<String> images){
        this.name = name;
        this.description = description;
        this.path = path;
        if (images.size() != 0) {
            this.images = images;
        }else{
            this.images = new ArrayList<String>();
            this.images.add("./res/test/image1.jpg"); // on met une image par défault
        }
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }
}
