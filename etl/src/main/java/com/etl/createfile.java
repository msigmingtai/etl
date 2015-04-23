package com.etl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class createfile
{

    private File output;
    private String Line=null;
    private Calendar cal=Calendar.getInstance();
    private JFileChooser save=new JFileChooser();
    private int day=cal.get(Calendar.DATE);
    private int month=cal.get(Calendar.MONTH)+1;
    private int year=cal.get(Calendar.YEAR);
    private int hour=cal.get(Calendar.HOUR);
    private int minute=cal.get(Calendar.MINUTE);
    private int second=cal.get(Calendar.SECOND);
    private String time=Integer.toString(year)+Number(month)+Number(day);

    public void create(File file) throws IOException, InvalidFormatException
    {
	output=new File("D:\\CMF388_"+time+".D");
	PrintWriter writer=new PrintWriter(output,"MS950");
	try
	{
	    Workbook workbook=WorkbookFactory.create(new File(file.getAbsolutePath()));
	    Sheet sheet=workbook.getSheetAt(0);
	    for(Row row : sheet)
	    {
		for(Cell cell : row)
		{
		    if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC)
		    {
			Line=(Line+cell.getNumericCellValue()+"|").trim().replace(" ","").replace("null","");
		    }
		    else
		    {
			Line=Line+(String)cell.getStringCellValue().trim()+"|".replace(" ","").replace("null","");
		    }
		}
		writer.write(Line+"\n");
		Line=null;
	    }

	    writer.close();
	    JOptionPane.showMessageDialog(null,"轉檔完成"+output.getAbsolutePath());
	    // 另存新檔動作
	    savefile();
	}
	catch (java.lang.OutOfMemoryError e)
	{
	    JOptionPane.showMessageDialog(null,e.getMessage()+"\n筆數太多請分成兩次產生D檔在合併起來");
	}

    }

    public void savefile()
    {
	save.setSelectedFile(new File(output.getName()));
	int rVal=save.showSaveDialog(null);
	if(rVal==JFileChooser.APPROVE_OPTION)
	{
	    new Thread(new upload(output,save.getSelectedFile().getAbsolutePath())).run();
	}
    }

    private String Number(int value)
    {
	String number=null;
	if(value<10)
	{
	    number="0"+value;
	}
	else
	{
	    number=""+value;
	}
	return number;
    }
}
