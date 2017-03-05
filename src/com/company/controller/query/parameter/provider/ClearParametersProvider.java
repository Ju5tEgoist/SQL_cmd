package com.company.controller.query.parameter.provider;

import com.company.controller.query.parameter.Parameters;

/**
 * Created by yulia on 05.03.17.
 */
public class ClearParametersProvider extends ConsoleParametersProvider {
    @Override
    public Parameters getParameters() {
        Parameters parameters = new Parameters();
        System.out.println("Enter table name");
        parameters.setTableName("public." + getConsoleInput());
        return parameters;
    }
}
