package com.spring.web.rest.config;

public class ConfigBuilder {
    public ConfigBean build(CommandLineParameterConfigBean commandParamConfig, Configuration fileConfig) {
        ConfigBean configBean = new ConfigBean();
        if (commandParamConfig != null) {
            configBean.setApplicationConfigFile(commandParamConfig.getConfigFile().getAbsolutePath());
        }
        configBean.setPersistentDBConfiguration(fileConfig.getPersistentConfiguration());
        configBean.setApplicationConfig(fileConfig);
        return configBean;
    }
}

