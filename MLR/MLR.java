package MLR;

public class MLR{

    double[] x1;
    double[] x2;
    double[] y;
    double B0, B1, B2;
    int len, dep;
    
    int tran = 0;


    public MLR(double[] x1, double[] x2, double[] y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
        len = x1.length;
        dep = 3;
    }

    public void Matricial(){

        //Matriz X
        double X[][] = X();
        //Matriz X'
        double XT[][] =trasnspose(X);
        //Matriz Y
        double Y[][] = Y();


        if(X.length != XT[0].length){
            throw new RuntimeException("No se puede realizar multiplicacion de matrices, revisar dataset");
        }

        //Multiplicar X'*X
        double [][]XR = multiply(X, XT);
        //(X'*X)-1
        double[][]XRI = inverse(XR);
        double [][]XY = multiply(Y, XT);
        double[][]res = multiply(XY, XRI);

        this.B0 = res[0][0];
        this.B1 = res[0][2];
        this.B2 = res[0][1];
    }
    

    //Crear matriz X
    public double[][] X(){
        double [][]X = new double[dep][len];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < len; j++) {
                if(i == 0){
                    X[i][j] = 1;
                }
                else if(i == 2){
                    X[i][j] = this.x1[j];
                }
                else{
                    X[i][j] = this.x2[j];
                }
            }
        }
        return X;
    }

    public double[][]Y(){
        double [][]Y = new double[1][y.length];
        for(int i = 0; i < y.length; i++){
            Y[0][i] = y[i];
        }
        return Y;
    }

    //Crea la matriz inversa
    public double[][] inverse(double[][]X){
        double det = 1/determinante(X);
        double[][]adjX = adjMatrix(X);

        for (int i = 0; i < adjX.length; i++) {
            for (int j = 0; j < adjX.length; j++) {
                adjX[i][j]*=det;
            }
        }

        return adjX;
    }
    //Funcion transponer X
    public double[][] trasnspose(double[][]X){
        double [][] XT = new double[X[0].length][X.length];

        for (int i = 0; i < X[0].length; i++) {
            for (int j = 0; j < X.length; j++) {
                XT[i][j] = X[j][i];
            }
        }
        return XT;

    }

    //Calcular el determinante
    public static double determinante(double[][] matriz){
        double det;
        if(matriz.length==2){
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
        double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                            nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }


    //Calcular matriz adjunta
    public double[][] adjMatrix(double[][]X){

        double[][] nm=new double[X.length][X.length];
        for(int i=0;i<X.length;i++) {
            for(int j=0;j<X.length;j++) {
                double[][] det=new double[X.length-1][X.length-1];
                double detValor;
                for(int k=0;k<X.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<X.length;l++) {
                            if(l!=j){
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=X[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return trasnspose(nm);
    }

    public void impMat(double[][]X){
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[0].length; j++) {
                System.out.print(X[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        if (a[0].length == b.length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
       
        return c;
    }

    public double getB0(){
        return this.B0;
    }
    public double getB1(){
        return this.B1;
    }
    public double getB2(){
        return this.B2;
    }
}