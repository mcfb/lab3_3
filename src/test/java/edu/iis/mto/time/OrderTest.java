package edu.iis.mto.time;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OrderTest {


    private FakeDateTime fakeDateTime;
    private Order order;

    @Before
    public void setUp(){
         fakeDateTime = Mockito.mock(FakeDateTime.class);
         order = new Order(fakeDateTime);
    }



    @Test(expected = OrderExpiredException.class)
    public void shouldThrowOrderExpiredIfOrderIsExpired() {
        Mockito.when(fakeDateTime.getDate()).thenReturn(new DateTime(2019,04,20,20,00));
        order.submit();
        Mockito.when(fakeDateTime.getDate()).thenReturn(new DateTime(2019,04,21,21,00));
        order.confirm();

    }


    @Test
    public void shouldBeSubmittedIfOrderIsNotExpired(){
        Mockito.when(fakeDateTime.getDate()).thenReturn(new DateTime(2019,04,20,20,00));
        order.submit();
        Mockito.when(fakeDateTime.getDate()).thenReturn(new DateTime(2019,04,20,23,00));
        order.confirm();

        assertThat(order.getOrderState(), Matchers.equalTo(Order.State.SUBMITTED));

    }
}
