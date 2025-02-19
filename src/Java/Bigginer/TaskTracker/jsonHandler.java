package Java.Bigginer.TaskTracker;

import java.io.*;
import java.io.IOException;

public class jsonHandler {
    private String FILE_NAME = "Task.json";

        public static void initializeFile(){
            try{
                if (File.exists(path.of(FILE_NAME))) {
                    File.write(path.of(FILE_NAME), "[]".getBytes());
                }
            }catch(IOException e){
                System.out.println("Error Initializing JSON file: " + e.getMessage());
            }
        }
}
