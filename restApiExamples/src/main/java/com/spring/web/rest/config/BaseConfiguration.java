package com.spring.web.rest.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class BaseConfiguration {
    protected Config applicationConfiguration;
    protected String appConfigurationFolder;
    protected Helper helper;


    public BaseConfiguration(String configurationFilePath) {
        loadApplicationConfiguration(configurationFilePath);
    }

    private void loadApplicationConfiguration(String configurationFilePath) {
        File file = findConfigurationFile(configurationFilePath);
        if (!file.exists()) {
            throw new RuntimeException("Specified Configuration file does not exist:" + file.getAbsolutePath());
        }
        applicationConfiguration = ConfigFactory.parseFile(file).resolve();
        this.appConfigurationFolder = file.getParent();
        helper = new Helper(applicationConfiguration);
    }

    public File findConfigurationFile(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isAbsolute()) {
            file = new File(appConfigurationFolder + File.separatorChar + path);
        }
        return file;
    }
}
