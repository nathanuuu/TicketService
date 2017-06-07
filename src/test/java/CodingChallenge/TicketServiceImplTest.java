package CodingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TicketServiceImplTest {

    @Test
    public void seatsAvailableInitializeTest() {
        TicketService ts = new TicketServiceImpl(33, 9, 60);
        assertTrue(ts.numSeatsAvailable() == 297);

        ts = new TicketServiceImpl(1, 9, 60);
        assertTrue(ts.numSeatsAvailable() == 9);

        ts = new TicketServiceImpl(9, 1, 60);
        assertTrue(ts.numSeatsAvailable() == 9);

        ts = new TicketServiceImpl(0, 9, 60);
        assertTrue(ts.numSeatsAvailable() == 0);
    }

    @Test
    public void seatsAvailableAfterReserveTest() {
        TicketService ts = new TicketServiceImpl(33, 9, 60);
        assertTrue(ts.numSeatsAvailable() == 297);
        SeatHold seatHold = ts.findAndHoldSeats(10, "email@company.com");
        int confirmationID = seatHold.getId();
        assertTrue(ts.numSeatsAvailable() == 287);
        String failureMessage = "Reservation failed. Please make sure your inputs are correct, " +
                "and your hold has not expired.";
        assertTrue(ts.reserveSeats(confirmationID, "email@organization.org").equals(failureMessage));
        assertTrue(ts.reserveSeats(-100000, "email@company.com").equals(failureMessage));
        assertTrue(ts.reserveSeats(confirmationID, "email@company.com").equals("Reservation succeeded!"));
    }


}
