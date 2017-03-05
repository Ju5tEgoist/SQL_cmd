package com.company.controller.query.parameter.provider;

import com.company.controller.query.parameter.ConnectParameters;

/**
 * Created by yulia on 05.03.17.
 */
public class ConnectParametersProvider extends ConsoleParametersProvider {
    @Override
    public ConnectParameters getParameters() {
        ConnectParameters parameters = new ConnectParameters();
        System.out.println("Please enter database name:");
        parameters.setDatabase(getConsoleInput());
        System.out.println("Please enter user name:");
        parameters.setUserName(getConsoleInput());
        System.out.println("Please enter password:");
        parameters.setPassword(getConsoleInput());
        return parameters;
    }
}
