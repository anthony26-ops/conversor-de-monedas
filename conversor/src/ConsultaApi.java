import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaApi {
    public static void main(String[] args) {
        Scanner busqueda = new Scanner(System.in);


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        boolean continuarPrograma=true;

        while (continuarPrograma) {
        System.out.println("*************************************************************");
        System.out.println("Seleccion una opcion:");
        System.out.println("1 - Quetzal Guatemaltecto (GTQ) - Dolar Estadounidense (USD)");
        System.out.println("2 - Dolar Estadounidense (USD) - Quetzal Guatemalteco (GTQ)");
        System.out.println("3 - Quetzal Guatemaltecto (GTQ) - Peso Mexicano (MXN)");
        System.out.println("4 - Peso Mexicano (MXN) - Quetzal Guatemalteco (GTQ)");
        System.out.println("5 - Personalizado");
        System.out.println("6 - Salir");
        System.out.println("*************************************************************");

        var opcion = busqueda.nextInt();
        busqueda.nextLine();

        String base_code = "";
        String target_code = "";
        double amount = 0;

            if (opcion == 1) {
                base_code = "GTQ";
                target_code = "USD";
                System.out.println("Monto a convertir");
                amount = busqueda.nextDouble();
                busqueda.nextLine();
            } else if (opcion == 2) {
                base_code = "USD";
                target_code = "GTQ";
                System.out.println("Monto a convertir");
                amount = busqueda.nextDouble();
                busqueda.nextLine();
            } else if (opcion == 3) {
                base_code = "GTQ";
                target_code = "MXN";
                System.out.println("Monto a convertir");
                amount = busqueda.nextDouble();
                busqueda.nextLine();
            } else if (opcion == 4) {
                base_code = "MXN";
                target_code = "GTQ";
                System.out.println("Monto a convertir");
                amount = busqueda.nextDouble();
                busqueda.nextLine();
            } else if (opcion == 5) {
                System.out.println("Ingresa la moneda a convertir");
                base_code = busqueda.nextLine();

                System.out.println("Ingresa la moneda a consultar");
                target_code = busqueda.nextLine();

                System.out.println("Ingresa el monto a convertir");
                amount = busqueda.nextDouble();
                busqueda.nextLine();
            } else if (opcion==6) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opcion invalida");
                continue;
            }

            String direccion = "https://v6.exchangerate-api.com/v6/dca1c1e4eb8619ea860118f3/pair/" + base_code + "/" + target_code + "/" + amount;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = null;
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                Monedas miMoneda = gson.fromJson(json, Monedas.class);
                System.out.println(miMoneda);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            System.out.println("Deseas realizar otra transaccion?");
            System.out.println("1 - Si");
            System.out.println("2 - No");
            int respuesta = busqueda.nextInt();
            if (respuesta!=(1)){
                System.out.println("Gracias por la preferencia");
                continuarPrograma=false;
            }
        }
    }
}
