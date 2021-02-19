package com.spring.web.rest.service;

import com.spring.web.rest.procedure.ProcedureCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
    static{
        ProcedureCache procedureCache = new ProcedureCache();
        try {
            procedureCache.loadProcedureConf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Autowired
    protected final JdbcTemplate jdbcTemplate;

    public BaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
