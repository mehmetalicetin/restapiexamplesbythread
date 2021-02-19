package com.spring.web.rest.config;

import picocli.CommandLine;

public class ConfigurationDelegeta {
    public ConfigBean configure(String[] args) {
        return createConfiguration(args);
    }

    private ConfigBean createConfiguration(String[] args) {
        CommandLineParameterConfigBean cLPConfig = initCommandLineParametersConfig(args);
        Configuration fileConfig = new Configuration(cLPConfig.getConfigFile().getAbsolutePath());
        return new ConfigBuilder().build(cLPConfig, fileConfig);
    }

    private CommandLineParameterConfigBean initCommandLineParametersConfig(String[] args) {
        CommandLineParameterConfigBean recfCliConfigBean = new CommandLineParameterConfigBean();
        new CommandLine(recfCliConfigBean).parseArgs(args);
        return recfCliConfigBean;
    }
}
