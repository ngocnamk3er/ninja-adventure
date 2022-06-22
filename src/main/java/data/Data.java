package data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;

public class Data {
    private static int point = 0;
    private static int level = 0;
    public Data(){
        try {
 
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader("data.txt");
     
            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
     
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                point = Integer.valueOf(nextRecord[0]);
                level = Integer.valueOf(nextRecord[1]);
            }
            csvReader.close();
        }
        catch (IOException e) {
            point = 0;
            level = 0;
        }
    }
    
    public static int getPoint() {
        return point;
    }

    public static void setPoint(int point) {
        Data.point = point;
    }
    
    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Data.level = level;
    }

    public static void saveData(){
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write(String.valueOf(point)+","+String.valueOf(level));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
