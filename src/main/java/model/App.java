package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("outputDir")
    @Expose
    private String outputDir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}