import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheaterTest {

    @Test
    public void initializeTest() {
        Theater theater = new Theater(30, 20);
        assertTrue(theater.getAvailableSeats() == 600);
    }

    // TODO: 6/7/17 create other tests for Theater

}
