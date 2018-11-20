package yang.mr.com.timetabletask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long[] times = new long[3];
        times[0] = TimetableTask.getMillisecond(17, 21, 0);
        times[1] = TimetableTask.getMillisecond(17, 21, 30);
        times[2] = TimetableTask.getMillisecond(17, 22, 0);
        new TimetableTask().setLongTime(times).start();//启动时间表
    }
}
