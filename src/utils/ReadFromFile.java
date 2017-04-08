package utils;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Kristo
 *
 */
public class ReadFromFile {
	
	/**
	 * @var String pathToFile
	 */
	private String pathToFile = "";
	
	/**
	 * @var Array fileContent
	 */
	private String[] fileContent= null;
	
	/**
	 * @var Array fileContentMatrix
	 */
	private char[][] fileContentMatrix = null;
	
	
	/**
	 * Array numbersArray
	 */
	private int[] numbersArray = null;
	
	
	/**
	 * @var int numberOfLines
	 */
	private int numberOfLines = 0;
	
	/**
	 * @var int nuberOfColumns
	 */
	private int nuberOfColumns = 0;
	
	/**
	 * @param pathToFile
	 * @param resultType
	 * @throws IOException 
	 */
	public ReadFromFile(String pathToFile) throws IOException{
		this.pathToFile = pathToFile;		
		readFileLinesAndSaveToArray();
	}

	/**
	 * @throws IOException
	 */
	private void readFileLinesAndSaveToArray() throws IOException   
    {  
        FileReader fileReader = new FileReader(this.pathToFile);  
          
        BufferedReader bufferedReader = new BufferedReader(fileReader);  
        List<String> lines = new ArrayList<String>();  
        String line = null;  
          
        while ((line = bufferedReader.readLine()) != null)   
        {  
            lines.add(line);  
        }  
          
        bufferedReader.close();  
          
        this.fileContent = lines.toArray(new String[lines.size()]);  
    }
	
	/**
	 * @return array
	 * @throws IOException
	 */
	public  String[] readFile() throws IOException{
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
		return this.fileContent;
	}
		
	/**
	 * @param int lineNumber
	 * @return String
	 */
	public String readLineAsString(int lineNumber){
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
	
		return this.fileContent[lineNumber];					
	}
	
	
	/**
	 * Returns an int array form file
	 * @return int Array
	 */
	public ArrayList<Integer> getFileContentAsIntArray(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i=0; i<this.fileContent.length; i++){
			result.add(Integer.parseInt(this.fileContent[i]));
		}		
		return result;
	}


	/**
	 * Returns an int array form file
	 * @return int Array
	 */
	public ArrayList<Long> getFileContentAsLongArray(){
		ArrayList<Long> result = new ArrayList<Long>();

		for(int i=0; i<this.fileContent.length; i++){
			result.add(Long.parseLong(this.fileContent[i]));
		}
		return result;
	}




	/**
	 * @param int lineNumber
	 * @return array char
	 */
	public char[] readLineAsCharArray(int lineNumber){
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
			
		return this.fileContent[lineNumber].toCharArray();				
	}
	
	
	/**
	 * @param int columnNumber
	 * @return String
	 */
	public String readColumnAsString(int columnNumber){
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
		
		String resultColumnString = "";
		for(int i=0; i<this.fileContent.length; i++){
			resultColumnString += this.fileContent[i].charAt(columnNumber);
		}
		
		return resultColumnString;
	}
	
	/**
	 * @param int columnNumber
	 * @return array char
	 */
	public char[] readColumnAsCharArray(int columnNumber){
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
		
		char[] resultColumnArray = new char[this.nuberOfColumns];
		for(int i=0; i<this.fileContent.length; i++){
			resultColumnArray[i] = this.fileContent[i].charAt(columnNumber);
		}
		
		return resultColumnArray;
	}
	
	
	/**
	 * @param int lineNumber
	 * @param int columnNumber
	 * @return char
	 */
	public char readSpecificElement(int lineNumber, int columnNumber){
		if(this.fileContent == null){
			throw new IllegalStateException("Object not initialised properly, file content missing!");
		}
		
		return fileContentMatrix[lineNumber][columnNumber];
	}
	
	/**
	 * @param int startLine
	 * @param int endLine
	 * @param int startColumn
	 * @param int endColumn
	 * @return array
	 */
	public char[][] readSpecificContentBlock(int startLine, int endLine, int startColumn, int endColumn){
		char[][] resultBlock = new char[this.numberOfLines][this.nuberOfColumns];
		int resultBlockLine = 0;
		int resultBlockColumn = 0;		
		
		for(int i=0; i<this.fileContentMatrix.length; i++){					
			if((i >= startLine) && (i <= endLine)){
				char[] contentRow = this.fileContentMatrix[i];	
				for(int j=0; j<contentRow.length; j++){
					if((j >= startColumn) && (j <= endColumn)){						
						resultBlock[resultBlockLine][resultBlockColumn] = contentRow[j];
						resultBlockColumn++;
					}					
				}
				resultBlockColumn = 0;
				resultBlockLine++;
			}						
		}		
		return resultBlock;
	}
	
	/**
	 * @param charater
	 * @return int
	 */
	public int countCaracter(char charater){
		int characterCount = 0;
		for(int i=0; i<this.numberOfLines; i++){			
			for(int j=0; j<this.nuberOfColumns; j++){				
				if(charater == this.fileContentMatrix[i][j]){
					characterCount++;										
				}				
			}	
		}
		
		return characterCount;
	}
	
	/**
	 * Generate an char matrix from file content
	 */
	private void generateMatrixFromFileContent(){
		
		for(int i=0; i<this.fileContent.length; i++){
			char[] contentRow = this.fileContent[i].toCharArray();
			for(int j=0; j<contentRow.length; j++){
				this.fileContentMatrix[i][j] = contentRow[j];
			}			
		}
		
	}
	
	/**
	 * Initialize the fileContentMatrix
	 */
	private void initializeFileContentMatrix(){
		
		String fristRow = this.fileContent[0];
		String[] fileDetails = fristRow.split("\\s+");
		
		int fileLines = Integer.parseInt(fileDetails[0]);
		int fileColumns = Integer.parseInt(fileDetails[1]);	
		
		this.nuberOfColumns = fileColumns;
		this.numberOfLines = fileLines;
		
		this.fileContentMatrix = new char[fileLines][fileColumns];
	}	
	
	
	/**
	 *  Removing first line of array
	 */
	private void removeFirstLine(){
		this.fileContent = Arrays.copyOfRange(this.fileContent, 1, this.fileContent.length);
	}
	
	
	/**
	 * @return Array
	 */
	public String[] getFileContent() {
		return this.fileContent;
	}

	/**
	 * @return String
	 */
	public String getPathToFile() {
		return this.pathToFile;
	}

	/**
	 * @return Array
	 */
	public char[][] getFileContentMatrix() {
		return this.fileContentMatrix;
	}

	/**
	 * @return int
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * @return  int
	 */
	public int getNuberOfColumns() {
		return nuberOfColumns;
	}
	
	
	
}
