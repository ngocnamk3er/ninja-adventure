package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {
    private static int point = 0;
    public Data(){
        try {
            // FileInputStream myObj = new FileInputStream(Data.class.getResource("data.txt").getPath());
            File myObj = new File("src\\main\\resources\\main\\data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                point = Integer.parseInt(data); 
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static int getPoint() {
        return point;
    }

    public static void setPoint(int point) {
        Data.point = point;
    }
    
    public static void saveData(){
        try {
            // FileWriter myWriter = new FileWriter(Data.class.getResourceAsStream("data.txt").toString());
            FileWriter myWriter = new FileWriter("src\\main\\resources\\main\\data.txt");
            myWriter.write(String.valueOf(point));
            myWriter.close();
            // System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
