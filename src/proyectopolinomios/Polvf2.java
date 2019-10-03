
package proyectopolinomios;

import javax.swing.*;


public class Polvf2 
{
    private int n;
    private float vec[];

    public Polvf2(int canterm) {
        n=canterm*2+1;
        vec=new float[n];
        vec[0]=canterm;
    }
    //Metodo para mostrar
    public void mostrar() {
        StringBuilder salida = new StringBuilder("<html>");
        for(int k=1;k<vec[0]*2+1;k+=2)
        {
            if(vec[k+1]>0&&k>2)
            {
                salida.append("+");
            }
            salida.append(vec[k + 1]).append("X<sup>").append(String.valueOf((int) vec[k])).append("</sup>");
        }
           salida.append("</html>");
         JOptionPane.showMessageDialog(null,"Terminos del polinomio\n"+salida);  
    }

    //Metodo para almacenar un termino en el polinomio
    public void almacenarTerm(float coef, int exp) {
        int k = 1, j;
        while (k < vec[0] * 2 + 1 && vec[k] > exp && vec[k + 1] != 0) {
            k += 2;
        }
        if (k < vec[0] * 2 + 1 && vec[k] == exp && vec[k + 1] != 0) {
            JOptionPane.showMessageDialog(null, "Ya hay un termino con ese exponente");
        } else {
            for (j = (int) vec[0] * 2; j > k + 1; j--) {
                vec[j] = vec[j - 2];
            }
            vec[k] = exp;
            vec[k + 1] = coef;
        }
    }

    //Metodo para insertar un termino en el polinomio
    public void insertarTerm(float coef, int exp) {
        int k=1,j;
        while (k < vec[0] * 2 + 1 && vec[k] > exp && vec[k + 1] != 0) {
            k += 2;
        }
        if (k < vec[0] * 2 + 1 && vec[k] == exp && vec[k + 1] != 0) {
            if (vec[k + 1] + coef != 0) {
                vec[k + 1] += coef;
            } else {
                for (j = k; j < vec[0] * 2 - 1; j += 2) {
                    vec[j] = vec[j + 2];
                    vec[j + 1] = vec[j + 3];
                }
                vec[0] = vec[0] - 1;
            }
        } else {
            if (vec[0] * 2 + 1 == n) {
                this.redimensionar();
            }
            for (j = (int) vec[0] * 2; j >= k; j--) {
                vec[j + 2] = vec[j];
            }
            vec[k] = exp;
            vec[k + 1] = coef;
        }
    }

    public void redimensionar()
    {
        n=n+2;
        float aux[] = new float[n];
        for (int k = 0; k < vec[0] * 2 + 1; k++) {
            aux[k] = vec[k];
        }
        //Delete vec aqui se libera la memoria utilizada por vec
        vec=aux;
    }

    //Metodo para ingresar los terminos del polinomio
    public void ingresarterminos(int canterm)
    {
        float coef;
        int k, exp;
        for (k = 1; k <= canterm; k++) {
            coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente"));
            exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente"));
            this.almacenarTerm(coef, exp);
        }
    }
}
