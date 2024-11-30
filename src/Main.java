import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ApiClient api = new ApiClient();

        int opcion = 0;
        double valor = 0.0;
        while (opcion < 7) {
            System.out.println("""
                    
                    *********************************************
                    Sea bienvenido(a) al Conversor de Moneda =]
                    1) Dólar =>> Peso mexicano
                    2) Peso mexicano =>> Dólar
                    3) Euro =>> Peso mexicano
                    4) Peso mexicano =>> Euro
                    5) Yuan =>> Peso mexicano
                    6) Peso mexicano =>> Yuan
                    7) Salir
                    *********************************************
                    
                    Elija una opción válida""");
            opcion = scanner.nextInt();
            if (opcion > 6 || opcion < 1) {
                System.out.println("\nGracias por usar el Conversor de Monedas!");
                return;
            }
            System.out.println("Ingrese el valor que deseas convertir: ");
            valor = scanner.nextDouble();
            api.obtenerConversion(opcion, valor);
        }

    }
}