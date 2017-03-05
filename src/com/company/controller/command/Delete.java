package com.company.controller.command;

import com.company.controller.query.builder.DeleteTableQueryBuilder;
import com.company.controller.query.parameter.provider.ClearParametersProvider;
import com.company.model.exception.CommandExecutionException;
import com.company.view.TablePresenter;

import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Delete implements Command {
    private ClearParametersProvider clearParametersProvider;

    public Delete() {
        this.clearParametersProvider = new ClearParametersProvider();
    }

    @Override
    public boolean shouldExecute(String command) {
        return "delete".equals(command);
    }

    @Override
    public void execute() throws CommandExecutionException, SQLException {
        TablePresenter tablePresenter = new TablePresenter();
        String tableName = clearParametersProvider.getParameters().getTableName();
        tablePresenter.showColumns(tableName);
        tablePresenter.showTable(tableName);
        DeleteTableQueryBuilder updateTableQueryBuilder = new DeleteTableQueryBuilder();
        updateTableQueryBuilder.build(tableName);
        tablePresenter.showTable(tableName);
    }
}
