package com.spring.web.rest.config;

import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.Level;

class DatabaseConfiguration {
   private String       datasource;
   private String       url;
   private String       username;
   private String       password;
   private int          connectionCount;
   private int          connectionTimeout         = 2000;
   private String       driver;
   private String       name;
   private String       catalog;
   private String       schema;
   private boolean      createJDBCDatasource      = true;
   private boolean      rewriteBatchedStatements;
   private Boolean      autoCommit = false;
   private Boolean      readOnly                  = false;
   private String       identifier                = "noNameDB-" + System.currentTimeMillis();
   private boolean      reconnectOnConnectionLoss = false;
   private String moduleIdentifier;
   private Optional<Level>	logExecutionTimeLevel		= Optional.empty();
   private Optional<Long>	logExecutionTimeLimit		= Optional.empty();

   public DatabaseConfiguration(String datasource, String url, String username, String password, int connectionCount) {
       super();
       this.datasource = datasource;
       this.url = url;
       this.username = username;
       this.password = password;
       this.connectionCount = connectionCount;
   }

   /**
    * Creates new DatabaseConfiguration Object
    * @deprecated
    * Minimum and Maximum connection no longer supported.
    * Now using connectionCount. Maximum connection will accepted as connectionCount
    * Minimum parameter on this method has no impact!
    * <p> Use {@link DatabaseConfiguration#DatabaseConfiguration(String, String, String, String, int)}  instead.
    */
   @Deprecated
   public DatabaseConfiguration(String datasource, String url, String username, String password, int minimum, int maximum) {
       super();
       this.datasource = datasource;
       this.url = url;
       this.username = username;
       this.password = password;
       this.connectionCount = maximum;
   }

   public String getDatasource() {
       return datasource;
   }

   public String getUrl() {
       return url;
   }

   public String getUsername() {
       return username;
   }

   public String getPassword() {
       return password;
   }

   public int getConnectionCount() {
       return connectionCount;
   }

   public String getDriver() {
       return driver;
   }

   public void setDriver(String driver) {
       this.driver = driver;
       Objects.requireNonNull(driver, "@driver");
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getCatalog() {
       return catalog;
   }

   public void setCatalog(String catalog) {
       this.catalog = catalog;
   }

   public String getSchema() {
       return schema;
   }

   public void setSchema(String schema) {
       this.schema = schema;
   }

   public boolean isCreateJDBCDatasource() {
       return createJDBCDatasource;
   }

   public void setCreateJDBCDatasource(boolean createJDBCDatasource) {
       this.createJDBCDatasource = createJDBCDatasource;
   }

   public boolean isRewriteBatchedStatements() {
       return rewriteBatchedStatements;
   }

   public void setRewriteBatchedStatements(boolean rewriteBatchedStatements) {
       this.rewriteBatchedStatements = rewriteBatchedStatements;
   }

   public Boolean getAutoCommit() {
       return autoCommit;
   }

   public void setAutoCommit(Boolean autoCommit) {
       this.autoCommit = autoCommit;
   }

   public Boolean getReadOnly() {
       return readOnly;
   }

   public void setReadOnly(Boolean readOnly) {
       this.readOnly = readOnly;
   }

   public String getIdentifier() {
       return identifier;
   }

   public void setIdentifier(String identifier) {
       this.identifier = identifier;
   }

   public boolean isReconnectOnConnectionLoss() {
       return reconnectOnConnectionLoss;
   }

   public void setReconnectOnConnectionLoss(boolean reconnectOnConnectionLoss) {
       this.reconnectOnConnectionLoss = reconnectOnConnectionLoss;
   }

   public int getConnectionTimeout() {
       return connectionTimeout;
   }

   public void setConnectionTimeout(int connectionTimeout) {
       this.connectionTimeout = connectionTimeout;
   }

   public void setConnectionCount(int connectionCount) {
       this.connectionCount = connectionCount;
   }

   public String getModuleIdentifier() {
       return moduleIdentifier;
   }

   public void setModuleIdentifier(String moduleIdentifier) {
       this.moduleIdentifier = moduleIdentifier;
   }


   public Optional<Level> getLogExecutionTimeLevel() {
       return logExecutionTimeLevel;
   }

   public void setLogExecutionTimeLevel(Optional<Level> logExecutionTimeLevel) {
       this.logExecutionTimeLevel = logExecutionTimeLevel;
   }

   public Optional<Long> getLogExecutionTimeLimit() {
       return logExecutionTimeLimit;
   }

   public void setLogExecutionTimeLimit(Optional<Long> logExecutionTimeLimit) {
       this.logExecutionTimeLimit = logExecutionTimeLimit;
   }

   @Override
   public String toString() {
       return "DatabaseConfiguration{" +
               ", identifier='" + identifier + '\'' +
               ", datasource='" + datasource + '\'' +
               ", url='" + url + '\'' +
               ", username='" + username + '\'' +
               ", connectionCount=" + connectionCount +
               ", driver='" + driver + '\'' +
               ", schema='" + schema + '\'' +
               ", readOnly='" + readOnly + '\'' +
               '}';
   }
}

