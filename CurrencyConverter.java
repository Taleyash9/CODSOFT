import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Currency Converter ===");

        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        try {
            double exchangeRate = getExchangeRate(base, target);
            if (exchangeRate == -1) {
                System.out.println("Could not fetch exchange rate.");
            } else {
                double result = amount * exchangeRate;
                System.out.printf("%.2f %s = %.2f %s\n", amount, base, result, target);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static double getExchangeRate(String base, String target) throws Exception {
        String url = "https://api.exchangerate-api.com/v4/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return -1;
        }

        String body = response.body();

        // Extract target currency rate manually from JSON string
        String search = "\"" + target + "\":";
        int index = body.indexOf(search);
        if (index == -1) {
            return -1;
        }

        int start = index + search.length();
        int end = body.indexOf(",", start);
        if (end == -1) {
            end = body.indexOf("}", start);
        }

        String rateStr = body.substring(start, end).trim();
        return Double.parseDouble(rateStr);
    }
}
