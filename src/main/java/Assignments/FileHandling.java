package Assignments;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandling {

    public static void main(String[] args) {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //Read
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sampleFile.txt"))) {

            long i;
            System.out.println("FILE READING...");
            while ((i = bufferedReader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Write
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sample2.txt"))) {

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("FILE WRITING...");
            System.out.println("Enter string to write: ");
            String userInput = bufferedReader.readLine();
            bufferedWriter.write(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Update
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sampleFile.txt"))) {

            System.out.println("UPDATE FILES...");
            System.out.println("Enter string you want to replace: ");
            String userInput;
            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                userInput = bufferedReader.readLine();
            }
            Path fileName = Path.of("sampleFile.txt");
            String str = Files.readString(fileName);
            String fileData = str.replaceAll(userInput, "THE TEXT HAS BEEN APPENDED!");
            bufferedWriter.write(fileData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Delete
        File file = new File("sampleFile.txt");
        if (file.exists()) {
            System.out.println("sampleFile.txt File has been deleted");
            //file.deleteOnExit();
        } else {
            System.out.println("File to be deleted doesn't exist.");
        }
    }
}
