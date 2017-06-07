public class SeatHold {

    boolean success;
    String message = "Seat hold succeeded!";
    String id;
    int numSeats;
    String customerEmail;
    long time;

    public SeatHold(boolean success, String message) throws Exception {
        if (success != false) {
            throw new Exception("Method can only be called with false value.");
        }
        this.success = success;
        this.message = message;
    }

    public SeatHold(boolean success, String id, int numSeats, String customerEmail) throws Exception {
        if (success != false) {
            throw new Exception("Method can only be called with true value.");
        }
        this.success = success;
        this.id = id;
        this.numSeats = numSeats;
        this.customerEmail = customerEmail;
        this.time = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public int getNumSeats() {
        return numSeats;
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
}
