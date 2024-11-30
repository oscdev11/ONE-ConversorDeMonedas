import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private final String USD = "USD";
    private final String MXN = "MXN";
    private final String EUR = "EUR";
    private final String YUAN = "CNY";
    private Double cambio;

    public Double obtenerTasaConversion(String moneda1, String moneda2){
        URI url =  URI.create("https://v6.exchangerate-api.com/v6/c5cc5c037d36f5dd2bd4293e/pair/" + moneda1 + "/" + moneda2);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            TasaDeConversion tasa = new Gson().fromJson(response.body(), TasaDeConversion.class);
            return Double.valueOf(tasa.conversion_rate().toString());
        } catch (Exception e) {
            throw new RuntimeException("No se encontró información de conversión.");
        }
    }

    public void obtenerConversion(int opcion, Double valor){
        switch (opcion) {
            case 1: // DOLAR A MXN
                setCambio(obtenerTasaConversion(USD, MXN) * valor);
                System.out.println("\nEl valor " + valor + " [" + USD +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + MXN + "]");
                break;
            case 2: // MXN A DOLAR
                setCambio(obtenerTasaConversion(MXN, USD) * valor);
                System.out.println("\nEl valor " + valor + " [" + MXN +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + USD + "]");
                break;
            case 3: // EURO A MXN
                setCambio(obtenerTasaConversion(EUR, MXN) * valor);
                System.out.println("\nEl valor " + valor + " [" + EUR +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + MXN + "]");
                break;
            case 4: // MXN A EURO
                setCambio(obtenerTasaConversion(MXN, EUR) * valor);
                System.out.println("\nEl valor " + valor + " [" + MXN +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + EUR + "]");
                break;
            case 5: // YUAN A MXN
                setCambio(obtenerTasaConversion(YUAN, MXN) * valor);
                System.out.println("\nEl valor " + valor + " [" + YUAN +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + MXN + "]");
                break;
            case 6: // MXN A YUAN
                setCambio(obtenerTasaConversion(MXN, YUAN) * valor);
                System.out.println("\nEl valor " + valor + " [" + MXN +  "]" + " corresponde al valor final de =>>> " + getCambio() + " [" + YUAN + "]");
                break;
        }
    }

    public Double getCambio() {
        return cambio != null ? Math.round(cambio * 100.0) / 100.0 : null;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }
}
