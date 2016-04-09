package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class WriteToFile {
	
	PrintWriter writer = null;	
	String pathToOutputFolder = "";
	
	public WriteToFile(String pathToOutputFolder) throws FileNotFoundException {
		this.pathToOutputFolder = pathToOutputFolder;
		writer = new PrintWriter(pathToOutputFolder);
	}
	
	public  void writeToFile(String lineToWrite) throws IOException {
		writer.println(lineToWrite);
	}
	
	public void closeWriter(){
		writer.close();
	}	
}
