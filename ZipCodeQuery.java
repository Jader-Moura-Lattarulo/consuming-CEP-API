import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ZipCodeQuery {
    public Address searchAddress(String cep) {

        URI address = URI.create("https://viacep.com.br/ws/"+ cep +"/json/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(address)
                .build();

        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Address.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("CEP não encontrado", e);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui encontrar o endereço", e);
        }
    }
}
