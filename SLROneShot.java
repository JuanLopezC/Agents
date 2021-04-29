package SLR;

import jade.core.Agent; 
import jade.core.behaviours.OneShotBehaviour;
import java.util.Scanner;

public class SLROneShot extends Agent{
    
    private SLRGui myGui;
    double[] benettonx = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    double[] benettony = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    protected void setup(){
        System.out.println("Agent " + getLocalName() + "started.");
        myGui = new SLRGui(this);
		myGui.showGui();
        // addBehaviour(new MyOneShotBehaviour());
    }

    public void createSLR(final double x){
        addBehaviour(new OneShotBehaviour(){
            public void action(){
                SLR slr = new SLR(benettonx, benettony, x);
                slr.prediction();
            }
        });
    }

    
    
    // private class MyOneShotBehaviour extends OneShotBehaviour {
        
    //     public void action (){
              
    //         double[] benettonx = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    //         double[] benettony = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    //         System.out.print("Inserta el valor de x: ");
    //         Scanner sc = new Scanner(System.in);
    //         double x = sc.nextDouble();
    //         SLR slr = new SLR(benettonx, benettony, x);
    //         slr.prediction();
    //         sc.close();
    //     }    
          
    //     public int onEnd(){
    //         myAgent.doDelete();
    //         return super.onEnd();
    //     }  
    // }
}