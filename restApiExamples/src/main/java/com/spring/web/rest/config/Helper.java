package com.spring.web.rest.config;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

import static com.spring.web.rest.config.Constant.*;


public class Helper {
    protected final Logger logger	= LoggerFactory.getLogger(this.getClass());
    private Config applicationConfiguration;

    Helper(Config applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    final String applicationPath(String key) {
        return  key;
    }

    final String fcbsPath(String key) {
        return "fcbs." + key;
    }

    public String getString(String key) {
        String path = this.applicationPath(fcbsPath(key));
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getString(path);
        }
        throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
    }

    public int getInt(String key) {
        String path = this.applicationPath(fcbsPath(key));
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getInt(path);
        }
        throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
    }

    public long getLong(String key) {
        String path = this.applicationPath(fcbsPath(key));
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getLong(path);
        }
        throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
    }

    public boolean getBoolean(String key) {
        String path = this.applicationPath(fcbsPath(key));
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getBoolean(path);
        }
        throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
    }

    public Config getConfig(String key) {
        String path = this.applicationPath(fcbsPath(key));
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getConfig(path);
        }
        throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
    }

    public DatabaseConfiguration getDatabaseConfiguration(String databaseKey, boolean noEncryptionMode) {
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
        String datasource = getString(key + Constant.Database.DATA_SOURCE);
        String url = getString(key + Database.URL);
        String username = getString(databaseKey + Database.USERNAME);
        String password = getString(databaseKey + Database.PASSWORD);
        Integer maximum = getOptionalInteger(databaseKey + Database.MAXIMUM_CONNECTION);
        Integer connectionCount = getOptionalInteger(databaseKey + Database.CONNECTION_COUNT);
        Integer connectionTimeout = getOptionalInteger(databaseKey + Database.CONNECTION_TIMEOUT);
        Boolean rewriteBatchedStatements = getOptionalBoolean(databaseKey + ".rewriteBatchedStatements");
        Boolean autoCommit = getOptionalBoolean(databaseKey + ".autoCommit");
        Boolean readOnly = getOptionalBoolean(databaseKey + ".readOnly");
        if (connectionCount == null && maximum == null) {
            throw new RuntimeException("There is no configuration for [Key: connectionCount]!");
        }
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(datasource, url, username, password,
                connectionCount != null ? connectionCount : maximum);
        databaseConfiguration.setDriver(getOptionalString(key + Database.DRIVER));
        databaseConfiguration.setName(getOptionalString(databaseKey + Database.NAME));
        databaseConfiguration.setCatalog(getOptionalString(databaseKey + Database.CATALOG));
        databaseConfiguration.setSchema(getOptionalString(databaseKey + Database.SCHEMA));
        databaseConfiguration.setRewriteBatchedStatements(rewriteBatchedStatements != null ? rewriteBatchedStatements : false);
        databaseConfiguration.setAutoCommit(autoCommit == null ? false : autoCommit);
        databaseConfiguration.setReadOnly(readOnly == null ? false : readOnly);
        databaseConfiguration.setIdentifier(databaseKey);
        return databaseConfiguration;
    }

    public List<String> getOptionalStringList(String key) {
        String path = this.applicationPath(key);
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getStringList(path);
        }
        return null;
    }

    public List<Long> getOptionalLongList(String key) {
        String path = this.applicationPath(key);
        if (applicationConfiguration.hasPath(path)) {
            return applicationConfiguration.getLongList(path);
        }
        return null;
    }

    public List<String> getStringList(String key) {
        List<String> list = getOptionalStringList(key);
        if (list == null)
            throw new RuntimeException("There is no configuration for [Key: " + key + "]!");
        return list;
    }

    public String getOptionalString(String key) {
        try {
            return getString(key);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public Integer getOptionalInteger(String key) {
        try {
            return getInt(key);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public Boolean getOptionalBoolean(String key) {
        try {
            return getBoolean(key);
        } catch (RuntimeException e) {
            return null;
        }
    }


    public boolean existInConfig(String key) {
        String path = this.applicationPath(key);
        if (applicationConfiguration.hasPath(path)) {
            return true;
        }
        logger.info("There is no configuration for [Key:{}]!", key);
        return false;
    }
}
