package com.company.logentity;

import com.company.factory.LogType;

public class RuleParserLog extends LogEntity{
    private String formName;
    private Integer ruleRecCnt;
//    private String message;

    private RuleParserLog(RuleParserLog.Builder builder) {
        super(builder);
        this.formName = builder.formName;
        this.ruleRecCnt = builder.ruleRecCnt;
//        this.message = builder.message;

    }

    public LogType getComponent() {
        return LogType.RULEPARSER;
    }

    public static class Builder extends LogEntity.Builder<RuleParserLog.Builder> {
        private String formName;
//        private Integer runID;
        private Integer ruleRecCnt;
//        private String message;

        public Builder (Integer procID) {
            super(procID);
            super.component(LogType.RULEPARSER.name());
        }


//        public RuleParserLog.Builder runID(Integer runID) {
//            this.runID = runID;
//            return this;
//        }

        public RuleParserLog.Builder formName(String formName) {
            this.formName = formName;
            return this;
        }

        public RuleParserLog.Builder ruleRecCnt(Integer ruleRecCnt) {
            this.ruleRecCnt = ruleRecCnt;
            return this;
        }

//        public RuleParserLog.Builder message(String message) {
//            this.message = message;
//            return this;
//        }

        @Override
        public RuleParserLog build() {
            return new RuleParserLog(this);
        }


    }
}
