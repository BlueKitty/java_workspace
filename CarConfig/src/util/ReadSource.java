package util;

import java.io.*;
import java.util.StringTokenizer;

import Car_Config.Automotive;

public class ReadSource {
	
	public Automotive buildAutoObject(String filename){
		Automotive auto = new Automotive();
		try{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			String line = null;
			int OpsetCount = 0; // initial optionset count
			int OpCount = 0;
			int lineCount = 0;
			while( (line = buff.readLine()) != null){
				if(lineCount == 0){
					//System.out.println(line);
					auto.setName(line);
					lineCount++;
				}else{
					if(line.contains("OptionSet:")){
						//Set up OptionSet Name and Size
						
						StringTokenizer st = new StringTokenizer(line);
						if(st.nextToken().equals("OptionSet:")){
							OpsetCount++;
							String OpsetName = st.nextToken();
							if(OpsetName.endsWith(",")){
								OpsetName = OpsetName.substring(0, OpsetName.length() - 1);
							}
							int OpsetSize = Integer.parseInt(st.nextToken());
							//System.out.println(OpsetName);
							//System.out.println(OpsetSize);
							auto.setOpSet(OpsetCount-1, OpsetName, OpsetSize);;
							OpCount = 0;
						}else{
							System.out.println("Watch out. This isn't OptionSet info.");
						}
						
					}else{
						//Set up Option: name and price
						String[] parts = line.split(",");
						//System.out.println(parts[0]);
						//System.out.println(parts[1]);
						String OpName = parts[0];
						float OpPrice = Float.parseFloat(parts[1]);
						auto.setOption(auto.getOptionsetName(OpsetCount-1), OpCount, OpName, OpPrice);
						OpCount++;
					}
					
					lineCount++;
				}
			}
			buff.close();
		}catch (IOException e) {
			System.out.print("Error --" + e.toString());
		}
		return auto;		
	}
			
}
