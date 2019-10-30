package scratch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class MyFirstGUI extends JFrame
{
	private class DoubleActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if(random.nextFloat() < 0.5)
				numCats = numCats + 1;
			else
				numCats = numCats + 0;
			updateTextField();
		}
	}
	
	private void updateTextField()
	{
		aTextField.setText("You have " + numCats + " cat(s).");
		validate();
	}
	
	private static final long serialVersionUID = -5465077034163085045L;
	
	private JTextField aTextField = new JTextField();
	private JButton doubleButton = new JButton("I am a cat lady.");
	private int numCats = 1;
	private Random random = new Random();
	
	
	public MyFirstGUI()
	{
		super("Cat Clinic");
		setLocationRelativeTo(null);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(doubleButton, BorderLayout.SOUTH);
		doubleButton.addActionListener(new DoubleActionListener());
		getContentPane().add(aTextField, BorderLayout.CENTER);
		updateTextField();
		setVisible(true);
	}
	
	
	public static void main (String[] args)
	{
		new MyFirstGUI();
	}
}
