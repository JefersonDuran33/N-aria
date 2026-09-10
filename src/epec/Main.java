package epec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Producto> productos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    crearPedido();
                    break;
                case 3:
                    agregarProductoAPedido();
                    break;
                case 4:
                    mostrarPedidos();
                    break;
                case 5:
                    mostrarProductos();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== TIENDA ==========");
        System.out.println("1. Crear producto");
        System.out.println("2. Crear pedido");
        System.out.println("3. Agregar producto a un pedido");
        System.out.println("4. Ver pedidos");
        System.out.println("5. Ver productos");
        System.out.println("0. Salir");
    }

    private static void crearProducto() {
        int codigo = leerEntero("Código del producto: ");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        double precio = leerDouble("Precio: ");

        productos.add(new Producto(codigo, nombre, precio));

        System.out.println("Producto creado correctamente.");
    }

    private static void crearPedido() {
        int numero = leerEntero("Número del pedido: ");

        if (buscarPedido(numero) != null) {
            System.out.println("Ya existe un pedido con ese número.");
            return;
        }

        pedidos.add(new Pedido(numero));
        System.out.println("Pedido creado correctamente.");
    }

    private static void agregarProductoAPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Primero debe crear un pedido.");
            return;
        }

        if (productos.isEmpty()) {
            System.out.println("Primero debe crear un producto.");
            return;
        }

        int numeroPedido = leerEntero("Número del pedido: ");
        Pedido pedido = buscarPedido(numeroPedido);

        if (pedido == null) {
            System.out.println("No se encontró el pedido.");
            return;
        }

        mostrarProductos();
        int codigo = leerEntero("Código del producto: ");
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            System.out.println("No se encontró el producto.");
            return;
        }

        int cantidad = leerEntero("Cantidad: ");

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return;
        }

        pedido.agregarItem(producto, cantidad);

        System.out.println("Producto agregado al pedido correctamente.");
    }

    private static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido pedido : pedidos) {
            pedido.mostrarPedido();
        }
    }

    private static void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n===== PRODUCTOS =====");

        for (Producto producto : productos) {
            System.out.printf(
                    "Código: %d | %s | Precio: $%.2f%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            );
        }
    }

    private static Pedido buscarPedido(int numero) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    private static Producto buscarProducto(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}
