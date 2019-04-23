import java.io.*;

/**
 * Methods for generating, storing and checking hashcodes for a puzzle
 * 
 * @author Cyrus Baker
 *
 */
public class HashPuzzle {
	
	/**
	 * generate a string of the contents of a file
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static String genString(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader (f));
		String line = null;
		StringBuilder sb = new StringBuilder();
		String ls = System.clearProperty("line.separator");
		
		sb.append(f.getPath());
		
		try {
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith("Hash:")) {
				    sb.append(line);
				    sb.append(ls);
				}
			}
			return sb.toString();
		} finally {
			reader.close();
		}
	}
	
	/**
	 * Generates a hash code from a file as a string
	 * 
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static int genHash(File f) throws IOException {
		// default no file, hash set to -1
		int v = -1;
		boolean bool = false;
		
		// turn file contents to string
		String hashStr = genString(f);
			
		v = hashStr.hashCode();
		
		bool = f.exists();
		
		if (bool) {
			System.out.println(v);
			return v;
		}
		
		System.err.println("File not found");
		return v;
	}
	
	/**
	 * Reads the hash from a line in a puzzle file
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static int getHashFromString(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader (f));
		String line = null;
		StringBuilder sb = new StringBuilder();
		
		
		
		
		String v;
		
		try {
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Hash:")) {
				    sb.append(line);
				   
				}
			}
			v = sb.toString().substring(5);
			
			return Integer.parseInt(v);
		} finally {
			reader.close();
		}
		
	}
	
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean equals(int v1, int v2) {
		if (v1 == v2) return true;
		return false;
	}
	
	/*
	 
	public static void writeHash(File f, int v) throws IOException {
		String vStr = Integer.toString(v);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
		BufferedReader reader = new BufferedReader(new FileReader (f));
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Hash:")) {
				    writer.append(vStr);
				}
			}
			return;
		} finally {
			reader.close();
		}
		
		
	}*/
	
	public static void main(String args[]) throws IOException {
		File p = new File("puzzles/DefaultTestPuzzle.spsf");
		int v = genHash(p);
		System.out.println(getHashFromString(p));
		//writeHash(p, v);
		//System.out.println(getHashFromString(p));
		
	}
}
