package com.etl;

import java.io.*;

import javax.swing.JFileChooser;

public class FileSave {

	private static void copyfile(String srFile, String dtFile) {
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);
			long Total = f1.length();
			long percent;
			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);
			// For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			int sublen = 0;
			System.out.println("TOTAL:" + Total);
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
				sublen = len + sublen;
				percent = (int) (sublen / Total * 100);
				System.out.println(percent);
			}
			System.out.println(sublen);

			in.close();
			out.close();
			System.out.println("File copied.");
		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		JFileChooser open = new JFileChooser();
		JFileChooser save = new JFileChooser();
		int rVal = open.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			// default save file name
			save.setSelectedFile(new File(open.getSelectedFile().getName()));
			// show dialog
			save.showSaveDialog(null);
			copyfile(open.getSelectedFile().getAbsolutePath(), save
					.getSelectedFile().getAbsolutePath());
		}
	}
}
