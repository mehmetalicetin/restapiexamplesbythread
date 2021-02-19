package com.spring.web.rest.procedure;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcedureCache {
    private static final URL[] PROCEDURE_CONFS = {
            ProcedureCache.class.getResource("/static/select.conf")
    };

    private static Map<String, String> procedureMap	= new HashMap<>();

    public void loadProcedureConf() throws Exception {
        try {
            procedureMap = load(PROCEDURE_CONFS);
        } catch (Exception e) {
            throw new Exception("loadProcedureConf()", e);
        }
    }

    public Map<String, String> load(URL... urls) throws Exception {
        procedureMap.clear();
        for (URL url : urls) {
            Config rootConfig = ConfigFactory.parseURL(url).resolve();
            load(rootConfig, url.getPath());
        }
        return procedureMap;
    }

    private void load(Config rootConfig, String resourceIdentifier) throws Exception {
        try {
            if (!rootConfig.hasPath("DATA")) {
                throw new Exception(resourceIdentifier + " MUST include 'DATA' config!");
            }
            Config config = rootConfig.getConfig("DATA");
            if (!config.hasPath("queries")) {
                throw new Exception(resourceIdentifier + " 'DATA' MUST include 'queries' element!");
            }
            List<? extends Config> conflist = config.getConfigList("queries");
            for (Config conf : conflist) {
                parseConfig(conf);
            }
        } catch (Exception t) {
            throw new Exception("Cannot load procedures from :" + resourceIdentifier, t);
        }
    }

    private void parseConfig(Config config) throws  Exception {
        String name = config.getString("name");
        if (procedureMap.containsKey(name)) {
            throw new Exception("Name : " + name + " collision. Processed before!");
        }

        String sql = config.getString("sql").replaceAll("[\\r\\n\\t]", " ");
        procedureMap.put(name, sql);
    }

    public static String getProcedure(String procedureName) {
        String procedure = getOptionalProcedure(procedureName);
        if (procedure == null) {
            throw new IllegalArgumentException(
                    "procedure:" + procedureName + " is not defined in " + Arrays.toString(PROCEDURE_CONFS) + " or not mapped correctly");
        }
        return procedure;
    }

    private static String getOptionalProcedure(String procedureName) {
        return procedureMap.get(procedureName);
    }

}
