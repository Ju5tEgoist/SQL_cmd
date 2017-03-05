package com.company.controller.command;

import com.company.controller.query.parameter.ConnectParameters;
import com.company.controller.query.parameter.provider.ConnectParametersProvider;
import com.company.model.DatabaseManager;
import com.company.model.exception.CommandExecutionException;

/**
 * Created by yulia on 06.11.16.
 */
public class Connect implements Command {
    private ConnectParametersProvider parametersProvider;

    public Connect() {
        this.parametersProvider = new ConnectParametersProvider();
    }

    @Override
    public boolean shouldExecute(String command) {
        return "connect".equals(command);
    }

    @Override
    public void execute() throws CommandExecutionException {
        ConnectParameters parameters = parametersProvider.getParameters();
        boolean connectionSuccessful = false;
        while (!connectionSuccessful) {
            try {
                connectionSuccessful = DatabaseManager.connect(parameters.getDatabase(), parameters.getUserName(), parameters.getPassword()) != null;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Check your entries and try again");
            }
        }

    }
}
