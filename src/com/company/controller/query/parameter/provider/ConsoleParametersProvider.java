package com.company.controller.query.parameter.provider;

import com.company.view.ScannerConsoleReader;

/**
 * Created by yulia on 05.03.17.
 */
public abstract class ConsoleParametersProvider implements ParametersProvider {

    private ScannerConsoleReader scannerConsoleReader;

    protected ConsoleParametersProvider() {
        this.scannerConsoleReader = new ScannerConsoleReader();
    }

    protected String getConsoleInput()
    {
       return scannerConsoleReader.read();
    }
}
