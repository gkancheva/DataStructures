package labs.l11_combining_data_structures.exercises.shopping_center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static ProductCollection collection = new ProductCollectionImpl();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < num; i++) {
            String input = br.readLine();
            String command = input.substring(0, input.indexOf(" "));
            String param = input.substring(input.indexOf(" ") + 1);
            switch (command) {
                case "AddProduct":
                    result.append(executeAddProduct(param)).append('\n');
                    break;
                case "DeleteProducts":
                    result.append(executeDelete(param)).append("\n");
                    break;
                case "FindProductsByName":
                    result.append(executeFindProductsByName(param));
                    break;
                case "FindProductsByProducer":
                    result.append(executeFindProductsByProducer(param));
                    break;
                case "FindProductsByPriceRange":
                    result.append(executeFindProductsByPriceRange(param));
                    break;
                default:
                    break;
            }
        }
        System.out.println(result.toString());
    }

    private static String executeFindProductsByPriceRange(String input) {
        double startPrice = Double.parseDouble(input.split(";")[0]);
        double endPrice = Double.parseDouble(input.split(";")[1]);
        List<Product> products = collection.findProductsByPriceRange(new BigDecimal(startPrice), new BigDecimal(endPrice));
        StringBuilder sb = new StringBuilder();
        if(products.size() == 0) {
            sb.append("No products found").append("\n");
        } else {
            for (Product product : products) {
                sb.append(product).append("\n");
            }
        }
        return sb.toString();
    }

    private static String executeFindProductsByProducer(String producer) {
        List<Product> products = collection.findProductsByProducer(producer);
        StringBuilder sb = new StringBuilder();
        if(products.size() == 0) {
            sb.append("No products found").append("\n");
        } else {
            for (Product product : products) {
                sb.append(product).append("\n");
            }
        }
        return sb.toString();
    }

    private static String executeFindProductsByName(String name) {
        List<Product> products = collection.findProductsByName(name);
        StringBuilder sb = new StringBuilder();
        if(products.size() == 0) {
            sb.append("No products found").append("\n");
        } else {
            for (Product product : products) {
                sb.append(product).append("\n");
            }
        }
        return sb.toString();
    }

    private static String executeDelete(String input) {
        String[] params = input.split(";");
        int result = 0;
        if(params.length == 1) {
            result = collection.deleteProducts(params[0]);
        } else {
            result = collection.deleteProducts(params[0], params[1]);
        }
        return result == 0 ? "No products found" : result + " products deleted";
    }

    private static String executeAddProduct(String input) {
        String[] params = input.split(";");
        String name = params[0];
        double price = Double.parseDouble(params[1]);
        String producer = params[2];
        collection.addProduct(name, new BigDecimal(price), producer);
        return "labs.l11_combining_data_structures.exercises.shopping_center.Product added";
    }
}