package com.clement.poll.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String tempLocation;

    public String getTempLocation() {
        return tempLocation;
    }

    public void setTempLocation(String location) {
        this.tempLocation = location;
    }

    /**
     * Folder location for storing files
     */
    private String rootLocation;

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String location) {
        this.rootLocation = location;
    }

}
