package com.company.logentity;

import com.company.factory.LogType;

import java.io.File;

public class FileStagingLog extends LogEntity {


    private Integer runID;
    private String moduleName;
    private String fileName;
    private Integer fileRecCount;
    private Integer loadRecCount;
//    private String message;

    private FileStagingLog(FileStagingLog.Builder builder) {
        super(builder);
        this.runID = builder.runID;
        this.moduleName = builder.moduleName;
        this.fileName = builder.fileName;
        this.fileRecCount = builder.fileRecCount;
        this.loadRecCount = builder.loadRecCount;
//        this.message = builder.message;
    }

    public LogType getComponent() {
        return LogType.FILESTAGING;
    }

    public static class Builder extends LogEntity.Builder<FileStagingLog.Builder> {
        private Integer runID;
        private String moduleName;
        private String fileName;
        private Integer fileRecCount;
        private Integer loadRecCount;
//        private String message;

        public Builder(Integer procId) {
            super(procId);
            super.component(LogType.FILESTAGING.name());
        }

        public FileStagingLog.Builder runID(Integer runID) {
            this.runID = runID;
            return this;
        }

        public FileStagingLog.Builder moduleName(String moduleName) {
            this.moduleName = moduleName;
            return this;
        }

        public FileStagingLog.Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public FileStagingLog.Builder fileRecCount(Integer fileRecCount) {
            this.fileRecCount = fileRecCount;
            return this;
        }

        public FileStagingLog.Builder loadRecCount(Integer loadRecCount) {
            this.loadRecCount = loadRecCount;
            return this;
        }

//        public FileStagingLog.Builder message(String message) {
//           this.message = message;
//           return this;
//        }

        @Override
        public FileStagingLog build() {
            return new FileStagingLog(this);
        }


    }


}
