package com.example.stickhero;

import java.io.*;

import static com.example.stickhero.MainController.bestscore;
import static com.example.stickhero.MainController.cherries;

public class SaveScoreAndCherries {

    public static void saveBestScore(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bestscore.txt"))) {
            // Convert the integer to a string and write it to the file
            writer.write(Integer.toString(bestscore));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show an error message)
        }
    }
    public static int retrieveBestScore(){
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("bestscore.txt"))) {
            // Read the first line from the file
            String line = reader.readLine();

            if (line != null) {
                // Parse the string to an integer
                result = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show an error message)
        }

        return result;
    }
    public static void saveCherries(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cherries.txt"))) {
            // Convert the integer to a string and write it to the file
            writer.write(Integer.toString(cherries));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show an error message)
        }
    }
    public static int retrieveCherries() {
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("cherries.txt"))) {
            // Read the first line from the file
            String line = reader.readLine();

            if (line != null) {
                // Parse the string to an integer
                result = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show an error message)
        }

        return result;
    }
    public static void serialize(Character object) throws IOException {
        ObjectOutputStream out=null;
        //VacuumCleaner vacuumCleaner=new VacuumCleaner("Bajaj");
        try{
            out=new ObjectOutputStream(new FileOutputStream("progress.txt"));
            out.writeObject(object);
        }
        finally{
            if(out!=null)
                out.close();
        }
    }
    public static void deserialize() throws IOException,ClassNotFoundException{
        ObjectInputStream in=null;
        //Stick char=null;
        try{
            in=new ObjectInputStream(new FileInputStream("progress.txt"));
            //char=(Character) in.readObject();
        }
        finally{
            if(in!=null)
                in.close();
        }
    }
    public static void main(String[] args) {
        saveBestScore();
    }
}
