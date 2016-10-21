package task;

import util.DateParse;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by STZHANGJK on 2016.9.14.
 */
public class TestTask extends TimerTask{
    @Override
    public void run() {
        try {
            System.out.println("Running！！time=" + DateParse.parseString(new Date()));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        TimerTask task = new TestTask();
        Date date = DateParse.parseDate("2016-10-21 21:33:00");
        timer.scheduleAtFixedRate(task,date,3000);
    }
}
