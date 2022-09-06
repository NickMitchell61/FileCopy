package book.fain.yakov;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewFileCopy extends JFrame implements ActionListener{
	
	JPanel windowContent;
	JTextField copyFrom = new JTextField("C:\\Users\\nickm\\example_name.txt");
	JTextField copyTo = new JTextField("C:\\Users\\nickm\\NEW_example_name.txt");
	JButton enterToCopy = new JButton("Copy");
	JLabel copyFromLabel = new JLabel("Copy from: ");
	JLabel copyToLabel = new JLabel("Copy to: ");
	JButton browseButton = new JButton("Browse");
	JButton browseButton2 = new JButton("Browse");
	
	public ViewFileCopy() {
		
		Font font = new Font("Consolas", Font.BOLD, 20);
		Font font1 = new Font("Consolas", Font.ITALIC, 15);
		
		copyFrom.setFont(font1);
		copyTo.setFont(font1);
		enterToCopy.setFont(font);
		copyFromLabel.setFont(font);
		copyToLabel.setFont(font);
		browseButton.setFont(font);
		browseButton2.setFont(font);
		
		windowContent = new JPanel();
		windowContent.setLayout(new GridLayout(3, 3));
		
		
		browseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileOpen = new JFileChooser();
				fileOpen.showDialog(null, "Select");
				File file = fileOpen.getSelectedFile();
				copyFrom.setText(file.getAbsolutePath());
			}
		});
		
		browseButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileOpen = new JFileChooser();
				fileOpen.showDialog(null, "Copy to");
				File file = fileOpen.getSelectedFile();
				copyTo.setText(file.getAbsolutePath());
				
			}
		});
		
		windowContent.add(copyFromLabel);
		windowContent.add(copyFrom);
		windowContent.add(browseButton);
		windowContent.add(copyToLabel);
		windowContent.add(copyTo);
		windowContent.add(browseButton2);
		windowContent.add(new JLabel(""));
		
		
		copyFrom.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked (MouseEvent e) {
				copyFrom.setText(" ");
			}
		});
		
		
		copyTo.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked (MouseEvent e) {
				copyTo.setText(" ");
			}
		
		});
		
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
