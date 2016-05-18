import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	
 
       /*
        * 
        Main executed method
		
		
		Takes arguments: topWall bottomWall leftWall rightWall "file_to_be_read.csv"
 			topWall = the top of the bounding box;
 			bottomWall = bottom of the bounding box;
 			leftWall = the left side of bounding box;
 			rightWall = the right side of bounding box;
 			fileToBeRead = name of CSV file with necessary data (I was given "sample_data.csv", 600k rows, 3 columns)
 				****Make sure fileToBeRead is in same directory as acmeQueryBox.jar****
 			 
 			
 		example command line call:
 			java -jar acmeQueryBox.jar 120 85 3 30 "sample_data.csv"
 			
 		output:
 		 	new file called "query_results.txt" will be in current directory
         	length of time program took to run
        
        */
	   
        public static void main(String[] args) {
        	
        	//performance tracking
			long startTime = System.currentTimeMillis();
			
			//necessary variables
        	double topWall = Double.parseDouble(args[0]);
			double bottomWall = Double.parseDouble(args[1]);
			double leftWall = Double.parseDouble(args[2]);
			double rightWall = Double.parseDouble(args[3]);
			
			//to be parsed
			String readFile = args[4];   
			String writeFile = "query_results.txt";
        	BufferedReader reader = null;
        	BufferedWriter writer = null;
			
        	try {
        		
        		//Opens file to be written to
    			writer  = new BufferedWriter(new FileWriter(new File(writeFile)));
        	    //Opens file to be parsed
    			reader = new BufferedReader(new FileReader(readFile));
        	    String data;
        	    
        	    while ((data = reader.readLine()) != null) { 
        	    	//note -- reader.nextLine() is much slower than reader.readLine();     	        
        	        
        	    	StringTokenizer values = new StringTokenizer(data, ",");
        	        // split() changes each row to an array and is really slow as a result
        	    	// StringTokenizer keeps each row as a String and is much faster
        	        
        	    	//gets x value from row 
        	    	double x = Double.parseDouble((String)values.nextElement());
        	        //gets y value from row
        	    	double y = Double.parseDouble((String)values.nextElement());
        	    	//these variables are all true if a given point is in the given box
        	        boolean left = x > leftWall;
        	        boolean right = x < rightWall;
        	        boolean top = y < topWall;
        	        boolean low = y > bottomWall;
        	        
        	        //checks if point is in box and adds it to written file if it is
        	        if(left && right && top && low){
        	        	writer.write(data + "\n");
    				}
        	    }
        	    writer.flush();
        	    writer.close();
        	    reader.close();
        	    
        	    long endTime = System.currentTimeMillis();
        		System.out.println("Took "+(endTime - startTime) + " ms");
        	} catch (IOException e) {
        	    e.printStackTrace();
        	} 
       	}
}