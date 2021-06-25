package MLP;

import jade.core.Agent; 
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;

public class MLPAgent extends Agent{
    
    private MLPGui myGui;
        Dataset in = new Dataset();
        ArrayList<double[]> ds = in.LeerDataSet();
        double [][]X = new double[ds.size()][ds.get(0).length-1];
        double [][]Y = new double[ds.size()][1];
        double []input = in.LeerEntrada();
        NeuralNetwork NN;

    protected void setup(){
        System.out.println("Agent " + getLocalName() + "started.");
        myGui = new MLPGui(this);
		myGui.showGui();
        ds.remove(ds.size()-1);

        for (int i = 0; i < ds.size(); i++) {
            for (int j = 0; j < ds.get(i).length; j++) {
                if(j == ds.get(i).length - 1)
                    Y[i][0] = ds.get(i)[j];
                else
                    X[i][j] = ds.get(i)[j];
            }
            
        }

        DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("MLP");
		sd.setName("JADE-MLP");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
    }


    protected void takeDown(){
            // Close the GUI
            myGui.dispose();
            // Printout a dismissal message
            System.out.println("MLP-agent "+getAID().getName()+" terminating.");
    }
    public void NN(int hi, int e){
        addBehaviour(new OneShotBehaviour(){
            public void action(){
                System.out.println("Training...");
                NN = new NeuralNetwork(25, hi, 1);
                NN.fit(X, Y, e);
                System.out.println("Ready...");
            }
        });
    }

    public void Predict(){
        addBehaviour(new OneShotBehaviour(){
            public void action(){
                List<Double> output;
                in.mostrarArray(input);
                output = NN.predict(input);
                System.out.println("\nPredicion: " + output.toString());
                if((int)Math.round(output.get(0)) == 1)
                    System.out.println("Right");
                else
                    System.out.println("Left");
            }
        });
    }

}