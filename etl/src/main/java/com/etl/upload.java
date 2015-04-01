package com.etl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class upload implements Runnable
{

    private static JFrame frame;
    private static JProgressBar progressBar=new JProgressBar();
    private File upFile;
    private String save;

    public upload(File file, String path)
    {
	save=path;
	upFile=file;
	frame=new JFrame();
	frame.setBounds(100,100,307,120);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	progressBar.setStringPainted(true);
	progressBar.setBounds(14,31,255,28);
	frame.getContentPane().add(progressBar);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
    }

    private static void copyfile(String srFile, String dtFile)
	    throws IOException
    {
	OutputStream out=null;
	InputStream in=null;
	try
	{
	    File f1=new File(srFile);
	    File f2=new File(dtFile);
	    in=new FileInputStream(f1);
	    long Total=f1.length();
	    long percent;
	    out=new FileOutputStream(f2);

	    byte[] buf=new byte[1024];
	    int len;
	    int sublen=0;
	    // System.out.println("TOTAL:" + Total);
	    while ((len=in.read(buf))>0)
	    {
		out.write(buf,0,len);
		sublen=len+sublen;
		percent=(int)(sublen/Total*100);
		progressBar.setValue((int)percent);
		System.out.println(percent);
	    }

	    in.close();
	    out.close();
	    frame.dispose();
	}
	catch (FileNotFoundException ex)
	{
	    System.out.println(ex.getMessage()+" in the specified directory.");
	    System.exit(0);
	}
	catch (IOException e)
	{
	    System.out.println(e.getMessage());
	}
	finally
	{
	    in.close();
	    out.close();
	}
    }

    public void fileupload() throws IOException
    {
	InputStream in=new FileInputStream(upFile);
	copyfile(upFile.getAbsolutePath(),save);

    }

    @Override
    public void run()
    {
	try
	{
	    fileupload();
	}
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
