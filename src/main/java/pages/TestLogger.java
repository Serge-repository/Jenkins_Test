package pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

public class TestLogger {
    private int step = 1;

    public void log(String message) {
        messenger(message);
    }

    private static String getCurrentDateAndTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss:S");
        return simpleDateFormat.format(calendar.getTime());
    }

    private void messenger(String message) {
        String dateFormat = getCurrentDateAndTime();
        System.out.println(Level.INFO + " Step " + step + " " + dateFormat + " [" + methodName() + "]: " + message);
        step++;
    }

    private String methodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }
}
