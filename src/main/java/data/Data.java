package data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;

public class Data {
    private static int point = 0;
    private static int level = 0;
    public Data(){
        
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
    public static void resetData(){
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write("0,0");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void loadData(){
        try {
            FileReader filereader = new FileReader("data.txt");
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
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
}
