import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class PhotoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String inputValue = value.toString();
        String[] field = inputValue.split("\t", -1);
        if (field[10].length() > 0) {
            String lowerLine = field[10].toLowerCase();
            String[] userTags = lowerLine.split(",", -1);
            for (String tag : userTags) {
                StringTokenizer itr = new StringTokenizer(tag);
                while (itr.hasMoreTokens()) {
                    String separatedTag = itr.nextToken().trim();
                    context.write(new Text(separatedTag), one);

//                    if (countMap.containsKey(word)) {
//                        countMap.put(word, countMap.get(word) + 1);
//                    } else {
//                        countMap.put(word, 1);
//                    }
                }
            }
        }
    }


    //    @Override
//    protected void cleanup(Context context) throws IOException, InterruptedException {
//        Map<String, Integer> stringIntegerMap = utils.sortMapByValue(countMap);
//        for (String key : stringIntegerMap.keySet()) {
//            context.write(new Text(key), new IntWritable(stringIntegerMap.get(key)));
//        }
//    }
}