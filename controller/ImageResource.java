package controller;

import java.net.URL;
/**
 * Struct used when passing resources to the GUI
 * Type tag included for easy reference across project
 * @author Adam Bonnedahl (c13abl)
 */
public class ImageResource {
    private ObjectTypes type;
    private String path;

    public ImageResource(ObjectTypes type, String path){
        this.type = type;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ObjectTypes getType() {
        return type;
    }

    public void setType(ObjectTypes type) {
        this.type = type;
    }


}
