package com.company.controller.query.parameter.provider;

import com.company.view.ConsoleManager;
import com.company.view.View;

/**
 * Created by yulia on 05.03.17.
 */
public abstract class ConsoleParametersProvider implements ParametersProvider {

    protected View view;
    protected ConsoleParametersProvider() {
        this.view = new ConsoleManager();
    }
}
