package task;

import util.DateParse;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by STZHANGJK on 2016.9.14.
 */
public class TestTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("Running！！time=" + DateParse.parseString(new Date()));
    }
}
