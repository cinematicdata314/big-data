package avgTemp;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgTemperatureMapper
extends Mapper<LongWritable, Text, Text, IntWritable> {

private static final int MISSING = 9999;

@Override
public void map(LongWritable key, Text value, Context context)
   throws IOException, InterruptedException {
   // Your mapper implementation here
	String data = value.toString();

    String yearAndMonth = extractYearMonth(data);
    String time = extractTime(data);
    int tempValue = extractTemperature(data);
    char qualityCode = extractQuality(data);

    if (isTemperatureValid(time, tempValue, qualityCode)) {
        context.write(new Text(yearAndMonth), new IntWritable(tempValue));
    }
}

private String extractYearMonth(String line) {
    return line.substring(15, 21);
}

private String extractTime(String line) {
    return line.substring(23, 27);
}

private int extractTemperature(String line) {
    int temp;
    try {
        temp = Integer.parseInt(line.substring(87, 92));
    } catch (NumberFormatException e) {
        temp = MISSING;
    }
    return temp;
}

private char extractQuality(String line) {
    return line.charAt(92);
}

private boolean isTemperatureValid(String time, int tempValue, char qualityCode) {
    return time.equals("1300") && tempValue != MISSING && 
           (qualityCode == '0' || qualityCode == '1' || qualityCode == '4' || 
            qualityCode == '5' || qualityCode == '9');
}
}

