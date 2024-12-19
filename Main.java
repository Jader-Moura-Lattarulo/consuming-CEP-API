import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ZipCodeQuery zipCodeQuery = new ZipCodeQuery();

        System.out.println("Digite um número de CEP para consulta: ");
        var cep = scanner.nextLine();

        try {
            Address newAddress = zipCodeQuery.searchAddress(cep);
            System.out.println(newAddress);
            FileGenerator generator = new FileGenerator();
            generator.SaveJson(newAddress);
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando a aplicação");
        }
    }
}
