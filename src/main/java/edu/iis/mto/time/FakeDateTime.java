package edu.iis.mto.time;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class FakeDateTime {

    private List<DateTime> datesToReturn;

    public FakeDateTime() {
        datesToReturn = new ArrayList<>();
    }

    public void addDateToReturn(int year, int month, int day, int hour, int minute) {
        datesToReturn.add(new DateTime(year, month, day, hour, minute));
    }

    public DateTime getDate() {
        return datesToReturn.remove(0);
    }
}
