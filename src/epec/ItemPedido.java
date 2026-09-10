package epec;

public class ItemPedido {

    private Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public void mostrarItem() {
        System.out.printf(
                "%d x %s - $%.2f c/u - Subtotal: $%.2f%n",
                cantidad,
                producto.getNombre(),
                producto.getPrecio(),
                calcularSubtotal()
        );
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
