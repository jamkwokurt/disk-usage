package diskusage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DiskUsage {
	private Scanner scan = new Scanner(System.in);
	private Map<String, Long>sizes = new HashMap<String, Long>();
	public DiskUsage() {
		System.out.println("File Path:");
		String path = scan.nextLine();
		File file = new File(path);
		System.out.println("Total size of directory "+file.getName()+" is "+sizeCalculator(file)+" bytes");
		eachSizeCalculator(file, sizes);
		printTree(1,file);
		
	}
	public long sizeCalculator(File file) {
		long size = 0;
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f:files) {
				size += sizeCalculator(f);
			}
		}else {
			size += file.length();
		}
		return size;
	}
	public void printTree(int counter, File file) {
		for(int i = 0; i < counter; i++) {
			System.out.print('-');
		}
		System.out.println(file.getName()+" ("+sizes.get(file.getAbsolutePath())+" bytes)");
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f:files) {
				printTree(counter+4, f);
			}
		}else {
			
		}
	}
	public void eachSizeCalculator(File file, Map<String, Long>sizes) {
		if(file.isDirectory()) {
			sizes.put(file.getAbsolutePath(), sizeCalculator(file));
			File[] files = file.listFiles();
			for(File f:files) {
				sizes.put(f.getAbsolutePath(), sizeCalculator(f));
				eachSizeCalculator(f, sizes);
			}
		}
		
	}
	public static void main(String[] args) {
		new DiskUsage();
	}

}
