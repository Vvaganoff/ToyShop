package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithFile {
    public static ArrayList<String> ReadFile (String fileName) throws IOException {
        ArrayList<String> totalString = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            Scanner scan = new Scanner(fileReader);
            while (scan.hasNextLine()) {
                totalString.add(scan.nextLine());
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  totalString;
    }

    public static void WriteFile (String fileName, ArrayList<String> strArray) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (String str:strArray
                 ) {
                fileWriter.write(str + "\n");
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
