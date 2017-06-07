import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TicketServiceImpl implements TicketService {

    static String COMMAND_PROMPT = "Invalid command. Type 'number' for availability, " +
            "'hold [quantity] [email] to find and hold seats', " +
            " and 'reserve [confirmation id] [email]' to make reservation.";

    static String GET_NUMBER_COMMAND = "number";
    static String FIND_AND_HOLD_COMMAND = "hold";
    static String RESERVE_COMMAND = "reserve";
    static String EXIT_COMMAND = "exit";

    Random rand = new Random();

    ArrayList<SeatHold> seatHoldList;
    HashMap<String, ArrayList<SeatHold>> emailTable;
    HashMap<Integer, SeatHold> idTable;

    private Theater theater;

    public TicketServiceImpl(int rows, int cols) {
        this.theater = new Theater(rows, cols);
        this.seatHoldList = new ArrayList<SeatHold>();
        this.emailTable = new HashMap<String, ArrayList<SeatHold>>();
        this.idTable = new HashMap<Integer, SeatHold>();
    }

    private void addSeatHold(SeatHold seatHold) {
        // add to seat hold list
        this.seatHoldList.add(seatHold);
        // add to email table
        ArrayList<SeatHold> seatHoldList = emailTable.get(seatHold.getCustomerEmail());
        if (seatHoldList == null) {
            seatHoldList = new ArrayList<SeatHold>();
            seatHoldList.add(seatHold);
            emailTable.put(seatHold.getCustomerEmail(), seatHoldList);
        } else {
            seatHoldList.add(seatHold);
        }
        // add to id table
        idTable.put(seatHold.getId(), seatHold);
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

    private int idGenerator() {
        int id = rand.nextInt(1000000000);
        while (idTable.containsKey(id) == true) {
            id = rand.nextInt();
        }
        return id;
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        try {
            if (numSeats <= 0) {
                return new SeatHold(false, "Invalid quantity of seats to hold.");
            }
            int[][] heldSeats = theater.holdSeatsByCount(numSeats);
            if (heldSeats == null) {
                return new SeatHold(false, "Not enough seats available. Try again later.");
            } else {
                SeatHold seatHold = new SeatHold(true, idGenerator(), numSeats, customerEmail, heldSeats);
                addSeatHold(seatHold);
                return seatHold;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        // need to check time stamp for expiration in case it is not reaped
        // TODO: 6/6/17  

        return "ok";
    }

    public void printSeatMap() {
        this.theater.printSeatMap();
    }

    public static void main(String[] args) {
        TicketService ts = new TicketServiceImpl(9, 33);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ts.printSeatMap();
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
                    String idString = String.format("%09d", seatHold.getId());
                    System.out.println(seatHold.getMessage() + "Confirmation ID: " + idString);
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
