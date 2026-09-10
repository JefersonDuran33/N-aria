package epec;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private List<ItemPedido> items;

    public Pedido(int numero) {
        this.numero = numero;
        this.items = new ArrayList<>();
    }

    public void agregarItem(Producto producto, int cantidad) {
        ItemPedido item = new ItemPedido(producto, cantidad);
        items.add(item);
    }

    public void mostrarPedido() {
        System.out.println("\n===== PEDIDO #" + numero + " =====");

        if (items.isEmpty()) {
            System.out.println("El pedido no tiene productos.");
            return;
        }

        double total = 0;

        for (ItemPedido item : items) {
            item.mostrarItem();
            total += item.calcularSubtotal();
        }

        System.out.printf("TOTAL: $%.2f%n", total);
    }

    public int getNumero() {
        return numero;
    }

    public List<ItemPedido> getItems() {
        return items;
    }
}
