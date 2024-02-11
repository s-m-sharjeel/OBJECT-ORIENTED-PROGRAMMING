package Final_Exam.question4;

import java.io.IOException;

public class ToothFairyManager {

    public static void main(String[] args) throws IOException {

        ToothFairysHelper tfh = new ToothFairysHelper();
        tfh.readFile();
        tfh.assignGifts();
        tfh.writeToFile();

    }

}
