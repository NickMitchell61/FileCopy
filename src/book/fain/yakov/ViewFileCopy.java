package book.fain.yakov;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class ViewFileCopy extends JFrame implements ActionListener{
	
	JPanel windowContent;
	JTextField copyFrom = new JTextField();
	JTextField copyTo = new JTextField();
	JButton enterToCopy = new JButton("Copy");
	JLabel copyFromLabel = new JLabel("Copy from: ");
	JLabel copyToLabel = new JLabel("Copy to: ");
	JLabel browseLabel = new JLabel("Browse");
	JLabel browseLabel2 = new JLabel("Browse");
	
	public ViewFileCopy() {
		
		Font font = new Font("Consolas", Font.BOLD, 20);
		Font font1 = new Font("Consolas", Font.ITALIC, 15);
		
		copyFrom.setFont(font1);
		copyTo.setFont(font1);
		enterToCopy.setFont(font);
		copyFromLabel.setFont(font);
		copyToLabel.setFont(font);
		browseLabel.setFont(font);
		browseLabel2.setFont(font);
		
		windowContent = new JPanel();
		windowContent.setLayout(new GridLayout(3, 3));
		
		
		windowContent.add(copyFromLabel);
		windowContent.add(copyFrom);
		windowContent.add(browseLabel);
		windowContent.add(copyToLabel);
		windowContent.add(copyTo);
		windowContent.add(browseLabel2);
		windowContent.add(new JLabel(""));
		
		
		
		
		
		enterToCopy.addActionListener(this);
		windowContent.add(enterToCopy);
		
		setContentPane(windowContent);
		setSize(800, 150);
		setLocation(600, 200);
		setTitle("CopyFile");
		setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		String selectedCopyFromText = copyFrom.getText();
		String selectedToCopyText = copyTo.getText();
		
		try {
			fis = new FileInputStream(selectedCopyFromText);
			fos = new FileOutputStream(selectedToCopyText);
			
			while (true) {
				int intValueOfByte = fis.read();
				
				try {
					fos.write(intValueOfByte);
				} catch (IOException e1) {
					System.out.println("Неможливо записати файл: " + e1.toString());
				}
	
				if (intValueOfByte == -1) {
					break;
				}
				
			}

		} catch (IOException e2) {
			System.out.println("Неможливо прочитати файл: " + e.toString());
		} finally {
			
			try {
				fis.close();
				fos.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			
			System.out.println("Файл зчитаний!");
			System.out.println("Новий файл записаний!");
			
		}
		
	}
	
	public static void main(String[] args) {
		new ViewFileCopy();
	}

}
