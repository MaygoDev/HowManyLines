package fr.maygo.howmanylines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HowManyLines {
	
	static int lines, classes, packages = 0;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		File src = new File("src");
		
		if (!src.exists()) {
			System.err.println("The source folder wasn't found");
			return;
		}else {
			System.out.println("The source folder was found");
		}

        search(src);
		System.out.println("There is a Total of " + lines + " lines, "+packages+" packages and " + classes + " classes.");
		File log = new File(new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date())+".hml");
		log.createNewFile();
		FileWriter writer = new FileWriter(log);
		writer.write("There is a Total of " + lines + " lines, "+packages+" packages and " + classes + " classes.");
		writer.flush();
		writer.close();
	}

	public static void search(final File folder) throws IOException, InterruptedException {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f);
                packages++;
            }

            if (f.isFile()) {
            	int fileLines = Files.readAllLines(Paths.get(f.getPath())).size();
            	
            	classes++;
            	lines += fileLines;
            	
            	System.out.println("In the File "+f.getName()+", There is "+fileLines+" lines.");
            }

        }
    }

}
