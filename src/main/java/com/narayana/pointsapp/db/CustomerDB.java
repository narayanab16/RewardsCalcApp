package com.narayana.pointsapp.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    private static Logger LOG = LoggerFactory.getLogger(CustomerDB.class);
    private static List<Customer> customersList = null;
    public static List<Customer> getCustomers(LocalDate startDate, LocalDate endDate) {
        LOG.debug("static customers");
        if(customersList == null) {
            customersList = new ArrayList<>();
            // Assumption : records are ordered on date ASC based on 3 months period : sql query
            // select name, txDate, spendAmount from customers where  txDate between '2022-01-01' and '2022-03-31'
            Customer c1 = new Customer("Narayana", LocalDate.of(2022,1,1), 122.45 );
            Customer c2 = new Customer("Basetty", LocalDate.of(2022,1,1), 52.14 );
            Customer c3 = new Customer("Narayana", LocalDate.of(2022,1,2), 122.45 );
            Customer c4 = new Customer("Basetty", LocalDate.of(2022,1,2), 52.14 );
            customersList.add(c1);
            customersList.add(c2);
            customersList.add(c3);
            customersList.add(c4);
        }
        return customersList;
    }
}
