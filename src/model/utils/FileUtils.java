package model.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils{

	public static BufferedReader loadFile(String fileName) throws IOException {
		return new BufferedReader(new FileReader(fileName)); 
	}
}
