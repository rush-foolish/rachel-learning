package com.company;

import com.company.logentity.*;
import com.sun.deploy.util.StringUtils;

public class ProcessMaker implements Runnable {
    private static String[] statusList = {"start", "waiting", "running", "success"};
    private static String[] products = {"CAD_BAL", "CAD_IND", "DEB_BAL", "DEB_IND", "EAST", "OPTIMA"};
//    private static String[] component = {"common-file-watcher", "file-staging", "data-enrichment", "data-unloading", "rule-parser", "rdm-loader"};
    private static String[] asOfDateList = {"2020-07-31", "2020-08-01", "2020-08-02", "2020-08-03", "2020-08-04", "2020-08-05"};

    private String env;
    private String report;
    private int procID;
    private LogProducer lp;

    public ProcessMaker(LogProducer logProducer, String report, int procID, String env){
        this.lp = logProducer;
        this.report = report;
        this.procID =  procID;
        this.env = env;


    }
    @Override
    public void run() {
        for (String date : asOfDateList) {
           procID = procID + 2;
            int fwRunID = getRunID(1, 5000);
            // file-watcher
            for (String status : statusList) {
                LogEntity fw = new FileWatcherLog.Builder(procID)
                        .env(env)
                        .runID(fwRunID)
                        .status(status)
                      //  .hostName("test SERVER")
                        .asOfdate(date)
                        .fileName(report + "_" + procID + ".dat")
                        .logFile("file_watcher_" + report + "_" + procID + ".log")
                        .build();
                lp.sendMessage(fw);
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //file-staging
            int fsRunID = getRunID(1, 5000);
            for (String status : statusList) {
                LogEntity fst = new FileStagingLog.Builder(procID)
                        .env(env)
                        .runID(fsRunID)
                       // .hostName("test SERVER")
                        .asOfdate(date)
                        .moduleName(report)
                        .fileName(report + "_" + procID + ".dat")
                        .status(status)
                        .fileRecCount(fsRunID+3000)
                        .loadRecCount(fsRunID+3000)
                        .build();
                lp.sendMessage(fst);
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Data-Enrichment
            int deRunID = getRunID(5001, 8000);
            for (String status : statusList) {
                LogEntity de = new DataEnrichmentLog.Builder(procID)
                        .env(env)
                        .status(status)
                        .runID(deRunID)
                        .formName(report)
                        .build();

                lp.sendMessage(de);
                try {
                    Thread.sleep(1000 * 12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Rule-Parser
            int rpRunID = getRunID(456, 9999);
            for (String status : statusList) {
                LogEntity rp = new RuleParserLog.Builder(procID)
                        .env(env)
                        .status(status)
                        //.runID(rpRunID)
                        .formName(report)
                        .ruleRecCnt((rpRunID+20)/20)
                        .message(report + "'s status is " + status)
                        .build();

                lp.sendMessage(rp);
                try {
                    Thread.sleep(1000 * 20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Rdm-Loader
            int rdmRunID = getRunID(280, 2280);
            for (String status : statusList) {
                LogEntity rdm = new RDMLoaderLog.Builder(procID)
                        .env(env)
                        .status(status)
                        .asOfdate(date)
                        .runID(rdmRunID)
                        .formName(report)
                        .type("file")
                        .originalRuleCnt((rdmRunID+50)/20)
                        .resultRuleCnt((rdmRunID+50)/20)
                        .resultMetaRuleCnt((rdmRunID+50)/4)
                        .message(report + "'s status is " + status)
                        .build();

                lp.sendMessage(rdm);
                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public Integer getRunID(int a, int b) {

        return a+(int)(Math.random()*(b-a+1));

    }


}
