public class Nodo {
    public Nodo anterior;
    public Nodo siguiente;
    SoldadoVirtual soldadoVirtual;


    public Nodo(SoldadoVirtual soldadoVirtual) {
        this.soldadoVirtual = soldadoVirtual;
        this.anterior = null;
        this.siguiente = null;
    }
}
