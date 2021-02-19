package com.spring.web.rest.config;

public class Constant {


    static final String			CONFIGURATION_FILE					= "configuration.file";
    static final String			SINGLETON							= "application.singleton";
    static final String			ROUTE_SEARCH_ITERATION_LIMIT		= "application.route.searchIterationLimit";
    static final String			LOGGING_CONFIGURATION_FILE			= "logging.configuration.file";
    static final String			APPLICATION_NAME					= "application.name";
    static final String			CACHE_CONFIGURATION_FILE			= "cache.configuration.file";
    static final String			CACHE_CONNECTION_TYPE				= "cache.configuration.connectionType";
    static final String			CACHE_WITHOUT_RECONNECT_STRATEGY	= "cache.configuration.withoutReconnectStrategy";
    static final String			AKKA_CONFIGURATION_FILE				= "akka.configuration.file";
    static final String			SNMP_CONFIGURATION_FILE				= "snmp.configuration.file";
    static final String			SITE_REP_CACHE_CONFIG_FILE			= "siteReplication.cache.configuration.file";
    static final String			SITE_REP_CACHE						= "siteReplication.database.cache";
    static final String			PERSISTENT							= "database.persistent";
    static final String			RESOURCE_BUNDLE						= "resourceBundle";
    static final String			REQUEST								= "database.request";
    static final String			CONTROL								= "database.control";
    static final String			CACHE								= "database.cache";
    static final String			KAFKA_CONFIGURATION_FILE			= "kafka.configuration.file";
    public static final String	LICENSE_FOLDER_PATH					= "license.folder.path";
    static final String			TELNET_CONFIGURATION_FILE			= "telnet.configuration.file";
    static final String			SSH_CONFIGURATION_FILE				= "ssh.configuration.file";
    static final String			JMS_CONFIGURATION_FILE				= "jms.configuration.file";
    static final String			JMS_CHARGING_SERVER					= "jms.charging";
    static final String			JMS_BILLING_SERVER					= "jms.billing";
    static final String			JMS_AOM_SERVER						= "jms.aom";
    static final String			INITIAL_FETCHER						= "application.fetcher.initial";
    static final String			MINIMUM_FETCHER						= "application.fetcher.minimum";
    static final String			MAXIMUM_FETCHER						= "application.fetcher.maximum";
    static final String			INITIAL_PRODUCER					= "application.producer.initial";
    static final String			MINIMUM_PRODUCER					= "application.producer.minimum";
    static final String			MAXIMUM_PRODUCER					= "application.producer.maximum";
    static final String			INPUT_ROOT_DIRECTORY				= "input.root-directory";
    public static final String	NO_ENCRYPTION						= "no-encryption";
    static final String			DATA_DB								= "database.dataDb";

    static final class Database {
        static final String	URL					= ".url";
        static final String	DATA_SOURCE			= ".dataSource";
        static final String	MAXIMUM_CONNECTION	= ".maximumConnection";
        static final String	CONNECTION_COUNT	= ".connectionCount";
        static final String	CONNECTION_TIMEOUT	= ".connectionTimeout";
        static final String	USERNAME			= ".username";
        static final String	PASSWORD			= ".password";
        static final String	DRIVER				= ".driver";
        static final String	NAME				= ".name";
        static final String	CATALOG				= ".catalog";
        static final String	SCHEMA				= ".schema";
        static final String LOG					= ".log";
        static final String EXECUTION_TIME		= ".executionTime";
        static final String LEVEL				= ".level";
        static final String LIMIT				= ".limit";
    }
}
