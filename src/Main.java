import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //se não funcionar verificar a api_key, pode ser necessário atualizar a api.
        var url = "https://api.nasa.gov/planetary/apod?api_key=r4uL7gRraGZZwR0cb8PDEsww8bkGT1TERSOvOaAr&start_date=2022-06-12&end_date=2022-07-01";
        var endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        System.out.println(body);

        var p = new JsonParser();
        var listItens = p.parse(body);
        //List<Map<String,String>> listItens = pRes;

        for(Map<String,String> item : listItens){
            String tipoArquivo = item.get("media_type");
            if(Objects.equals(tipoArquivo, "image")) {
                String urlImg = item.get("url");
                String nomeArquivo = item.get("title");
                InputStream inputStream = new URL(urlImg).openStream();

                var geradora = new GeradorDeFigurinhas();
                geradora.cria(inputStream, nomeArquivo);
                System.out.println(item.get("title"));
                System.out.println(item.get("url"));
            }
        }
    }
}











