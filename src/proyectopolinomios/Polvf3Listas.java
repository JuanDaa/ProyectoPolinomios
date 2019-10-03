package proyectopolinomios;

import javax.swing.*;

public class Polvf3Listas {
  private Nodo cab;

    public Polvf3Listas() {
        cab = null;
    }
  //Metodo para mostrar los terminos del polinomio
  public void mostrar() {
      Nodo q=cab;
      StringBuilder salida = new StringBuilder("<html>");
      if(cab==null) {
        JOptionPane.showMessageDialog(null,"la lista del polinomio esta vacia");  
      }
      else {
          while(q!=null) {
            if(q.getCoef()>0&&q!=cab) {
                salida.append("+");
            }
              salida.append(q.getCoef()).append("X<sup>").append(String.valueOf((int) q.getExp())).append("</sup>");
              q = q.getLiga();
          }
          salida.append("</html>");
          JOptionPane.showMessageDialog(null, "Terminos del polinomio\n" + salida);
      }
  }
  //Metodo para insertar un termino
  public void insertarterm(float coef,int exp) {
      Nodo q=cab,ant=null,x;
      while(q!=null&&q.getExp()>exp) {
          ant=q;
          q=q.getLiga();
      }
      if(q!=null&&q.getExp()==exp) {
          if(q.getCoef()+coef!=0) {
              q.setCoef(q.getCoef()+coef);
          }
          else {
              if(q==cab)
              {
                  cab=cab.getLiga();
              }
              else
              {
                  ant.setLiga(q.getLiga());
              }
              //delete(q) aca se libera el nodo
          }
      }
      else {
         x=new Nodo(coef, exp);
         x.setLiga(q);
         if(q==cab)
         {
             cab=x;
         }
         else
         {
             ant.setLiga(x);
         }
     }
  }
  //Metodo para almacenar un termino
  public void almacenarterm(float coef,int exp)
  {
      Nodo q=cab,ant=null,x;
      while(q!=null&&q.getExp()>exp)
      {
          ant=q;
          q=q.getLiga();
      }
      if (q != null && q.getExp() == exp) {
          JOptionPane.showMessageDialog(null, "Ya hay un termino con ese exponente");
      } else {
          x = new Nodo(coef, exp);
          x.setLiga(q);
          if (q == cab) {
              cab = x;
          } else {
              ant.setLiga(x);
          }
      }
  }

  public void ingresarTerminos() 
  {
     int exp, pos;
     float coef;
     String rpa;
      rpa = JOptionPane.showInputDialog("¿Desea ingresar un término? (S/N)");
      while (rpa.toLowerCase().equals("s")) {
          coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente del término:"));
          exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente del término:"));
          this.almacenarterm(coef, exp);
          rpa = JOptionPane.showInputDialog("¿Desea ingresar un término? (S/N)");
      }
    }
}
