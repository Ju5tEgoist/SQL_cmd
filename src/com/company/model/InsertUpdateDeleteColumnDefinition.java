package com.company.model;

/**
 * Created by yulia on 22.02.17.
 */
public class InsertUpdateDeleteColumnDefinition {
    private String name;
    private String value;


    public InsertUpdateDeleteColumnDefinition() {
    }

    private InsertUpdateDeleteColumnDefinition(Builder builder) {
        name = builder.name;

        value = builder.value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String value;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder value(String value){
            this.value = value;
            return this;
        }
        public InsertUpdateDeleteColumnDefinition build(){
            return new InsertUpdateDeleteColumnDefinition(this);
        }
    }
}
