package SLR;

public class SLR {

    double B0, B1, n, x;
    double[] X, Y;

    public SLR(double[] X, double[] Y, double x){
        this.X = X;
        this.Y = Y;
        this.n = X.length;
        this.x = x;
        this.B1 = this.B1();
        this.B0 = this.B0();
    }

    private double B1(){
        double sumX, sumY, sumXY, sumX2;
        sumX = sumY = sumXY = sumX2 = 0;

        for (int i = 0; i < this.n; i++) {
            sumX += this.X[i];
            sumY += this.Y[i];
            sumXY += this.X[i] * this.Y[i];
            sumX2 += Math.pow(this.X[i], 2);
        }

    
        return (((this.n * sumXY) - (sumX*sumY)) / ((this.n * sumX2) - (sumX*sumX)));
    }

    private double B0(){
        double sumX, sumY;
        sumX = sumY = 0;
        for (int i = 0; i < this.n; i++) {
            sumX += this.X[i];
            sumY += this.Y[i];
        }
        return (sumY - (this.B1 * sumX)) / this.n;
    }

    public void prediction(){
        double y = this.B0 + (this.B1 * this.x);
        System.out.println("La prediccion con entrada x = "+ this.x + " es: " + y);
        System.out.println("B0: " + this.B0 + " B1: " + this.B1 );
    }

}
