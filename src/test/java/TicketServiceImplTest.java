import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class TicketServiceImplTest {

    @Test
    public void seatsAvailableInitializeTest() {
        TicketService ts = new TicketServiceImpl(33, 9);
        assertTrue(ts.numSeatsAvailable() == 297);

        ts = new TicketServiceImpl(1, 9);
        assertTrue(ts.numSeatsAvailable() == 9);

        ts = new TicketServiceImpl(9, 1);
        assertTrue(ts.numSeatsAvailable() == 9);

        ts = new TicketServiceImpl(0, 9);
        assertTrue(ts.numSeatsAvailable() == 0);
    }

    @Test
    public void seatsAvailableAfterReserveTest() {
        TicketService ts = new TicketServiceImpl(33, 9);
        assertTrue(ts.numSeatsAvailable() == 297);
        // TODO: 6/6/17 test for seats available after reserve and hold
    }


}
