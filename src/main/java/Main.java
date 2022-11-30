import java.io.FileNotFoundException;

/**
 * Main Project
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Color.AnsiColor.processLine();
        FileManager fileManager = new FileManager("src/main/resources/Data.txt");
        InternetSupportCenter internetSupportCenter = new InternetSupportCenter(fileManager.getNumberOfSupporters());

        while (fileManager.hasNextCostumer())
            internetSupportCenter.waitToCallToSupporter(fileManager.getNextCostumer());

        internetSupportCenter.startAllThreads();

    }
}
