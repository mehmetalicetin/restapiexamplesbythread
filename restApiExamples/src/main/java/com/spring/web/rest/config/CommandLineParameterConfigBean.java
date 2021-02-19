package com.spring.web.rest.config;

import java.io.File;
import java.util.List;

import picocli.CommandLine;

public class CommandLineParameterConfigBean {
    @CommandLine.Option(names = { "-c", "--config-file" }, description = "Specify config file path.", defaultValue = "src/main/resources/config/fcbs.conf")
    private File configFile;
    @CommandLine.Unmatched
    List<String> unmatched;

    public File getConfigFile() {
        return this.configFile;
    }

    public List<String> getUnmatched() {
        return this.unmatched;
    }

    @Override
    public String toString() {
        return "AOMCommandLineParameterConfigBean{" +
                "configFile=" + configFile +
                '}';
    }
}
