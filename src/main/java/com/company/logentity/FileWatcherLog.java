package com.company.logentity;

import com.company.factory.LogType;

public class FileWatcherLog extends LogEntity {

    private Integer runID;
    private String fileName;
    private Integer recCounts;

    private FileWatcherLog(Builder builder) {
        super(builder);
        this.runID = builder.runID;
        this.fileName = builder.fileName;
        this.recCounts = builder.recCounts;

    }

    public LogType getComponent() {
        return LogType.FILEWATCHER;
    }

    public static class Builder extends LogEntity.Builder<Builder> {
        private Integer runID;
        private String fileName;
        private Integer recCounts;

        public Builder (Integer procID) {
            super(procID);
            super.component(LogType.FILEWATCHER.name());
        }


        public Builder runID(Integer runID) {
            this.runID = runID;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder recCounts(Integer recCounts) {
            this.recCounts = recCounts;
            return this;
        }

        @Override
        public FileWatcherLog build() {
            return new FileWatcherLog(this);
        }


    }


}
