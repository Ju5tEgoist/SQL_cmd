package com.company;

import java.sql.SQLException;

/**
 * Created by yulia on 30.10.16.
 */
public interface TableList {
    String[] getAllTableNames(String database) throws SQLException;
    void getTableForView(String database) throws SQLException;
}
