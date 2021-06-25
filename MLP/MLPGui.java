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

package MLP;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyDescriptor;

import javax.swing.*;

class MLPGui extends JFrame {	
	private MLPAgent myAgent;
	
	private JTextField inputField;
	private JTextField inputField2;
	
	MLPGui(MLPAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Hidden layer neurons:"));
		inputField = new JTextField(15);
		p.add(inputField);
		p.add(new JLabel("Epoach:"));
		inputField2 = new JTextField(15);
		p.add(inputField2);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Train");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int hid = Integer.parseInt(inputField.getText());
					int	e = Integer.parseInt(inputField.getText());
					myAgent.NN(hid, e);		
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLPGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

		JButton predict = new JButton("Predict");
		predict.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					myAgent.Predict();			
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLPGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

		p = new JPanel();
		p.add(addButton);
		p.add(predict);
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
