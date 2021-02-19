package com.spring.web.rest.config;

public class ConfigBean {
    private String applicationConfigFile;
    private Configuration applicationConfig;
    private DatabaseConfiguration persistentDBConfiguration;
    private String cliConfigFile;



    public DatabaseConfiguration getPersistentDBConfiguration() {
        return persistentDBConfiguration;
    }

    public void setPersistentDBConfiguration(DatabaseConfiguration persistentDBConfiguration) {
        this.persistentDBConfiguration = persistentDBConfiguration;
    }

    public String getCliConfigFile() {
        return cliConfigFile;
    }

    public void setCliConfigFile(String cliConfigFile) {
        this.cliConfigFile = cliConfigFile;
    }

    public String getApplicationConfigFile() {
        return applicationConfigFile;
    }

    public void setApplicationConfigFile(String applicationConfigFile) {
        this.applicationConfigFile = applicationConfigFile;
    }

    public Configuration getApplicationConfig() {
        return applicationConfig;
    }

    public void setApplicationConfig(Configuration applicationConfig) {
        this.applicationConfig = applicationConfig;
    }
}
