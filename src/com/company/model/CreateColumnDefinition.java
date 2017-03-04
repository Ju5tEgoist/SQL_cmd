package com.company.model;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateColumnDefinition {
    private String name;
    private String dataType;
    private boolean nullable;
    private String defaultValue;

    public CreateColumnDefinition() {
    }

    private CreateColumnDefinition(Builder builder) {
        name = builder.name;
        dataType = builder.dataType;
        nullable = builder.nullable;
        defaultValue = builder.defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String dataType;
        private boolean nullable;
        private String defaultValue;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder dataType(String dataType){
            this.dataType = dataType;
            return this;
        }

        public Builder nullable(boolean nullable){
            this.nullable = nullable;
            return this;
        }

        public Builder defaultValue(String defaultValue){
            this.defaultValue = null;
            return this;
        }
        public CreateColumnDefinition build(){
            return new CreateColumnDefinition(this);
        }
    }
}
