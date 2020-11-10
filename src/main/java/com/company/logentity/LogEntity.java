package com.company.logentity;

import com.company.factory.LogType;
import com.google.gson.Gson;

public abstract class LogEntity {

    private Integer procID;
    private String env;
    private String asOfdate;
    private String component;
    private String startTime;
    private String status;
    private String hostName;
    private String message;
    private String logFile;
    private String errFile;

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public abstract LogType getComponent();

    public LogEntity(Builder builder) {
        this.procID = builder.procID;
        this.env = builder.env;
        this.asOfdate = builder.asOfdate;
        this.component = builder.component;
        this.startTime = builder.startTime;
        this.status = builder.status;
        this.hostName = builder.hostName;
        this.message = builder.message;
        this.logFile = builder.logFile;
        this.errFile = builder.errFile;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private Integer procID;
        private String env;
        private String asOfdate;
        private String component;
        private String startTime;
        private String status;
        private String hostName;
        private String message;
        private String logFile;
        private String errFile;

        public Builder(Integer procID) {
            this.procID = procID;
        }

        private T self() {
            return (T) this;
        }

//        public T procID(Integer procID) {
//            this.procID = procID;
//            return self();
//        }

        public T env(String env) {
            this.env = env;
            return self();
        }

        public T asOfdate(String asOfdate) {
            this.asOfdate = asOfdate;
            return self();
        }

        public T component(String component) {
            this.component = component;
            return self();
        }

        public T startTime(String startTime) {
            this.startTime = startTime;
            return self();
        }

        public T status(String status) {
            this.status = status;
            return self();
        }

        public T hostName(String hostName) {
            this.hostName = hostName;
            return self();
        }

        public T message(String message) {
            this.message = message;
            return self();
        }

        public T logFile(String logFile) {
            this.logFile = logFile;
            return self();
        }

        public T errFile(String errFile) {
            this.errFile = errFile;
            return self();
        }

        public abstract LogEntity build();

    }



}
