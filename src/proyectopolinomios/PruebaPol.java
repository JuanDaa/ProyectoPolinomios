package proyectopolinomios;

import javax.swing.*;

public class PruebaPol {
        private long coeficientes[];

        public PruebaPol()
        {
        }

        public PruebaPol(int grado)
        {
            if ( grado <= 0 ) grado = 0;
            coeficientes = new long[grado + 1];
        }

        public int grado()
        {
            return coeficientes.length - 1;
        }

        public long coeficiente(int i)
        {
            return i < coeficientes.length ? coeficientes[i] : 0;
        }

        public void leer()
        {
            int n = Integer.parseInt(JOptionPane.showInputDialog(("Grado (n): ")));
            coeficientes = new long[n + 1];
            for (int i = coeficientes.length - 1; i >= 0; --i) {
                coeficientes[i] =Integer.parseInt(JOptionPane.showInputDialog("Coeficiente de x^" + i + ": "));
            }
        }

        public void imprimir()
        {
            for ( int i = grado(); i >= 0; --i )
            {
                if ( coeficientes[i] != 0 )
                {
                    if ( i != grado() ) System.out.print(" + ");
                    if ( coeficientes[i] != 1 || i == 0 ) System.out.print(coeficientes[i]);
                    if ( i > 0 ) System.out.print("x");
                    if ( i > 1 ) System.out.print("^" + i);
                }
            }
        }

        PruebaPol sumar(PruebaPol otro)
        {
            int grado = Math.max( grado(), otro.grado() );
            PruebaPol resultado = new PruebaPol(grado);
            for ( int i = 0; i <= grado; ++i )
                resultado.coeficientes[i] = coeficiente(i) + otro.coeficiente(i);

            return resultado;
        }

        PruebaPol restar(PruebaPol otro)
        {
            int grado = Math.max( grado(), otro.grado() );
            PruebaPol resultado = new PruebaPol(grado);
            for ( int i = 0; i <= grado; ++i )
                resultado.coeficientes[i] = coeficiente(i) - otro.coeficiente(i);

            return resultado;
        }

        PruebaPol multiplicar(PruebaPol otro)
        {
            int grado = grado() + otro.grado();
            PruebaPol resultado = new PruebaPol(grado);
            for ( int i = 0; i <= grado(); ++i )
                for ( int j = 0; j <= otro.grado(); ++j )
                    resultado.coeficientes[i + j] += coeficiente(i) * otro.coeficiente(j);

            return resultado;
        }

        public static void main(String args[])
        {
            System.out.println("PruebaPol 1");
            PruebaPol PruebaPol1 = new PruebaPol();
            PruebaPol1.leer();
            PruebaPol1.imprimir();

            System.out.println("\n\nPruebaPol 2");
            PruebaPol PruebaPol2 = new PruebaPol();
            PruebaPol2.leer();
            PruebaPol2.imprimir();

            System.out.println();
            imprimirOperacion( PruebaPol1, PruebaPol2, "+", PruebaPol1.sumar(PruebaPol2) );
            imprimirOperacion( PruebaPol1, PruebaPol2, "-", PruebaPol1.restar(PruebaPol2) );
            imprimirOperacion( PruebaPol2, PruebaPol1, "-", PruebaPol2.restar(PruebaPol1) );
            imprimirOperacion( PruebaPol1, PruebaPol2, "*", PruebaPol1.multiplicar(PruebaPol2) );
        }

        public static void imprimirOperacion(PruebaPol a, PruebaPol b, String op, PruebaPol resultado)
        {
            System.out.print("\n(");
            a.imprimir();
            System.out.print(") " + op + " (");
            b.imprimir();
            System.out.print(") = ");
            resultado.imprimir();
            System.out.println();
        }
}
