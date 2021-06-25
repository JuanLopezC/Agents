/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package MLR;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MLRGui extends JFrame {	
	private MLRAgent myAgent;
	
	private JTextField inputField;
	private JTextField inputField2;
	
	MLRGui(MLRAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Valor de x1:"));
		inputField = new JTextField(15);
		p.add(inputField);

		p.add(new JLabel("Valor de x2:"));
		inputField2 = new JTextField(15);
		p.add(inputField2);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Matricial");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					double x1 = Double.parseDouble(inputField.getText());
					double x2 = Double.parseDouble(inputField2.getText());
					myAgent.setXs(x1, x2);
					myAgent.Matricial();					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

		JButton addButton2 = new JButton("Cramer");
		addButton2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					double x1 = Double.parseDouble(inputField.getText());
					double x2 = Double.parseDouble(inputField2.getText());
					myAgent.setXs(x1, x2);
					myAgent.Crammer();					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		p.add(addButton2);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
