package com.company.controller.query.builder;

import com.company.controller.query.parameter.Parameters;

/**
 * Created by yulia on 05.03.17.
 */
public class DropQueryBuilder implements QueryBuilder<Parameters> {
    @Override
    public String build(Parameters parameters) {
        return "DROP TABLE " + parameters.getTableName();
    }
}
