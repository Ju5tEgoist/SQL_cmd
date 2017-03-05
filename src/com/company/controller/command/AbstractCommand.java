package com.company.controller.command;

import com.company.controller.query.builder.QueryBuilder;
import com.company.controller.query.executor.SqlQueryExecutor;
import com.company.controller.query.parameter.Parameters;
import com.company.controller.query.parameter.provider.ParametersProvider;
import com.company.model.exception.CommandExecutionException;

import java.sql.SQLException;

/**
 * Created by yulia on 02.03.17.
 */
public abstract class AbstractCommand implements Command {
    private SqlQueryExecutor sqlQueryExecutor;
    private QueryBuilder queryBuilder;
    private ParametersProvider parametersProvider;

    public AbstractCommand(ParametersProvider parametersProvider, QueryBuilder queryBuilder, SqlQueryExecutor sqlQueryExecutor) {
        this.parametersProvider = parametersProvider;
        this.queryBuilder = queryBuilder;
        this.sqlQueryExecutor = sqlQueryExecutor;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            Parameters parameters = getParametersProvider().getParameters();
            String query = getQueryBuilder().build(parameters);
            getSqlQueryExecutor().execute(query);
        } catch (SQLException e) {
            throw new CommandExecutionException(e);
        }
    }

    public SqlQueryExecutor getSqlQueryExecutor() {
        return sqlQueryExecutor;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public ParametersProvider getParametersProvider() {
        return parametersProvider;
    }
}
