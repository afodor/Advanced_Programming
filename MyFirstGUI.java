package scratch;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.util.*;
import javax.swing.*;

public class MyFirstGUI extends JFrame
{
	private class MetricListener implements ActionListener
	{

		public void actionPerformed(ActionEvent event2)
		{
			float height = Integer.parseInt(JOptionPane.showInputDialog("Enter your height (in meters) here."));
			float weight = Integer.parseInt(JOptionPane.showInputDialog("Enter your weight (in kilograms) here."));
			if (height !=0 && weight !=0)
			{
				float bodyMassIndex = (float)(weight / (height * height)); 
				updateTextField(bodyMassIndex);
				saveToFile(height, weight, bodyMassIndex);
			}
			
		}
	}
	
	private class ImperialListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event1)
		{
			float height = Integer.parseInt(JOptionPane.showInputDialog("Enter your height (in inches) here."));
			float weight = Integer.parseInt(JOptionPane.showInputDialog("Enter your weight (in pounds) here."));
			if (height !=0 && weight !=0)
			{
				float bodyMassIndex = (float)(703 * (weight / (height * height)));
				updateTextField(bodyMassIndex);
				saveToFile(height, weight, bodyMassIndex);
			}

		}
	}
	
	private JPanel getBottomPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,3));
		panel.add(imperialButton);
		panel.add(metricButton);

		
		metricButton.addActionListener(new MetricListener());
		metricButton.setMnemonic('M');
		metricButton.setToolTipText("Click here to calculate your BMI using the Metric Formula.");
		
		imperialButton.addActionListener(new ImperialListener());
		imperialButton.setMnemonic('I');
		imperialButton.setToolTipText("Click here to calculate your BMI using the Imperial Formula.");
	
		return panel;
		
	}

	private void updateTextField(float bodyMassIndex)
	{
		aTextField.setText("My Body Mass Index is " + bodyMassIndex);
		validate();
	}
	
	private static final long serialVersionUID = -5465077034163085045L;
	private JTextField txtInput = new JTextField("");
	private JTextField aTextField = new JTextField("");
	private JButton metricButton = new JButton("Calculate BMI: Metric");
	private JButton imperialButton = new JButton("Calculate BMI: Imperial");
	float bodyMassIndex = 0;
	
	public MyFirstGUI()
	{
		super("BMI Calculator");
		setLocationRelativeTo(null);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getBottomPanel(), BorderLayout.SOUTH);
		getContentPane().add(aTextField, BorderLayout.CENTER);
		setVisible(true);
	}

	private void saveToFile(float height, float weight, float bodyMassIndex)
	{
		JFileChooser jfc = new JFileChooser();
		if (jfc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		if (jfc.getSelectedFile() == null)
			return;
		File chosenFile = jfc.getSelectedFile();
		
		if (jfc.getSelectedFile().exists())
		{
			String message = "File " + jfc.getSelectedFile().getName() + "exists. Overwrite?";
			
			if (JOptionPane.showConfirmDialog(this, message) !=
					JOptionPane.YES_OPTION)
					return;
		}
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(chosenFile));
			writer.write("My height is: " + height + ".\nMy weight is: " + weight + "\nMy BMI is: " + bodyMassIndex + ".");
			writer.flush(); writer.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Could not write file", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main (String[] args)
	{
		new MyFirstGUI();
	}
}
