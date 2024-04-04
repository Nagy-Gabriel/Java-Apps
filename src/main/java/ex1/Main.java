package ex1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class  Main {
    public static void main(String[] args) {
        Map<Integer, Carte> colectie = new HashMap<>();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src\\main\\resources\\carti.json"));
            JSONObject jsonObiect = (JSONObject) obj;

            for (Object key : jsonObiect.keySet()) {
                String idString = (String) key;
                int id = Integer.parseInt(idString);
                JSONObject carteObiect = (JSONObject) jsonObiect.get(idString);
                String titlu = (String) carteObiect.get("titlul");
                String autorul = (String) carteObiect.get("autorul");
                int anul = ((Long) carteObiect.get("anul")).intValue();
                colectie.put(id, new Carte(titlu, autorul, anul));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Afisare colectie:");
        colectie.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("  ");
        System.out.println("O carte din colectie a fost stearsa, cu cheia 3");
        colectie.remove(3);
        System.out.println("  ");

        System.out.println("Colectia de carti dupa stergere este: ");
        colectie.forEach((key,value)->System.out.println(key+ ": "+value));

        System.out.println("S-a adaugat o noua carte in colectie");
        colectie.putIfAbsent(7, new Carte("Homo sapiens", "Yuval Noah Harari", 2014));

        System.out.println("  ");
        System.out.println("Afisarea dupa adaugarea noii carti: ");
        colectie.forEach((key,value)->System.out.println(key+ ": "+value));

        System.out.println("  ");
        System.out.println("S-a salvat in fisier");
        salvareInFisier(colectie);
        System.out.println("  ");

        Set<Carte> cartiYuvalHarari = colectie.values().stream()
                .filter(carte -> carte.getAutorul().equals("Yuval Noah Harari"))
                .collect(Collectors.toSet());
        System.out.println("\nCarti de Yuval Noah Harari:");
        cartiYuvalHarari.forEach(System.out::println);
        System.out.println("  ");


        System.out.println("\nCarti ordonate dupa titlu:");
        cartiYuvalHarari.stream()
                .sorted(Comparator.comparing(Carte::getTitlu))
                .forEach(System.out::println);

        Optional<Carte> ceaMaiVecheCarte = cartiYuvalHarari.stream()
                .min(Comparator.comparing(Carte::getAnul));
        System.out.println("\nCea mai veche carte:");
        ceaMaiVecheCarte.ifPresent(System.out::println);
    }

    private static void salvareInFisier(Map<Integer, Carte> colectie) {
        JSONObject jsonObiect = new JSONObject();
        colectie.forEach((key, value) -> {
            JSONObject carteObiect = new JSONObject();
            carteObiect.put("titlul", value.getTitlu());
            carteObiect.put("autorul", value.getAutorul());
            carteObiect.put("anul", value.getAnul());
            jsonObiect.put(Integer.toString(key), carteObiect);
        });

        try (FileWriter file = new FileWriter("src\\main\\resources\\carti_modificate.json")) {
            file.write(jsonObiect.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
