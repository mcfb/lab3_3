package edu.iis.mto.time;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {


    private FakeDateTime fakeDateTime;
    private Order order;

    private void setup(){
         fakeDateTime = new FakeDateTime();
         order = new Order(fakeDateTime);
    }



    @Test(expected = OrderExpiredException.class)
    public void shouldThrowOrderExpiredIfOrderIsExpired() {
        setup();
        fakeDateTime.addDateToReturn(2019,04,20,20,00);
        fakeDateTime.addDateToReturn(2019,04,21,21,00);
        order.submit();
        order.confirm();
    }


    @Test
    public void shouldBeSubmittedIfOrderIsNotExpired(){
        setup();
        fakeDateTime.addDateToReturn(2019,04,20,20,00);
        fakeDateTime.addDateToReturn(2019,04,20,23,00);
        order.submit();
        order.confirm();

        assertThat(order.getOrderState(), Matchers.equalTo(Order.State.SUBMITTED));

    }
}