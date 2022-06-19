package main;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Data {
    private static int point = 0;
    public Data(){
        try {
            BufferedInputStream myObj = new BufferedInputStream(Data.class.getResourceAsStream("data.txt"));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                point = Integer.parseInt(data); 
            }
            myReader.close();
        } catch (Exception e) {
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
            
          } catch (Exception e) {
            
          }
    }
}
