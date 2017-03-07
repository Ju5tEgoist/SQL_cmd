package com.company.controller.query.builder;

import com.company.controller.query.parameter.CreateParameters;
import com.company.controller.query.parameter.provider.ClearParametersProvider;
import com.company.model.CreateColumnDefinition;
import com.company.model.CreateColumnDefinitionPropertiesProvider;

import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateQueryBuilder implements QueryBuilder<CreateParameters>{
    @Override
    public String build(CreateParameters createParameters) {
        String properties = "";
        CreateColumnDefinitionPropertiesProvider columnDefinitionProvider = new CreateColumnDefinitionPropertiesProvider();
        List<CreateColumnDefinition> columnDefinition = columnDefinitionProvider.getProperties(createParameters.getColumnNumber());
        properties = getProperties(properties, columnDefinition);
        ClearParametersProvider clearParametersProvider = new ClearParametersProvider();
        return "CREATE TABLE " + clearParametersProvider.getParameters().getTableName() + "(" + properties + ")";
    }

    private String getProperties(String properties, List<CreateColumnDefinition> columnDefinition) {
        for (int i = 0; i < columnDefinition.size(); i++){
            properties +=  i > 0 ?  "," + " " + columnDefinition.get(i).getName() + " "
                 + columnDefinition.get(i).getDataType() + " " + columnDefinition.get(i).getDefaultValue() :
                 columnDefinition.get(i).getName() + " " + columnDefinition.get(i).getDataType()
                            + " " + columnDefinition.get(i).getDefaultValue();
        }
        return properties;
    }
}
