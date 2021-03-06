package lab2;

import java.io.*;
import java.util.StringTokenizer;

public class Util {
	static Student [] readFile( String filename, Student [] stu ) {
		//Reads the file and builds student array
		//open the file using FileReader Object
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			String line = null;
			int lineCount = 0;
			while( (line = buff.readLine()) != null) {
				if (lineCount == 0) {
				System.out.println(line);
				lineCount ++;
				} else {
					
					StringTokenizer st = new StringTokenizer(line);
					int tokenCount = 0;
					while(st.hasMoreTokens()) {
						if (tokenCount == 0) {
							//set SID for Student[lineCount - 1]
							int sid = Integer.parseInt( st.nextToken() );
							stu[lineCount - 1] = new Student();
							stu[lineCount - 1].setSID(sid);
							stu[lineCount - 1].printSID();
							tokenCount ++;
						} else {
							//set quiz scores for Student[lineCount - 1].scores[tokenCount -1 ]
							int score = Integer.parseInt(st.nextToken());
							stu[lineCount - 1].setScore(tokenCount - 1, score);
							stu[lineCount - 1].printScoreI(tokenCount - 1);
							tokenCount ++;
						}
					}
					System.out.println();
					lineCount ++;
														
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.print("Error --" + e.toString());
		}

		
		return stu;
	}

	}


