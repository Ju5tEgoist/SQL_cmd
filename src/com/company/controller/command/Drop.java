package com.company.controller.command;

import com.company.controller.query.builder.DropQueryBuilder;
import com.company.controller.query.executor.UpdateSqlQueryExecutor;
import com.company.controller.query.parameter.Parameters;
import com.company.controller.query.parameter.provider.ClearParametersProvider;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ConsoleManager;
import com.company.view.View;

/**
 * Created by yulia on 21.02.17.
 */
public class Drop extends AbstractCommand {

    public Drop() {
        super(new ClearParametersProvider(), new DropQueryBuilder(), new UpdateSqlQueryExecutor());
    }

    @Override
    public boolean shouldExecute(String command) {
        return "drop".equals(command);
    }

    @Override
    public void execute() throws CommandExecutionException {
        super.execute();
        View view = new ConsoleManager();
        Parameters parameters = new Parameters();
        view.write(parameters.getTableName() + " is dropped");
    }
}
