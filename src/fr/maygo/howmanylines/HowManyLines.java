package fr.maygo.howmanylines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HowManyLines {
	
	static int lines, classes, packages = 0;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		File src = new File("src");
		
		if (!src.exists()) {
			System.err.println("The source folder wasn't found");
			Thread.sleep(5000L);
			System.exit(0);
		}else {
			System.out.println("The source folder was found");
		}

        search(src);
		System.out.println("There is a Total of " + lines + " lines and "+packages+". For " + classes + " Classes.");
		Thread.sleep(10000L);
	}

	public static void search(final File folder) throws IOException, InterruptedException {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f);
                packages++;
            }

            if (f.isFile()) {
            	int fileLines = 0;
            	FileReader reader = new FileReader(f);
            	BufferedReader buffer = new BufferedReader(reader);
            	
            	while(buffer.readLine() != null) {
            		fileLines++;
            	}
            	
            	buffer.close();
            	reader.close();
            	
            	classes++;
            	lines += fileLines;
            	
            	System.out.println("In the File "+f.getName()+", There is "+fileLines+" lines.");
            	Thread.sleep(fileLines*2);
            }

        }
    }

}
