import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Theater {

    String[][] seatMap;
    int rows;
    int cols;
    static String AVALIABLE = "s";
    static String ONHOLD = "h";
    static String RESERVED = "r";

    public Theater (int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seatMap = new String[rows][cols];
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    setAvailable(i, j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAvailable(int row, int col) throws Exception {
        if (!(0 <= row && row < this.rows && 0 <= col && col < this.cols)) {
            throw new Exception("Input row and column out of bounds, input row: " + row + " input column: "
                    + col + " actual number of rows: " + this.rows + " actual number of columns: " + cols);
        }
        this.seatMap[row][col] = this.AVALIABLE;
    }

    public void setOnHold(int row, int col) throws Exception {
        if (!(0 <= row && row < this.rows && 0 <= col && col < this.cols)) {
            throw new Exception("Input row and column out of bounds, input row: " + row + " input column: "
            + col + " actual number of rows: " + this.rows + " actual number of columns: " + cols);
        }
        this.seatMap[row][col] = this.ONHOLD;
    }

    public void setReserved(int row, int col) throws Exception {
        if (!(0 <= row && row < this.rows && 0 <= col && col < this.cols)) {
            throw new Exception("Input row and column out of bounds, input row: " + row + " input column: "
                    + col + " actual number of rows: " + this.rows + " actual number of columns: " + cols);
        }
        this.seatMap[row][col] = this.RESERVED;
    }

    public void fileImport(String fileName) {
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
