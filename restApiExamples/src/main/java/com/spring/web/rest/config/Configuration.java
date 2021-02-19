package com.spring.web.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.spring.web.rest.config.Constant.*;

public class Configuration extends BaseConfiguration{
    private static final Logger logger	= LoggerFactory.getLogger(Configuration.class);
    String configurationFilePath;


    public Configuration(String configurationFilePath) {
        super(configurationFilePath);
        this.configurationFilePath = configurationFilePath;
    }

    public  String getConfigPath(){
       return configurationFilePath;
    }

    public DatabaseConfiguration getPersistentConfiguration() {
        return helper.getDatabaseConfiguration(PERSISTENT, true);
    }

    public DatabaseConfiguration getDatabaseConfiguration(String databaseKey) {
        if ((databaseKey.equals(DATA_DB) && !existInConfig(DATA_DB)) ||
                (databaseKey.equals(SITE_REP_CACHE) && !existInConfig(SITE_REP_CACHE))) {
            logger.info("DataDB configuration couldn't find, Use instead persistentDatabase");
            return null;
        }
        String key;
        if (Objects.equals(databaseKey, CONTROL)) {
            key = CACHE;
        } else if (Objects.equals(databaseKey, REQUEST)) {
            key = REQUEST;
        } else {
            key = databaseKey;
        }
        String datasource = helper.getString(key + Database.DATA_SOURCE);
        String url = helper.getString(key + Database.URL);
        String username = helper.getString(databaseKey + Database.USERNAME);
        String password = helper.getString(databaseKey + Database.PASSWORD);
        Integer maximum = helper.getOptionalInteger(databaseKey + Database.MAXIMUM_CONNECTION);
        Integer connectionCount = helper.getOptionalInteger(databaseKey + Database.CONNECTION_COUNT);
        Integer connectionTimeout = helper.getOptionalInteger(databaseKey + Database.CONNECTION_TIMEOUT);
        Boolean rewriteBatchedStatements = helper.getOptionalBoolean(databaseKey + ".rewriteBatchedStatements");
        Boolean autoCommit = helper.getOptionalBoolean(databaseKey + ".autoCommit");
        Boolean readOnly = helper.getOptionalBoolean(databaseKey + ".readOnly");
        if (connectionCount == null && maximum == null) {
            throw new RuntimeException("There is no configuration for [Key: connectionCount]!");
        }
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(datasource, url, username, password,
                connectionCount != null ? connectionCount : maximum);
        databaseConfiguration.setDriver(helper.getOptionalString(key + Database.DRIVER));
        databaseConfiguration.setName(helper.getOptionalString(databaseKey + Database.NAME));
        databaseConfiguration.setCatalog(helper.getOptionalString(databaseKey + Database.CATALOG));
        databaseConfiguration.setSchema(helper.getOptionalString(databaseKey + Database.SCHEMA));
        databaseConfiguration.setRewriteBatchedStatements(rewriteBatchedStatements != null ? rewriteBatchedStatements : false);
        databaseConfiguration.setAutoCommit(autoCommit == null ? false : autoCommit);
        databaseConfiguration.setReadOnly(readOnly == null ? false : readOnly);
        databaseConfiguration.setIdentifier(databaseKey);
        return databaseConfiguration;
    }

    public boolean existInConfig(String key) {
        return helper.existInConfig(key);
    }
}
