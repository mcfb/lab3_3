package edu.iis.mto.time;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    @Test(expected = OrderExpiredException.class)
    public void shouldThrowOrderExpiredIfOrderIsExpired() {
        FakeDateTime fakeDateTime = new FakeDateTime();
        Order order = new Order(fakeDateTime);
        fakeDateTime.addDateToReturn(2019,04,20,20,00);
        order.submit();
        fakeDateTime.addDateToReturn(2019,04,21,21,00);
        order.confirm();
    }
}