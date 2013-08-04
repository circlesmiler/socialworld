package org.socialworld.datasource;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.URL;
import org.socialworld.attributes.AttributeArray;

public class AttributeArrayPool {

	private static AttributeArrayPool instance;
	
	private static List<AttributeArray> attributes;
	
	private AttributeArrayPool () {
		attributes = new ArrayList<AttributeArray> ();

		initialize();
	}
	
	public static AttributeArrayPool getInstance() {
		if (instance == null) {
			instance = new AttributeArrayPool();
		}
		return instance;
	}
	
	public AttributeArray getArray(int index) {
		if (attributes.size() > index ) 
			return attributes.get(index);
		else {
			attributes.add(createArray());
			return attributes.get(attributes.size() - 1);
		}
		
	}

	private void initialize() {
		
		initializeFromFile();
		
		attributes.add(createArray());
		attributes.add(createArray());
		attributes.add(createArray());
		attributes.add(createArray());
		attributes.add(createArray());
		attributes.add(createArray());
	}
	
	private AttributeArray createArray() {
		int[] array;
		array = getArray();
		AttributeArray attributes = new AttributeArray(array);
		
		return attributes;
	}
	
	private int[] getArray() {
		int[] array;
		int[] array1 = {50,50,50,50,50,50,50,50};
		int[] array2 = {40,60,30,50,60,50,30,70};
		int[] array3 = {40,42,44,46,50,54,56,58};
		
		int count = attributes.size();
		
		switch (count) {
		case 1: 
			array = array2;
			break;
		case 2: 
			array = array3;
			break;
		default: 
			array = array1;
			break;
		}
		return array;
	
	}
	
	private void initializeFromFile() {
		
		try
		{
			String line;
			String values[];
			int attribIndex;
			int array[];
			
			array =new int[8];
			

			InputStream input = new URL("http://sourceforge.net/projects/socialworld/files/hmn_swa.txt").openStream();
			LineNumberReader lnr
			   = new LineNumberReader(new InputStreamReader(input));

			//FileReader in = new FileReader("C:/Users/Mathias/workspace/socialworld/data/hmn_swa.txt");
			//FileReader in = new FileReader(input);
			//LineNumberReader lnr = new LineNumberReader(in);
	
			while ((line = lnr.readLine()) != null)
			{
				if (line.startsWith("("))
				{
					line = line.substring(1);
					line = line.replace(")", "");
					line = line.trim();
					
					values = line.split(",");
					for (attribIndex = 0; attribIndex < 8; attribIndex++){
						array[attribIndex] = Integer.parseInt(values[attribIndex]);
					}
					attributes.add(new AttributeArray(array));
					
				}
			}
			lnr.close();
		}
		catch (IOException e)
		{
			System.out.println("Fehler beim Lesen der Datei");
			e.printStackTrace();
		}
	}
}
