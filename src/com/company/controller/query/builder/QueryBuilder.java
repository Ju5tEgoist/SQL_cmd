package com.company.controller.query.builder;

import com.company.controller.query.parameter.Parameters;

import java.sql.SQLException;

/**
 * Created by yulia on 05.03.17.
 */
public interface QueryBuilder <P extends Parameters>{
     String build(P parameters) throws SQLException;
}
