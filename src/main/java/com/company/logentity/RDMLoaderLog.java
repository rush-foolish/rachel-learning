package com.company.logentity;

import com.company.factory.LogType;

public class RDMLoaderLog extends LogEntity {
    private Integer runID;
    private String formName;
    private String type;
//    private String message;
    private Integer originalRuleCnt;
    private Integer resultRuleCnt;
    private Integer resultMetaRuleCnt;

    private RDMLoaderLog(RDMLoaderLog.Builder builder) {
        super(builder);
        this.runID = builder.runID;
        this.formName = builder.formName;
        this.type = builder.type;
//        this.message = builder.message;
        this.originalRuleCnt = builder.originalRuleCnt;
        this.resultRuleCnt = builder.resultRuleCnt;
        this.resultMetaRuleCnt = builder.resultMetaRuleCnt;
    }

    public LogType getComponent() {
        return LogType.RDMLOADER;
    }

    public static class Builder extends LogEntity.Builder<RDMLoaderLog.Builder> {
        private Integer runID;
        private String formName;
        private String type;
//        private String message;
        private Integer originalRuleCnt;
        private Integer resultRuleCnt;
        private Integer resultMetaRuleCnt;

        public Builder(Integer procId) {
            super(procId);
            super.component(LogType.RDMLOADER.name());
        }

        public RDMLoaderLog.Builder runID(Integer runID) {
            this.runID = runID;
            return this;
        }

        public RDMLoaderLog.Builder formName(String formName) {
            this.formName = formName;
            return this;
        }

        public RDMLoaderLog.Builder type(String type) {
            this.type = type;
            return this;
        }

//        public RDMLoaderLog.Builder message(String message) {
//            this.message = message;
//            return this;
//        }

        public RDMLoaderLog.Builder originalRuleCnt(Integer originalRuleCnt) {
            this.originalRuleCnt = originalRuleCnt;
            return this;
        }

        public RDMLoaderLog.Builder resultRuleCnt(Integer resultRuleCnt) {
            this.resultRuleCnt = resultRuleCnt;
            return this;
        }

        public RDMLoaderLog.Builder resultMetaRuleCnt(Integer resultMetaRuleCnt) {
            this.resultMetaRuleCnt = resultMetaRuleCnt;
            return this;
        }

        @Override
        public RDMLoaderLog build() {
            return new RDMLoaderLog(this);
        }

    }
}
