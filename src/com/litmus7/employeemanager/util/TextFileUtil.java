package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TextFileUtil {

    private static final String TARGET_CSV = "employees.csv";
    private static final String TEXT_FILE_PATH = "employees.txt";

    public static List<String> dataFromTextFile(String filepath) throws IOException{

        BufferedReader reader = new BufferedReader(new FileReader(filepath.trim().isEmpty()? TEXT_FILE_PATH : filepath));
        List<String> lines = new ArrayList<>();
        
        String line;

        while ((line = reader.readLine()) != null) {

            lines.add(line);
            
        }
        reader.close();
        return lines;
	   
    }

    public static List<String> dataFromCsvFile() throws IOException{ //same as dataFromTextFile method but there is no user specified path

        BufferedReader reader = new BufferedReader(new FileReader(TARGET_CSV));
        List<String> lines = new ArrayList<>();
        
        String line;

        while ((line = reader.readLine()) != null) {

            lines.add(line);
            
        }

        reader.close();
        return lines;
	   
    }
    
    public static void parseToCsv(List<String> lines) throws IOException{

        PrintWriter writer = new PrintWriter(TARGET_CSV);

        for (String line : lines) {

            writer.printf(line);
            
        }
        writer.close();

    }

    public static void appendToCsv(List<String> lines) throws IOException{

        PrintWriter writer = new PrintWriter(new FileWriter(TARGET_CSV,true));
        for (String line : lines) {

            writer.printf(line);
            
        }
        writer.close();

    }


}
