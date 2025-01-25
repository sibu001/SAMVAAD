package com.divergent.meet.divergentmeet;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileListTest {

	@Test
	public void listOfFiles() {
		File folder = new File("/home/aakash-divergent/tutorial_video/spring_videos/");

		File[] files = folder.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				File folder2 = new File(file.getAbsolutePath());
				File[] files2 = folder2.listFiles();
				for (File file2 : files2) {
					if (file2.isFile()&&file2.getName().toLowerCase().endsWith(".mp4")) {
//						System.out.println(file2.getName());
						System.out.println(file2.getAbsolutePath().substring(5));
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		File folder = new File("/home/aakash-divergent/tutorial_video/spring_videos/1. Course Introduction/");

		File[] files = folder.listFiles();

		for (File file : files) {
//			if (file.isDirectory()) {
//				File folder2 = new File(file.getAbsolutePath());
//				File[] files2 = folder2.listFiles();
//				for (File file2 : files2) {
					if (file.isFile()&&file.getName().toLowerCase().endsWith(".mp4")) {
						String[] arrOfStr=file.getName().split("_", 2);
//						file.getName().split("_", 2);
						
					            System.out.println(arrOfStr[0]); 
					//	System.out.println(file.getAbsolutePath().substring(5));
					}
//				}
//			}
		}
		
	//	FileListTest ft=new FileListTest();
		//ft.listOfFiles();
	}
}
