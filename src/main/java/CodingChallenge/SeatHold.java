package CodingChallenge;

public class SeatHold {

    boolean success;
    String message = "Seat hold succeeded!";
    int id;
    String customerEmail;
    int[][] seats;
    long time;

    public SeatHold(boolean success, String message) throws Exception {
        if (success != false) {
            throw new Exception("Method can only be called with false value.");
        }
        this.success = success;
        this.message = message;
    }

    public SeatHold(boolean success, int id, String customerEmail, int[][] seats) throws Exception {
        if (success != true) {
            throw new Exception("Method can only be called with true value.");
        }
        this.success = success;
        this.id = id;
        this.customerEmail = customerEmail;
        this.time = System.currentTimeMillis();
        this.seats = seats;
    }

    public int getId() {
        return id;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public int[][] getSeats() {
        return seats;
    }


}
