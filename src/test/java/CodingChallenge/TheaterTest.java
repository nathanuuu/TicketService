package CodingChallenge;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheaterTest {

    @Test
    public void seatOperationsTest() {
        Theater theater = new Theater(30, 20);
        assertTrue(theater.getAvailableSeats() == 600);
        int[][] seats = theater.holdSeatsByCount(5);
        assertTrue(seats.length == 5);
        theater.reserveSeats(seats);
        assertTrue(theater.availableSeats == 595);
        seats = theater.holdSeatsByCount(10);
        assertTrue(theater.getAvailableSeats() == 585);
        theater.releaseSeats(seats);
        assertTrue(theater.availableSeats == 595);
    }

}
