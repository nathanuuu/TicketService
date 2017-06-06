import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketServiceImpl implements TicketService {

    static String GET_NUMBER_COMMAND = "number";
    static String FIND_AND_HOLD_COMMAND = "hold";
    static String RESERVE_COMMAND = "reserve";
    static String EXIT_COMMAND = "exit";

    ArrayList<SeatHold> seatHoldList;
    HashMap<String, SeatHold[]> emailTable;
    HashMap<String, SeatHold> idTable;

    private Theater theater;

    public TicketServiceImpl(int rows, int cols) {
        this.theater = new Theater(rows, cols);
        this.seatHoldList = new ArrayList<SeatHold>();
        this.emailTable = new HashMap<String, SeatHold[]>();
        this.idTable = new HashMap<String, SeatHold>();
    }

    private void addSeatHold(SeatHold seatHold) {

    }

    private void deleteSeatHold(SeatHold seatHold) {

    }

    private void removeExpiredSeatHold() {

    }

    public int numSeatsAvailable() {
        return this.theater.getAvailableSeats();
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        try {
            return new SeatHold(false, "Default message.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        return "ok";
    }

    public static void main(String[] args) {
        TicketService ts = new TicketServiceImpl(33, 9);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your option: ");
            String[] arguments = scanner.nextLine().split(" ");

            String command = arguments[0];
            if (command.equals(GET_NUMBER_COMMAND)) {
                System.out.println("There are " + ts.numSeatsAvailable() + " seats available.");
            } else if (command.equals(FIND_AND_HOLD_COMMAND)) {
                int numSeats = Integer.parseInt(arguments[1]);
                String customerEmail = arguments[2];
                SeatHold seatHold = ts.findAndHoldSeats(numSeats, customerEmail);
                // TODO: 6/5/17 add a method to get hold status and if sucess confirmation seat hold ID
                System.out.println("Seat hold succeeded! Confirmation: " + "000000");
            } else if (command.equals(RESERVE_COMMAND)) {
                int seatHoldID = Integer.parseInt(arguments[1]);
                String customerEmail = arguments[2];
                String reserveMessage = ts.reserveSeats(seatHoldID, customerEmail);
                System.out.println("Seat reservation " + reserveMessage);
            } else if (command.equals(EXIT_COMMAND)) {
                System.exit(0);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }
}
