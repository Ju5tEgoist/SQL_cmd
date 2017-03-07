package com.company.controller.command;

import com.company.controller.query.builder.ClearQueryBuilder;
import com.company.controller.query.executor.UpdateSqlQueryExecutor;
import com.company.controller.query.parameter.provider.ClearParametersProvider;

/**
 * Created by yulia on 21.02.17.
 */
public class Clear extends AbstractCommand {

    public Clear() {
        super(new ClearParametersProvider(), new ClearQueryBuilder(), new UpdateSqlQueryExecutor());
    }

    @Override
    public boolean shouldExecute(String command) {
        return "clear".equals(command);
    }
}
