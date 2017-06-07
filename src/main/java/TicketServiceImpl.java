import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketServiceImpl implements TicketService {

    static String COMMAND_PROMPT = "Invalid command. Type 'number' for availability, " +
            "'hold [quantity] [email] to find and hold seats', " +
            " and 'reserve [confirmation id] [email]' to make reservation.";

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
        // add to seat hold list

        // add to email table

        // add to id table

    }

    private void deleteSeatHold(SeatHold seatHold) {
        // remove from seat hold list

        // remove from email table

        // remove from id table

    }

    private void removeExpiredSeatHold() {
        // starting from the beginning of the seat hold list, check the time stamp
        // invoke deleteSeatHold if time stamp expired, stop when the seat hold is
        // not expired

    }

    public int numSeatsAvailable() {
        return this.theater.getAvailableSeats();
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        try {
            return new SeatHold(false, "Not enough seats.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        // need to check time stamp for expiration in case it is not reaped


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
                int numSeats;
                String customerEmail;
                try {
                    numSeats = Integer.parseInt(arguments[1]);
                    customerEmail = arguments[2];
                } catch (Exception e) {
                    System.out.println(COMMAND_PROMPT);
                    continue;
                }
                SeatHold seatHold = ts.findAndHoldSeats(numSeats, customerEmail);
                if (seatHold == null) {
                    System.out.println("System error!");
                }
                if (seatHold.isSuccess() == true) {
                    System.out.println(seatHold.getMessage() + "Confirmation ID: " + seatHold.getId());
                } else {
                    System.out.println("Seat hold failed. " + seatHold.getMessage());
                }
            } else if (command.equals(RESERVE_COMMAND)) {
                int seatHoldID;
                String customerEmail;
                try {
                    seatHoldID = Integer.parseInt(arguments[1]);
                    customerEmail = arguments[2];
                } catch (Exception e) {
                    System.out.println(COMMAND_PROMPT);
                    continue;
                }
                String reserveMessage = ts.reserveSeats(seatHoldID, customerEmail);
                System.out.println("Seat reservation " + reserveMessage);
            } else if (command.equals(EXIT_COMMAND)) {
                System.exit(0);
            } else {
                System.out.println(COMMAND_PROMPT);
            }
        }
    }
}
