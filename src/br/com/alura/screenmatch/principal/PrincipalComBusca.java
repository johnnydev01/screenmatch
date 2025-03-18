package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.TItuloOmdb;
import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o filme que você deseja pesquisar: ");
        String busca = scanner.nextLine();

        String endereco = "http://www.omdbapi.com/?t=" + busca + "&apikey=f3778bb5";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        TItuloOmdb meuTituloOmdb = gson.fromJson(json, TItuloOmdb.class);
        Titulo meuTitulo = new Titulo(meuTituloOmdb);

        System.out.println("Filme já convertido");
        System.out.println(meuTitulo);
    }
}
