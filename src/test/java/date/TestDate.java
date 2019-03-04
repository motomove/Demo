package date;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YinXu
 * @Date: 19-2-20 上午10:31
 * @Version 1.0
 */
public class TestDate {

    @Test
    public void TestDateTimeFormat() throws ParseException {
//        Date data = new Date("2018-12-09 17:30:00.0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = sdf.parse("2018-12-09 17:30:00.0");
        System.out.println("Date = " + data.getTime());
    }

    public void TestFloat(){
        float f = 1.3f;
    }
}
