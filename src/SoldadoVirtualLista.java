import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.util.ArrayList;

public class SoldadoVirtualLista {
    private Nodo cabeza;
    private Nodo cola;

    public SoldadoVirtualLista() {
        this.cabeza = null;
        this.cola = null;
    }

    // Método para agregar un soldado ordenado por ID
    public boolean agregarSoldado(SoldadoVirtual soldadoVirtual) {
        Nodo nuevoNodo = new Nodo(soldadoVirtual);
        int idNuevo = soldadoVirtual.getId();

        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
            return true;
        }

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.soldadoVirtual.getId() == idNuevo) {
                JOptionPane.showMessageDialog(null, "El ID del soldado ya está registrado.");
                return false;
            }
            if (actual.soldadoVirtual.getId() > idNuevo) {
                nuevoNodo.siguiente = actual;
                nuevoNodo.anterior = actual.anterior;
                if (actual.anterior == null) {
                    cabeza = nuevoNodo;
                } else {
                    actual.anterior.siguiente = nuevoNodo;
                }
                actual.anterior = nuevoNodo;
                return true;
            }
            actual = actual.siguiente;
        }

        cola.siguiente = nuevoNodo;
        nuevoNodo.anterior = cola;
        cola = nuevoNodo;
        return true;
    }

    public ArrayList<SoldadoVirtual> toArrayList() {
        ArrayList<SoldadoVirtual> lista = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            lista.add(actual.soldadoVirtual);
            actual = actual.siguiente;
        }
        return lista;
    }

    public SoldadoVirtual buscarSoldadoPorId(int id) {
        ArrayList<SoldadoVirtual> lista = toArrayList();
        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            SoldadoVirtual sv = lista.get(medio);

            if (sv.getId() == id) {
                return sv;
            } else if (sv.getId() < id) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    public ArrayList<SoldadoVirtual> filtrarPorTecnologia(String tecnologia) {
        ArrayList<SoldadoVirtual> filtrados = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.soldadoVirtual.getTecnologiaEspecial().equals(tecnologia)) {
                filtrados.add(actual.soldadoVirtual);
            }
            actual = actual.siguiente;
        }
        return filtrados;
    }

    public void ordenarPorNivelSeleccion(ArrayList<SoldadoVirtual> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getNivelVirtualidad() > lista.get(maxIndex).getNivelVirtualidad()) {
                    maxIndex = j;
                }
            }
            SoldadoVirtual temp = lista.get(maxIndex);
            lista.set(maxIndex, lista.get(i));
            lista.set(i, temp);
        }
    }

    public int contarPorDimension(String dimension) {
        return contarRecursivo(cabeza, dimension);
    }

    private int contarRecursivo(Nodo nodo, String dimension) {
        if (nodo == null) {
            return 0;
        }
        int count = nodo.soldadoVirtual.getDimensionOperativa().equals(dimension) ? 1 : 0;
        return count + contarRecursivo(nodo.siguiente, dimension);
    }
}

