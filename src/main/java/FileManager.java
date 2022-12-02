import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <h1>File manager</h1>
 * <p>file manager class helps to read from data file to creat {@link Supporter supporters} </p>
 */
public class FileManager {
    /**
     * number of supporters in {@link #file file}
     */
    private int numberOfSupporters;

    /**
     * path of file include number {@link #numberOfSupporters number of supporters} and customers
     */
    private String path;

    /**
     * a file include supporters and costumers data. the {@link #path} is defined here.
     */
    File file;

    /**
     * utility to read from file
     */
    Scanner scanner;

    /**
     * Initialize the required variables and set the path.
     * @param path path of data file
     * @throws FileNotFoundException if file can't be found exception will throw
     */
    public FileManager(String path) throws FileNotFoundException {
        this.path = path;
        this.file = new File(this.path);
        this.scanner = new Scanner(file);
        this.numberOfSupporters = Integer.parseInt(scanner.nextLine());
    }

    /**
     * If there is any {@link Costumer} in {@link #file} return ture, otherwise return false
     * @return ture if there is any {@link Costumer Costumers} in {@link #file}, otherwise false
     */
    public boolean hasNextCostumer()  {
        return this.scanner.hasNextLine();
    }

    /**
     * If there is any {@link Costumer} then return that costumer from {@link #file}.
     * it is possible to check if there is any costumer to return or not, this feature is available in {@link  #hasNextCostumer()}.
     * @return the next costumer in the file if there is any costumer. if there is no costumer to return exception will happen.
     *
     * @see #hasNextCostumer()
     */
    public Costumer getNextCostumer() {
        assert !hasNextCostumer() : "No costumer found :(";
        String []costumerText = scanner.nextLine().split(" ");
        return new Costumer(costumerText[0], costumerText.length != 2 && costumerText[2].equalsIgnoreCase("weak_signal"), InternetSupportCenter.Services.valueOf(costumerText[1].toUpperCase()));
    }

    /**
     * Get the number of supporters in the {@link #file}
     * @return the number of supporters in the file
     */
    public int getNumberOfSupporters() {
        return numberOfSupporters;
    }

}
