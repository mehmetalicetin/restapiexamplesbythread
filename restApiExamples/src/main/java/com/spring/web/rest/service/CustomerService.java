package com.spring.web.rest.service;

import com.spring.web.rest.daoimpl.DAO;
import com.spring.web.rest.model.Customer;
import com.spring.web.rest.procedure.ProcedureCache;
import com.spring.web.rest.procedure.SQLQUERIES;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class CustomerService  extends BaseService implements DAO<Customer>{

    public CustomerService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    private Customer getCustomer(ResultSet rs, int var2) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getLong("cust_id"));
        customer.setCustomerName(rs.getString("cust_name"));
        return customer;
    }

    @Override
    public Collection<Customer> findAll() {
        String sql = ProcedureCache.getProcedure(SQLQUERIES.SELECT_ALL_CUSTOMERS);
       return jdbcTemplate.query(sql,this::getCustomer);
    }
}
