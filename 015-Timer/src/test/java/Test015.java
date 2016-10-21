import org.junit.Before;
import org.junit.Test;
import task.TestTask;
import util.DateParse;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by STZHANGJK on 2016.9.14.
 */
public class Test015 {
    private Timer timer;

    @Before
    public void before(){
        /*非守护线程方式*/
        //timer = new Timer();
        /*守护线程方式*/
        timer = new Timer();
    }

    /**
     * 计划时间比当前时间晚时，立即执行
     */
    @Test
    public void testSchedule() throws ParseException {
        TimerTask task = new TestTask();
        Date date = DateParse.parseDate("2016-10-21 21:33:00");
        timer.schedule(task,date,5000);
    }


}
