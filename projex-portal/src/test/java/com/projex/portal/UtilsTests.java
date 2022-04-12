package com.projex.portal;

import com.projex.portal.utils.TokenTools;
import com.projex.portal.vo.TokenResult;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class UtilsTests {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testToken(){
        String token = TokenTools.getToken(1001, "admin");
        TokenResult usernameByToken = TokenTools.getUsernameByToken(token);
        System.out.println(usernameByToken);
    }

    @Test
    public void testDate() throws ParseException {

        Date date1 = new Date();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-04");
        List<String> strings = splitDateList(date, date1);
        System.out.println(strings);
    }

    public static List<String> splitDateList(Date startDate, Date endDate) {
        List<String> listDate = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                listDate.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            return listDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDate;
    }

    @Test
    public void test1() throws ParseException {
        String s1 = "2022-03-31";
        String s2 = "2022-04-04";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse(s1);
        Date secondDate = sdf.parse(s2);
        long diffInMills = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMills, TimeUnit.MILLISECONDS);
        System.out.println(diff);
    }
}
