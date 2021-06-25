package MLR;

public class Dataset {
    private double dataX1[];
    private double dataX2[];
    private double dataY[];

    Dataset(double dataX1[], double dataX2[], double dataY[]){
        this.dataX1 = dataX1;
        this.dataX2 = dataX2;
        this.dataY = dataY;
    }

    public void setX1(double dataX1[]){
        this.dataX1 = dataX1;
    }
    public double[] getX1(){
        return this.dataX1;
    }

    public void setX2(double dataX2[]){
        this.dataX2 = dataX2;
    }
    public double[] getX2(){
        return this.dataX2;
    }

    public void setY(double dataY[]){
        this.dataY = dataY;
    }
    public double[] getY(){
        return this.dataY;
    }

    public double[][] getArray(){
        double [][]arr = new double[3][dataX2.length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < dataX1.length; j++) {
                if(i == 0){
                    arr[i][j] = dataX1[j];
                }
                else if(i == 1){
                    arr[i][j] = dataX2[j];
                }
                else{
                    arr[i][j] = dataY[j];
                }
            }
        }
        return arr;
    }
}
