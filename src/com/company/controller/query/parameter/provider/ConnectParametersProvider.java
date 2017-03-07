package com.company.controller.query.parameter.provider;

import com.company.controller.query.parameter.ConnectParameters;

/**
 * Created by yulia on 05.03.17.
 */
public class ConnectParametersProvider extends ConsoleParametersProvider {
    @Override
    public ConnectParameters getParameters() {
        ConnectParameters parameters = new ConnectParameters();
        view.write("Please enter database name:");
        parameters.setDatabase(view.read());
        view.write("Please enter user name:");
        parameters.setUserName(view.read());
        view.write("Please enter password:");
        parameters.setPassword(view.read());
        return parameters;
    }
}
