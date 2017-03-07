package com.company.controller.command;

import com.company.controller.query.builder.CreateQueryBuilder;
import com.company.controller.query.executor.UpdateSqlQueryExecutor;
import com.company.controller.query.parameter.provider.CreateParametersProvider;

/**
 * Created by yulia on 21.02.17.
 */
public class Create extends AbstractCommand {
    public Create() {
        super(new CreateParametersProvider(), new CreateQueryBuilder(), new UpdateSqlQueryExecutor());
    }

    @Override
    public boolean shouldExecute(String command) {
        return "create".equals(command);
    }

}
