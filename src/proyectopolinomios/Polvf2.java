
package proyectopolinomios;

import javax.swing.*;


public class Polvf2 
{
    private int numeroTerminos;
    private float vector[];

    Polvf2(int numeroTerminos) {
        this.numeroTerminos = numeroTerminos * 2 + 1;
        vector = new float[this.numeroTerminos];
        vector[0] = numeroTerminos;
    }

    public float getDato(int posicion){
        return vector[posicion];
    }

    public void setDato(float dato, int posicion) {
        vector[posicion] = dato;
    }

    public int getTamanio() {
        return numeroTerminos;
    }

    public Polvf2 sumar(Polvf2 segundPolinomio) {
        int k = 1, J = 1, expA, expB, coeA, coeB;

        Polvf2 polinomioSuma = new Polvf2(numeroTerminos);

        while ((k < getTamanio()) && (J < segundPolinomio.getTamanio())) {
            expA = (int) getDato(k);
            expB = (int) segundPolinomio.getDato(J);
            coeA = (int) getDato(k + 1);
            coeB = (int) segundPolinomio.getDato(J + 1);

            if (expA == expB) {
                if ((coeA + coeB) != 0) {
                    polinomioSuma.insertarTerm((coeA + coeB), expA);
                }
                k += 2;
                J += 2;
            } else {
                if (expA > expB) {
                    polinomioSuma.insertarTerm(coeA , expA);
                    k += 2;
                } else {
                    polinomioSuma.insertarTerm(coeB , expB);
                    J += 2;
                }
            }
        }
        while (k <= getTamanio()) {
            polinomioSuma.insertarTerm(getDato(k + 1), (int) getDato(k));
            k += 2;
        }
        while (J <= segundPolinomio.getTamanio()) {
            polinomioSuma.insertarTerm(segundPolinomio.getDato(J + 1), (int) getDato(J));
            J += 2;
        }
        return polinomioSuma;
    }


    //Metodo para mostrar
    public void mostrar() {
        StringBuilder salida = new StringBuilder("<html>");
        for (int k = 1; k < vector[0] * 2 + 1; k += 2) {
            if (vector[k + 1] > 0 && k > 2) {
                salida.append("+");
            }
            salida.append(vector[k + 1]).append("X<sup>").append(String.valueOf((int) vector[k])).append("</sup>");
        }
        salida.append("</html>");
        JOptionPane.showMessageDialog(null, "Terminos del polinomio\n" + salida);
    }

    public float evaluar(float x) {
        float resultado = 0;
        for (int contador = 1; contador < getTamanio(); contador +=2) {
            resultado += vector[contador + 1] * (float) Math.pow(x, vector[contador]);
        }
        return resultado;
    }

    //Metodo para almacenar un termino en el polinomio
    public void almacenarTerm(float coef, int exp) {
        int k = 1, j;
        while (k < vector[0] * 2 + 1 && vector[k] > exp && vector[k + 1] != 0) {
            k += 2;
        }
        if (k < vector[0] * 2 + 1 && vector[k] == exp && vector[k + 1] != 0) {
            JOptionPane.showMessageDialog(null, "Ya hay un termino con ese exponente");
        } else {
            for (j = (int) vector[0] * 2; j > k + 1; j--) {
                vector[j] = vector[j - 2];
            }
            vector[k] = exp;
            vector[k + 1] = coef;
        }
    }

    //Metodo para insertar un termino en el polinomio
    public void insertarTerm(float coef, int exp) {
        int k=1,j;
        while (k < vector[0] * 2 + 1 && vector[k] > exp && vector[k + 1] != 0) {
            k += 2;
        }
        if (k < vector[0] * 2 + 1 && vector[k] == exp && vector[k + 1] != 0) {
            if (vector[k + 1] + coef != 0) {
                vector[k + 1] += coef;
            } else {
                for (j = k; j < vector[0] * 2 - 1; j += 2) {
                    vector[j] = vector[j + 2];
                    vector[j + 1] = vector[j + 3];
                }
                vector[0] = vector[0] - 1;
            }
        } else {
            if (vector[0] * 2 + 1 == numeroTerminos) {
                this.redimensionar();
            }
            for (j = (int) vector[0] * 2; j >= k; j--) {
                vector[j + 2] = vector[j];
            }
            vector[k] = exp;
            vector[k + 1] = coef;
        }
    }

    public void redimensionar()
    {
        numeroTerminos = numeroTerminos +2;
        float aux[] = new float[numeroTerminos];
        for (int k = 0; k < vector[0] * 2 + 1; k++) {
            aux[k] = vector[k];
        }
        //Delete vec aqui se libera la memoria utilizada por vec
        vector =aux;
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

    public Polvf2 multiplicar(Polvf2 segundoPolinomioAMultiplicar) {
        int k, j, expA, expB, expR;
        float coefR;
        Polvf2 polinomioMultiplicacion = new Polvf2((int) (getDato(1) + segundoPolinomioAMultiplicar.getDato(1) + 1));
        polinomioMultiplicacion.setDato(getDato(1) + segundoPolinomioAMultiplicar.getDato(1), 1);
        for (j = 2; j <= segundoPolinomioAMultiplicar.getTamanio(); j += 2) {
            expB = (int) segundoPolinomioAMultiplicar.getDato(j);
            for (k = 2; k <= getTamanio(); k += 2) {
                expA = (int) getDato(k);
                expR = expA + expB;
                coefR = getDato(k + 1) * segundoPolinomioAMultiplicar.getDato(j + 1);
                polinomioMultiplicacion.insertarTerm(coefR, expR);
            }
        }
        return polinomioMultiplicacion;
    }
}
