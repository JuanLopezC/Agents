package MLR;

import jade.core.Agent; 
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;

public class MLRAgent extends Agent{
    
    private MLRGui myGui;
    double X1[] = {41.9, 43.4, 43.9, 44.5, 47.3,47.5,47.9, 50.2, 52.8, 53.2, 56.7, 57.0, 63.5, 65.3, 71.1, 77, 77.8};
    double X2[] = {29.1, 29.3, 29.5, 29.7, 29.9, 30.3, 30.5, 30.7, 30.8, 30.9, 31.5, 31.7, 31.9, 32, 32.1, 32.5, 32.9};
    double y[] = {251.3, 251.3, 248.3, 267.5, 273, 276.5, 270.3, 274.9, 285, 290, 297, 302.5, 304.5, 309.3, 321.7, 330.7, 349};
    Dataset ds = new Dataset(X1, X2, y);
    double x1 = 0, x2 = 0;

    protected void setup(){
        System.out.println("Agent " + getLocalName() + "started.");
        myGui = new MLRGui(this);
		myGui.showGui();

        DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("MLR");
		sd.setName("JADE-MLR");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
    }

    public void setXs(double x1, double x2){
        this.x1 = x1;
        this.x2 = x2;
    }

    protected void takeDown(){
            // Close the GUI
            myGui.dispose();
            // Printout a dismissal message
            System.out.println("MLR-agent "+getAID().getName()+" terminating.");
    }
    public void Matricial(){
        addBehaviour(new OneShotBehaviour(){
            public void action(){
                MLR mlr = new MLR(ds.getX1(), ds.getX2(), ds.getY());
                mlr.Matricial();
                double Y = mlr.getB0() + mlr.getB1()* x1 + mlr.getB2()* x2;
                System.out.println("B0: " + mlr.getB0() + " B1: " + mlr.getB1() + " B2: " + mlr.getB2());
                System.out.println("La predicion con Ecuacion matricial de Y es igual a = " + Y);
            }
        });
    }

    public void Crammer(){
        addBehaviour(new OneShotBehaviour(){
            public void action(){
                Cramer cr = new Cramer(ds.getArray());
                cr.calcular();
                double Y = cr.getB0() + cr.getB1() * x1 + cr.getB2() * x2;
                System.out.println("B0: " + cr.getB0() + " B1: " + cr.getB1() + " B2: " + cr.getB2());
                System.out.println("La predicion con Crammer de Y es igual a = " + Y);
            }
        });
    }
}