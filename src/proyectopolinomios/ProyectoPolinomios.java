
package proyectopolinomios;

import javax.swing.JOptionPane;
import java.awt.*;

public class ProyectoPolinomios {

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcionSeleccionada = getOpcionSeleccionada();
        //Menu principal
        switch(opcionSeleccionada){
            case 1:
                menuFormaUno();
                break;
            case 2:
                menuFormaDos();
                break;
            case 3:
                menuFormaTres();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta");
                break;
        }
    }

    private static void menuFormaUno() {
        Polvf1 primerPolinomio;
        int grado;

        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio"));
        primerPolinomio = new Polvf1(grado);
        primerPolinomio.insertarTermino();

        panelDeControl(primerPolinomio);
    }

    private static void menuFormaDos(){
        String menupolvf2 = "********Menú Polinomio Forma 2********\n 1. Mostar\n 2. Evaluar\n 3. Sumar\n 4. Multiplicar\n 5. Dividir\n 0. volver al menu principal";
        Polvf2 Polfor2;
        int opcion;

        Polfor2 = obtenerSegundoPolinomioF2();
        Polfor2.mostrar();

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menupolvf2));
            switch (opcion) {
                case 1:
                    Polfor2.mostrar();
                    break;
                case 2:
                    float variableX = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    float resul = Polfor2.evaluar(variableX);
                    JOptionPane.showMessageDialog(null, " al evaluar el polinomio con x = a (" + variableX + ") el resultado es " + resul);
                    break;
                case 3:
                    Polvf2 segundoPolinomioAsumar = obtenerSegundoPolinomioF2();
                    Polvf2 sumaPolinomios = Polfor2.sumar(segundoPolinomioAsumar);
                    segundoPolinomioAsumar.mostrar();
                    sumaPolinomios.mostrar();
                    break;
                case 4:
                    Polvf2 segundoPolinomioAMultiplicar = obtenerSegundoPolinomioF2();
                    Polvf2 multiplicacionPolinomio = Polfor2.multiplicar(segundoPolinomioAMultiplicar);
                    multiplicacionPolinomio.mostrar();
                    break;
                case 5:
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta!!!");
                    break;
            }
        } while (opcion != 0);

    }

    private static void menuFormaTres(){
        String menupolistas = "********Menú Polinomio Listas Ligadas********\n 1. Mostar\n 2. Evaluar\n 3. Sumar\n 4. Multiplicar\n 5. Dividir\n 0. Retornar al menú Principal";
        int opcion;

        Polvf3Listas Polista=new Polvf3Listas();
        Polista.ingresarTerminos();


        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menupolistas));
            switch (opcion) {
                case 1:
                    Polista.mostrar();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta!!!");
                    break;
            }
        } while (opcion != 0);
    }

    private static void panelDeControl(Polvf1 primerPolinomio) {
        int opcion;
        float variableX;
        Polvf1 segundoPolinomio = null;

        String menu = "***MENU***\n1-Mostrar\n2-Evaluar\n3-Sumar\n4-Multiplicar\n5-Dividir\n0-volver al menú principal";
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    primerPolinomio.mostrar();
                    if (segundoPolinomio != null) {
                        segundoPolinomio.mostrar();
                    }
                    break;
                case 2:
                    variableX = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    float resul = primerPolinomio.evaluar(variableX);
                    JOptionPane.showMessageDialog(null, " al evaluar el polinomio con x = a (" + variableX + ") el resultado es " + resul);
                    break;
                case 3:
                    Polvf1 segundoPolinomioAsumar = obtenerSegundoPolinomioF1(segundoPolinomio);
                    Polvf1 sumaPolinomios = primerPolinomio.sumar(segundoPolinomioAsumar);
                    segundoPolinomioAsumar.mostrar();
                    sumaPolinomios.mostrar();
                    break;
                case 4:
                    Polvf1 segundoPolinomioAMultiplicar = obtenerSegundoPolinomioF1(segundoPolinomio);
                    Polvf1 multiplicacionPolinomio = primerPolinomio.multiplicar(segundoPolinomioAMultiplicar);
                    multiplicacionPolinomio.mostrar();
                    break;
                case 5:
                    Polvf1 segundoPolinomioADividir = obtenerSegundoPolinomioF1(segundoPolinomio);
                    Polvf1 divisionPolinomio = primerPolinomio.dividir(segundoPolinomioADividir);
                    divisionPolinomio.mostrar();
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "opción no válida");
                    break;
            }
        } while (opcion != 0);
    }

    private static Polvf1 obtenerSegundoPolinomioF1(Polvf1 segundoPolinomio) {
        if (segundoPolinomio == null) {
            int grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del segundo polinomio"));
            segundoPolinomio = new Polvf1(grado);
            segundoPolinomio.insertarTermino();
        }
        return segundoPolinomio;
    }

    private static Polvf2 obtenerSegundoPolinomioF2() {
        int canterm = Integer.parseInt(JOptionPane.showInputDialog("cuantos termino tiene el polinommio:"));
        Polvf2 segundoPolinomio = new Polvf2(canterm);
        segundoPolinomio.ingresarterminos(canterm);
        return segundoPolinomio;
    }

    private static int getOpcionSeleccionada() {
        int opcionSeleccionada = -1;
        try {
            String menuPrincipal = "********Menú Principal********\n 1. Menú Polinomio Forma 1 \n 2. Menú Polinomio Forma 2\n 3. Menú Polinomio Forma 3 \n 4. Menú Polinomio combinado \n0. Salir";
            opcionSeleccionada = Integer.parseInt(JOptionPane.showInputDialog(menuPrincipal));
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Debe ingrasar una opción valida");
            getOpcionSeleccionada();
        }
        return opcionSeleccionada;
    }
}
