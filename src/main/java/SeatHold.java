public class SeatHold {

    boolean success;
    String id;
    int numSeats;
    String customerEmail;

    public SeatHold(boolean success) throws Exception {
        if (success != false) {
            throw new Exception("Method can only be called with false value.");
        }
        this.success = success;
    }

    public SeatHold(boolean success, String id, int numSeats, String customerEmail) throws Exception {
        if (success != false) {
            throw new Exception("Method can only be called with true value.");
        }
        this.success = success;
        this.id = id;
        this.numSeats = numSeats;
        this.customerEmail = customerEmail;
    }

    public boolean getSuccess() {
        return success;
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
}
