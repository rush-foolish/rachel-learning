package com.company.logentity;

import com.company.factory.LogType;

public class DataEnrichmentLog extends LogEntity{
    private Integer runID;
    private String formName;

    private DataEnrichmentLog(DataEnrichmentLog.Builder builder) {
        super(builder);
        this.runID = builder.runID;
        this.formName = builder.formName;

    }

    public LogType getComponent() {
        return LogType.DATAENRICHMENT;
    }

    public static class Builder extends LogEntity.Builder<DataEnrichmentLog.Builder> {
        private Integer runID;
        private String formName;

        public Builder (Integer procId) {
            super(procId);
            super.component(LogType.DATAENRICHMENT.name());
        }


        public DataEnrichmentLog.Builder runID(Integer runID) {
            this.runID = runID;
            return this;
        }

        public DataEnrichmentLog.Builder formName(String formName) {
            this.formName = formName;
            return this;
        }



        @Override
        public DataEnrichmentLog build() {
            return new DataEnrichmentLog(this);
        }


    }
}
