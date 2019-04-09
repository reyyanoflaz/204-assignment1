/**
 * @author REYYAN OFLAZ
 * @version 1.0
 */

import java.io.*;
public class FileManager{
	public static BufferedReader buffReadManager;
	public static BufferedWriter buffWriteManager;
	public static FileWriter fileWriter;
	public static File fileMng;
	public static File fileMng2;
	public static String lineManager;
	public static FileReader fileReader;
/**
 * open and read file the file
 * @param file
 * @throws
 * @return BufferedReader object
 */
	public static BufferedReader openFile(String file) throws IOException{
	
		try{
			fileMng= new File(file);
	    	fileReader = new FileReader(fileMng);
	    	buffReadManager= new BufferedReader(fileReader);
			return buffReadManager;
		}
		catch(Exception e){
			throw new IOException();
		}
		
	}
	/**
	 * writes to the file
	 * @param file
	 * @throws
	 * @return BufferedWriter object
	 */
	public static BufferedWriter writeFile (String file) throws IOException{
		try{
			fileMng2 = new File(file);
			fileWriter = new FileWriter(fileMng);
	    	buffWriteManager= new BufferedWriter(fileWriter);
			return buffWriteManager;
		}
		catch(Exception e){
			throw new IOException();
		}
	}
	/** 
	 * Closes this file
	 * @param buffered reader object
	 * @throws IOException
	 */
	public static void closeReaderFile(BufferedReader br) throws IOException{
		if(br!=null){
			br.close();
		}
	}
	public static void closeWriterFile(BufferedWriter bw) throws IOException{
		if(bw!=null){
			bw.close();
		}
	}
	
	
}
