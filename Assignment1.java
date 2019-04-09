/**
 * @author REYYAN OFLAZ
 * measured times for different data set is:
 * 
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Assignment1 {
	protected static final String csvSplitBy = ",";			
	private static int sortingKeyIdx;
	private static boolean saveFile = false;	
	private static int k;
	public static void main(String[] args) throws IOException,ParseException,ClassCastException{	
		sortingKeyIdx=Integer.parseInt(args[1]);
		if(args[2].toLowerCase().contains("t"))	saveFile=true;
		 String absolutePath = Assignment1.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		 absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"))+args[0].substring(args[0].indexOf("/"), args[0].length());
		 FileManager.buffReadManager=FileManager.openFile(absolutePath);	
		 SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 ArrayList<Double> featureDouble=new ArrayList<Double>(); 
		 ArrayList<Date> featureDate=new ArrayList<Date>();
		 ArrayList<String> featureString=new ArrayList<String>();
		 HashMap<Integer,ArrayList<String>> trafficFlowData=new HashMap<Integer,ArrayList<String>>();
		 String line;
		 StopWatch timer1=new StopWatch();
		 String flowLine=FileManager.buffReadManager.readLine();
		 String flowKey=flowLine.split(csvSplitBy)[sortingKeyIdx];
		 boolean timeStampFlag = false;
		 if(flowKey.toLowerCase().contains("Timestamp".toLowerCase()))	 timeStampFlag=true;
		 while((line = FileManager.buffReadManager.readLine()) != null) {
			 if(timeStampFlag==true)	 processLine(line,dateFormat.parse(line.split(csvSplitBy)[sortingKeyIdx]),featureDate,trafficFlowData);
			 else						 processLine(line,Double.parseDouble(line.split(csvSplitBy)[sortingKeyIdx]),featureDouble,trafficFlowData); 
		 }
		 /*SELECTION SORT ALGORITHM
		  * if(timeStampFlag==true)		new SelectionSort<Date>().selectionSort(featureDate, trafficFlowData);
		  * else						new SelectionSort<Double>().selectionSort(featureDouble, trafficFlowData);
		  */
		   if(timeStampFlag==true)		new QuickSort<Date>().sort(featureDate, trafficFlowData);
		   else 						new QuickSort<Double>().sort(featureDouble, trafficFlowData);
			

		  /* if(timeStampFlag==true)		new HeapSort<Date>().sort(featureDate,trafficFlowData);
		   * else 						new HeapSort<Double>().sort(featureDouble,trafficFlowData);
		   */
		
		/*RADIX SORT ALGORITHM
		 * featureString=(timeStampFlag==true) ? createArrayList(featureDate) : createArrayList(featureDouble);
		 * RadixSort.sort(featureString);
	     	 */
		 double time1 = timer1.elapsedTime();
		 System.out.println("measured time is"+time1);
		
		 FileManager.closeReaderFile(FileManager.buffReadManager);
		 Iterator it = trafficFlowData.entrySet().iterator();
		    while (it.hasNext()) {
		    	 Map.Entry me = (Map.Entry)it.next();
		         System.out.println("Key is after: "+me.getKey() + " &   after value is: "+me.getValue());	
		   }
		if(saveFile==true)	{
			FileManager.buffWriteManager=FileManager.writeFile(absolutePath);
		for (Entry<Integer, ArrayList<String>> entry : trafficFlowData.entrySet()) {
			FileManager.buffWriteManager.write(entry.getValue().toString());
			FileManager.buffWriteManager.newLine();
		}
		}
	}	
	/*@SuppressWarnings("unused")							//Implemented for Radix Sort
	private static <T extends Comparable<T>> ArrayList<String> createArrayList(ArrayList<T> arrList) 
			throws NumberFormatException,IllegalFormatConversionException{
		ArrayList<String> featureString=new ArrayList<String>();
		String currValue;
		 for(T s : arrList) {
			 currValue = String.format("%.0f",(double)s*Math.pow(10, 8));
			 System.out.println(currValue);
	         featureString.add(currValue);
    	 } 
		return featureString;
	}
	*/
	private static <T extends Comparable<T>>void processLine(String lineHandler,T keyData,ArrayList<T> featureDataHandler,HashMap<Integer,ArrayList<String>> trafficFlowDataHandler) 
			throws NumberFormatException,IllegalFormatConversionException{
		String[] line=lineHandler.split(csvSplitBy);
		featureDataHandler.add(keyData);
		trafficFlowDataHandler.put(k, new ArrayList<>(Stream.of(line).collect(Collectors.toList())));
		k++;
	}
}


