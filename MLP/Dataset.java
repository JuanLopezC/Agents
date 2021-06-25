package MLP;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataset {
    BufferedReader br = null;

    public ArrayList<double[]> LeerDataSet(){
        ArrayList<double[]> ds = new ArrayList<double[]>();
        try {
            br = new BufferedReader(new FileReader("C:\\jade\\src\\MLP\\Entrenamiento.csv"));
            String line = br.readLine();
            while (null != line) {
                String[] fields = line.split(",");
                double[] arr = new double[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    arr[i] = Double.parseDouble(fields[i]);
                }
                ds.add(arr);

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != br) {
                // br.close();
            }
        }
        return ds;
    }

    public double[] LeerEntrada(){
        double[] ds = new double[25];
        try {
            br = new BufferedReader(new FileReader("C:\\jade\\src\\MLP\\Entradas.csv"));
            String line = br.readLine();
            while (null != line) {
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
                    ds[i] = Double.parseDouble(fields[i]);
                }
                line = br.readLine();
                if(line == null){
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != br) {
                // br.close();
            }
        }
        return ds;
    }

    public void mostrarArray(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == 0) {
                System.out.print("  ");
            } else {
                System.out.print((int) arr[i - 1] + " ");
            }
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }
}
