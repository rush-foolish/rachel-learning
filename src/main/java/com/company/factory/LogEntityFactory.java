//package com.company.factory;
//
////import com.company.logentity.DataEnrichmentLog;
//import com.company.logentity.FileWatcherLog;
//import com.company.logentity.LogEntity;
//
//
//public class LogEntityFactory {
//
//
//
//    public LogEntity getLog(LogType logType) {
//
//        if (logType == null) {
//            return null;
//        }
//
//        if (logType == LogType.FIlEWATER) {
//            return new FileWatcherLog();
//        } else if (logType == LogType.DATAENRICHMENT) {
//            return new DataEnrichmentLog();
//        } else {
//            return null;
//        }
//    }
//}
