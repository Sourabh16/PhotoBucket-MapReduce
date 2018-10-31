import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhotoReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private Map<String, Integer> countMap = new HashMap<>();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) {
        int sumValue = 0;
        for (IntWritable value : values) {
            sumValue += value.get();
            countMap.put(key.toString(), sumValue);
        }
//        context.write(key, new IntWritable(sumValue));

//        Map<String, Integer> stringIntegerMap = utils.sortMapByValue(countMap);
//        context.write(stringIntegerMap., new IntWritable(sum));
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        Map<String, Integer> stringIntegerMap = utils.sortMapByValue(countMap);
        for (String mapKey : stringIntegerMap.keySet()) {
            context.write(new Text(mapKey), new IntWritable(stringIntegerMap.get(mapKey)));
        }
    }
}