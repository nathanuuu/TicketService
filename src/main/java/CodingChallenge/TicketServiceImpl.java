package CodingChallenge;

import java.util.*;

public class TicketServiceImpl implements TicketService {

    static String COMMAND_PROMPT = "Invalid command. Type 'number' for availability, " +
            "'hold [quantity] [email] to find and hold seats', " +
            " and 'reserve [confirmation id] [email]' to make reservation.";

    static String GET_NUMBER_COMMAND = "number";
    static String FIND_AND_HOLD_COMMAND = "hold";
    static String RESERVE_COMMAND = "reserve";
    static String EXIT_COMMAND = "exit";

    static int EXPIRATION = 600000;

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
        seatHoldList.remove(seatHold);
        // remove from email table
        ArrayList<SeatHold> seatHoldArrayList = emailTable.get(seatHold.getCustomerEmail());
        if (seatHoldArrayList.size() > 1) {
            seatHoldArrayList.remove(seatHold);
        } else {
            emailTable.remove(seatHold.getCustomerEmail());
        }
        // remove from id table
        idTable.remove(seatHold.getId());
    }

    private void removeExpiredSeatHold() {
        // starting from the beginning of the seat hold list, check the time stamp
        // invoke deleteSeatHold if time stamp expired, stop when the seat hold is
        // not expired
        while (seatHoldList.size() > 0) {
            SeatHold seatHold = seatHoldList.get(0);
            if (seatHold.getTime() + this.EXPIRATION < System.currentTimeMillis()) {
                deleteSeatHold(seatHold);
                theater.releaseSeats(seatHold.getSeats());
            } else {
                break;
            }
        }
    }

    public int numSeatsAvailable() {
        removeExpiredSeatHold();
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
        // Free up some expired seats for better search results
        removeExpiredSeatHold();
        try {
            if (numSeats <= 0) {
                return new SeatHold(false, "Invalid quantity of seats to hold.");
            }
            int[][] heldSeats = theater.holdSeatsByCount(numSeats);
            if (heldSeats == null) {
                return new SeatHold(false, "Not enough seats available. Try again later.");
            } else {
                SeatHold seatHold = new SeatHold(true, idGenerator(), customerEmail, heldSeats);
                addSeatHold(seatHold);
                return seatHold;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        long currentTime = System.currentTimeMillis();
        String failureMessage = "Reservation failed. Please make sure your inputs are correct, " +
                "and your hold has not expired.";
        SeatHold seatHold = idTable.get(seatHoldId);
        if (seatHold == null || emailTable.get(customerEmail) == null
                || emailTable.get(customerEmail).contains(seatHold) == false) {
            return failureMessage;
        }
        // need to check time stamp for expiration in case it is not reaped
        if (seatHold.getTime() + this.EXPIRATION < currentTime) {
            deleteSeatHold(seatHold);
            theater.releaseSeats(seatHold.getSeats());
            return failureMessage;
        }
        theater.reserveSeats(seatHold.getSeats());
        deleteSeatHold(seatHold);
        return "Reservation succeeded!";
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
                    Date expirationDate = new Date(seatHold.getTime() + EXPIRATION);
                    System.out.println(seatHold.getMessage() + "Confirmation ID: " + idString
                        + " Your hold will expire on " + expirationDate);
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
