import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Theater {

    String[][] seatMap;
    int rows;
    int cols;
    int availableSeats;
    static String AVALIABLE = "s";
    static String ONHOLD = "h";
    static String RESERVED = "r";


    public Theater (int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seatMap = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setAvailable(i, j);
            }
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public synchronized int[][] holdSeatsByCount(int numSeats) {
        if (!(this.getAvailableSeats() >= numSeats)) {
            return null;
        }
        // logic to get best seats in the theater
        int[][] bestSeats = new int[numSeats][2];
        int seat = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seatMap[i][j].equals(this.AVALIABLE)) {
                    this.setOnHold(i, j);
                    bestSeats[seat][0] = i;
                    bestSeats[seat][1] = j;
                    seat++;
                    if (seat == bestSeats.length) {
                        return bestSeats;
                    }
                }
            }
        }
        return bestSeats;
    }

    public boolean reserveSeats(int[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            this.setReserved(seats[i][0], seats[i][1]);
        }
        return true;
    }

    public boolean releaseSeats(int[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            this.setAvailable(seats[i][0], seats[i][1]);
        }
        return true;
    }

    public void printSeatMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(seatMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void setAvailable(int row, int col) {
        this.seatMap[row][col] = this.AVALIABLE;
        this.availableSeats += 1;
    }

    private void setOnHold(int row, int col) {
        assert(this.seatMap[row][col].equals(this.AVALIABLE));
        this.seatMap[row][col] = this.ONHOLD;
        this.availableSeats -= 1;
    }

    private void setReserved(int row, int col) {
        assert(this.seatMap[row][col].equals(this.ONHOLD));
        this.seatMap[row][col] = this.RESERVED;
    }

    private void fileImport(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
