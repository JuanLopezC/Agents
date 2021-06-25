package MLP;
import java.util.List;

public class NeuralNetwork {
	
	Data weights_ih,weights_ho , bias_h,bias_o;	
	double l_rate=0.01;
	
	public NeuralNetwork(int i,int h,int o) {
		weights_ih = new Data(h,i);
		weights_ho = new Data(o,h);
		
		bias_h= new Data(h,1);
		bias_o= new Data(o,1);
		
	}
	
	public List<Double> predict(double[] X)
	{
		Data input = Data.fromArray(X);
		Data hidden = Data.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();
		
		Data output = Data.multiply(weights_ho,hidden);
		output.add(bias_o);
		output.sigmoid();
		
		return output.toArray();
	}
	
	
	public void fit(double[][]X,double[][]Y,int epochs)
	{
		for(int i=0;i<epochs;i++)
		{	
			int sampleN =  (int)(Math.random() * X.length );
			this.train(X[sampleN], Y[sampleN]);
		}
	}
	
	public void train(double [] X,double [] Y)
	{
		Data input = Data.fromArray(X);
		Data hidden = Data.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();
		
		Data output = Data.multiply(weights_ho,hidden);
		output.add(bias_o);
		output.sigmoid();
		
		Data target = Data.fromArray(Y);
		
		Data error = Data.subtract(target, output);
		Data gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(l_rate);
		
		Data hidden_T = Data.transpose(hidden);
		Data who_delta =  Data.multiply(gradient, hidden_T);
		
		weights_ho.add(who_delta);
		bias_o.add(gradient);
		
		Data who_T = Data.transpose(weights_ho);
		Data hidden_errors = Data.multiply(who_T, error);
		
		Data h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(l_rate);
		
		Data i_T = Data.transpose(input);
		Data wih_delta = Data.multiply(h_gradient, i_T);
		
		weights_ih.add(wih_delta);
		bias_h.add(h_gradient);
	}


}