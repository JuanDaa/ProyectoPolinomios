
package proyectopolinomios;

import javax.swing.JOptionPane;

public class Polvf1
{
    private int numDatosNecesarios;
    private float vec[];

    //Método constructor
    public Polvf1(int grado) {
        numDatosNecesarios = grado + 2;
        vec = new float[numDatosNecesarios];
        vec[0] = grado;

    }
    //Método para mostrar
    public void mostrar() {
        int k;
        StringBuilder salida = new StringBuilder();
        salida.append("<html>");
        for(k=1;k<vec[0]+2;k++) {
            if(vec[k]!=0) {
                if(vec[k]>0&&k>1) {
                    salida.append("+").append(vec[k]).append("X<sup>").append((int) (getExponente(k))).append("</sup>");
                }
                else {
                    salida.append(vec[k]).append("X<sup>").append((int) (getExponente(k))).append("</sup>");
                }
            }
        }
        salida.append("</html>");
        JOptionPane.showMessageDialog(null, "Datos del polinomio\n" + salida);
    }

    public float evaluar(float x) {
        float resultado = 0;
        for (int contador = 1; contador < getTamanio(); contador++) {
            resultado += vec[contador] * (float) Math.pow(x, getExponente(contador));
        }
       return resultado;
    }

    public Polvf1 sumar(Polvf1 segundPolinomio) {
        int k = 1, J = 1, gm, posR, expA, expB;
        if (vec[0] > segundPolinomio.getDato(0)) {
            gm = (int) vec[0];
        } else {
            gm = (int) segundPolinomio.getDato(0);
        }

        Polvf1 polinomioSuma = new Polvf1(gm);

        while ((k < getTamanio()) && (J < segundPolinomio.getDato(0) + 2)) {
            expA = (int) (getExponente(k));
            expB = (int) (segundPolinomio.getDato(0) + 1 - J);

            if (expA == expB) {
                posR = (int) (polinomioSuma.getDato(0) + 1 - expA);
                polinomioSuma.setDato(vec[k] + segundPolinomio.getDato(J), posR);
                k++;
                J++;
            } else {
                if (expA > expB) {
                    posR = (int) (polinomioSuma.getDato(0) + 1 - expA);
                    polinomioSuma.setDato(vec[k], posR);
                    k++;
                } else {
                    posR = (int) (polinomioSuma.getDato(0) + 1 - expB);
                    polinomioSuma.setDato(segundPolinomio.getDato(J), posR);
                    J++;
                }
            }
        }
        polinomioSuma.ajustar();
        return polinomioSuma;
    }

    public Polvf1 multiplicar(Polvf1 segundoPolinomio) {
        float gradoPol = getExponente(1) + segundoPolinomio.getExponente(1);
        Polvf1 polinomioMultiplicacion = new Polvf1((int) gradoPol);
        for (int contadorPrimerPolinomio = 1; contadorPrimerPolinomio < getTamanio(); contadorPrimerPolinomio++) {
            float terminoPrimerPol = obtenerPorPosicion(contadorPrimerPolinomio);

            for (int contadorSegundoPolinomio = 1; contadorSegundoPolinomio < segundoPolinomio.getTamanio(); contadorSegundoPolinomio++) {
                float terminoSegundoPol = segundoPolinomio.obtenerPorPosicion(contadorSegundoPolinomio);
                float res = terminoPrimerPol * terminoSegundoPol;
                polinomioMultiplicacion.vec[(contadorPrimerPolinomio + contadorSegundoPolinomio) - 1] += res;
            }
        }
        return polinomioMultiplicacion;
    }

    public Polvf1 dividir(Polvf1 segundoPolinomio) {
        return null;
    }

    public void insertarTermino() {
        int exp, pos;
        float coef;
        String rpa;
        rpa = JOptionPane.showInputDialog("¿Desea ingresar término? s/n");
        while (rpa.toLowerCase().equals("s")) {
            coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente del término:"));
            exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente del término:"));
            if (exp < 0) {
                JOptionPane.showMessageDialog(null, "El exponente no es válido");
            } else {
                if (exp <= vec[0]) {
                    pos = (int) getExponente(exp);
                    vec[pos] = vec[pos] + coef;
                } else if (exp > vec[0]) {
                    String redimensionar = JOptionPane.showInputDialog("Desea redimensionar el polinomio  (S/N)");
                    if (redimensionar.toLowerCase().equals("s")) {
                        this.redimencionar(exp);
                        vec[0] = exp;
                        vec[1] = coef;
                    } else {
                        JOptionPane.showMessageDialog(null, "El exponente no corresponde al polinomio");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El exponente no corresponde al polinomio");
                }
            }
            rpa=JOptionPane.showInputDialog("Desea ingresar un término? s/n");
        }
    }

    public void redimencionar(int exponente) {
        int k, pos = exponente +1;
        float aux[] = new float[exponente + 2];
        for (k = (int) (vec[0] + 1); k > 0; k--) {
            aux[pos] = vec[k];
            pos--;
        }
//      delete liberarMemoria(vec);
        vec = aux;
    }

    public void ajustar() {
        int k, J = 1, cont = 0;
        while (J < getTamanio() && vec[J] == 0) {
            cont++;
            J++;
        }
        for (k = J; k > getTamanio(); k++) {
            vec[k - cont] = vec[k];
        }
        vec[0] = vec[0] - cont;
    }

    public float obtenerPorPosicion(int posicion) {
        return vec[posicion];
    }

    public float getTamanio() {
        return numDatosNecesarios;
    }

    public float getExponente(int position) {
        return vec[0] + 1 - position;
    }

    public float getDato(int pos) {
        return vec[pos];
    }

    public void setDato(float dato, int pos) {
        vec[pos] = dato;
    }
}
