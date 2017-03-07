package com.company.controller.query.parameter.provider;

import com.company.controller.query.parameter.CreateParameters;
import com.company.controller.query.parameter.Parameters;

/**
 * Created by yulia on 05.03.17.
 */
public class CreateParametersProvider extends ConsoleParametersProvider {
    @Override
    public Parameters getParameters() {
        view.write("Please, type the number of columns");
        CreateParameters createParameters = new CreateParameters();
        createParameters.setColumnNumber(Integer.valueOf(view.read()));
        return createParameters;
    }
}
