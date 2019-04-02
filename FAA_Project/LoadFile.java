import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFile {
	
	private ArrayList<String> fileText;
	private File file;
	private BufferedReader br;
	
	//loads file and puts all text line by line into the array fileText
	public LoadFile(String path) {
		fileText = new ArrayList<String>();
		file = new File(path);		
		br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			try {
				while ((line = br.readLine()) != null){//reads a line at a time
					if(!line.equals("")) {
						fileText.add(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//accesses file as an ArrayList of Strings
	public ArrayList<String> getFileText(){
		return fileText;
	}	
}