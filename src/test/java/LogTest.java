import com.company.LogProducer;
//import com.company.factory.LogEntityFactory;
//import com.company.factory.LogFactory;
import com.company.ProcessMaker;
import com.company.factory.LogType;
import com.company.logentity.FileStagingLog;
import com.company.logentity.FileWatcherLog;
import com.company.logentity.LogEntity;
import com.sun.deploy.util.StringUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.omg.CORBA.Environment;
import sun.nio.cs.ext.MacThai;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogTest {


    private static String[] statusList = {"start", "waiting", "running", "success"};
    private static String[] products = {"CAD_BAL", "CAD_IND", "DEB_BAL", "DEB_IND", "EAST", "OPTIMA"};
    private static String[] component = {"common-file-watcher", "file-staging", "data-enrichment", "data-unloading", "rule-parser", "rdm-loader"};
    private static String[] asOfDateList = {"2020-07-31", "2020-08-01", "2020-08-02", "2020-08-03", "2020-08-04", "2020-08-05"};


    public static void main(String[] args)  throws Exception {
        String env = "test";
        LogProducer lp = LogProducer.getInstance();

        ExecutorService execPool = Executors.newFixedThreadPool(2);
        List<String> reports = Arrays.asList(products);
        int a = 4567, b = 23242;
        //int num=a+(int)(Math.random()*(b-a+1));
        reports.parallelStream().forEach(report ->
                execPool.submit(new ProcessMaker(lp, report,a+(int)(Math.random()*(b-a+1)), env)));

    }

}
