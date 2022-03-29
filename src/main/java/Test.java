import com.datastax.driver.core.Cluster;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.batch.connectors.cassandra.CassandraInputFormatBase;
import org.apache.flink.batch.connectors.cassandra.CassandraPojoInputFormat;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.cassandra.ClusterBuilder;

public class Test {
    public void run() throws Exception {
        long t1 = System.currentTimeMillis();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String cql = "select * from data_usage where year=2022 and month=3 ALLOW FILTERING";
        DataStreamSource<DataUsageByCompanySimCard> dataSet = env.createInput(
                executeQuery(cql, DataUsageByCompanySimCard.class),
                        TypeInformation.of(new TypeHint<DataUsageByCompanySimCard>() {}));

        final long[] res = {0, 0, 0};
       // dataSet.print();
       dataSet.keyBy("year")
               .reduce((d1, d2) -> {
                   d1.setBytesIn(d1.getBytesIn() + d2.getBytesIn());
                   d1.setBytesOut(d1.getBytesOut() + d2.getBytesOut());
                   return d1;
               })
               .addSink(new SinkFunction<>() {
           @Override
           public void invoke(DataUsageByCompanySimCard value, Context context) throws Exception {
               res[0]++;
               res[1] = value.getBytesIn();
               res[2] = value.getBytesOut();
           }

           @Override
           public void finish() throws Exception {
               System.out.println(res[0] + "," + res[1] + "," + res[2]);
               System.out.println(System.currentTimeMillis() - t1);
           }
       });

        env.execute("hao test");
    }

    public static CassandraInputFormatBase executeQuery(String cql, Class clazz){

        ClusterBuilder builder = new ClusterBuilder() {
            private static final long serialVersionUID = 1;

            @Override
            protected Cluster buildCluster(com.datastax.driver.core.Cluster.Builder builder) {
                return builder.addContactPoints("localhost")
                        .withPort(9042)
                        .withClusterName("datacenter1")
                        .build();
            }
        };
        return new CassandraPojoInputFormat(cql, builder, clazz);

    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        t.run();
    }
}
