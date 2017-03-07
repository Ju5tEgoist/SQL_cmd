package com.company.controller.command;

import com.company.controller.query.parameter.ConnectParameters;
import com.company.controller.query.parameter.provider.ConnectParametersProvider;
import com.company.model.DatabaseManager;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ConsoleManager;
import com.company.view.View;

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
        View view = new ConsoleManager();
        ConnectParameters parameters = parametersProvider.getParameters();
        boolean connectionSuccessful = false;
        while (!connectionSuccessful) {
            try {
                connectionSuccessful = DatabaseManager.connect(parameters.getDatabase(), parameters.getUserName(), parameters.getPassword()) != null;
            } catch (Exception e) {
                view.write(e.toString());
                view.write("Check your entries and try again");
            }
        }

    }
}
