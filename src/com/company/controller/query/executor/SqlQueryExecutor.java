package com.company.controller.query.executor;

import java.sql.SQLException;

/**
 * Created by yulia on 05.03.17.
 */
public interface SqlQueryExecutor {
    void execute(String query) throws SQLException;
}
