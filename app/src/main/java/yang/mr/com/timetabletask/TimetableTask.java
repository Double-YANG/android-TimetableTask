package yang.mr.com.timetabletask;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;


/**
 * Created by ZY004Engineer on 2018/11/1.
 * 定时器任务
 */

public class TimetableTask implements Runnable {

    private static final String TAG = TimetableTask.class.getSimpleName();
    private Handler mHandler;
    private long[] mTime;
    private int count;
    //    private int[] mAction;
//    private boolean mEnable;

    public TimetableTask() {
        mHandler = new Handler();
    }

//    public TimetableTask setData(@NonNull long[] time, int[] action, boolean enable) {
//        mTime = time;
//        mAction = action;
//        mEnable = enable;
//        //排序从小到大
//        for (int i = 0; i < mTime.length; i++) {
//            for (int j = 0; j < mTime.length; j++) {
//                if (mTime[i] < mTime[j]) {
//                    long replaceNumb = mTime[i];
//                    mTime[i] = mTime[j];
//                    mTime[j] = replaceNumb;
//                }
//            }
//
//        }
//        return this;
//    }

    /**
     * 设置Long[] 的时间表
     *
     * @param time
     * @return
     */
    public TimetableTask setLongTime(@NonNull long[] time) {
        mTime = time;
        //排序从小到大
        for (int i = 0; i < mTime.length; i++) {
            for (int j = 0; j < mTime.length; j++) {
                if (mTime[i] < mTime[j]) {
                    long replaceNumb = mTime[i];
                    mTime[i] = mTime[j];
                    mTime[j] = replaceNumb;
                }
            }

        }
        return this;
    }

    /**
     * 启动定时任务
     */
    public void start() {
        if (mTime == null) return;
        long startTime = 0;
        for (int i = 0; i < mTime.length; i++) {
            long currentTime = System.currentTimeMillis();
            if (currentTime < mTime[i]) {
                startTime = mTime[i] - currentTime;
                count = i;
                break;
            }
        }
        if (mHandler != null) mHandler.postDelayed(this, startTime);
    }

    /**
     * 关闭定时任务
     */
    public void close() {
        if (mHandler != null) mHandler.removeCallbacks(this);
    }

    /**
     * 获取某段时间的(毫秒)
     *
     * @return 结果
     */
    public static long getMillisecond(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 执行定时任务
     */
    @Override
    public void run() {
        Log.d(TAG, "执行定时任务: " + count);
        if (count < mTime.length - 1) {
            long delay = mTime[count + 1] - mTime[count];
            mHandler.postDelayed(this, delay);
            count++;
        }
    }

}
