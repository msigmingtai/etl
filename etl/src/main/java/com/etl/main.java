package com.etl;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

/**
 * @author 50123
 * @category 佣金檔轉TXT至統計平台
 * @version 2.0
 * @since 2015/03/17
 */
public class main
{
    // java -jar -Xmn256m -Xms512m -Xmx1024m etl.jar
    private JFrame frame;
    private JFileChooser fc=new JFileChooser();
    private File file=null;

    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    main window=new main();
		    window.frame.setVisible(true);
		}
		catch (Exception e)
		{
		    JOptionPane.showMessageDialog(null,e.getStackTrace());
		}
	    }
	});
    }

    public main()
    {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
	// set default frame property
	frame=new JFrame();
	frame.setBounds(100,100,263,129);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("佣金檔轉檔");
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	JButton btnNewButton=new JButton("開啟");
	JButton btnNewButton_1=new JButton("轉換");
	frame.getContentPane().add(btnNewButton);
	frame.getContentPane().add(btnNewButton_1);
	// set button
	btnNewButton.setFont(new Font("新細明體",Font.PLAIN,20));
	btnNewButton.setBounds(72,13,111,27);
	btnNewButton_1.setFont(new Font("新細明體",Font.PLAIN,20));
	btnNewButton_1.setBounds(72,53,111,27);
	// button action
	btnNewButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		// fc.setFileFilter(new MyFileFilter("xls","xls"));
		// fc.setFileFilter(new MyFileFilter("xlsx","xlsx"));
		fc.showOpenDialog(null);
		file=fc.getSelectedFile();

	    }
	});
	btnNewButton_1.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		createfile c=new createfile();
		try
		{
		    c.create(file);
		}
		catch (Exception e1)
		{
		    JOptionPane.showMessageDialog(null,e1.getStackTrace());
		}

	    }
	});

    }
}
