package ex2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Set<InstrumentMuzical> instrumente = new HashSet<>();

        // Cerința 1: Adăugarea a 3 chitări și 3 seturi de tobe
        instrumente.add(creareChitara("Gibson", 2000, TipChitara.ELECTRICA, 6));
        instrumente.add(creareChitara("Fender", 1500, TipChitara.ACUSTICA, 12));
        instrumente.add(creareChitara("Yamaha", 1000, TipChitara.CLASICA, 6));
        instrumente.add(creareSetTobe("Pearl", 2500, TipTobe.ACUSTICE, 5, 3));
        instrumente.add(creareSetTobe("Roland", 4000, TipTobe.ELECTRONICE, 3, 2));
        instrumente.add(creareSetTobe("Mapex", 3000, TipTobe.ACUSTICE, 4, 4));

        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        System.out.println("Savlare in fisier cu succes!");
        salvareInstrumente(instrumente, "instrumente.json");

        System.out.println(" ");
        System.out.println("Citire din fisier...");
        Set<InstrumentMuzical> instrumenteCitite = incarcareInstrumente("instrumente.json");


        System.out.println("Verificare Si Eliminare Duplicate (instrumente);\n");
        verificareSiEliminareDuplicate(instrumente);


        stergereInstrumenteCuPretMaiMare(instrumente, 3000);


        System.out.println("Chitare:");
        afisareChitare(instrumenteCitite);
        System.out.println("Tobe:");
        afisareTobe(instrumenteCitite);

        afisareChitaraCuCeleMaiMulteCorzi(instrumenteCitite);

        afisareTobeAcusticeOrdonate(instrumenteCitite);

    }

    private static Chitara creareChitara(String producator, double pret, TipChitara tipChitara, int nrCorzi) {
        Chitara chitara = new Chitara("Gibson", 2000, TipChitara.ELECTRICA, 6);
        chitara.producator = producator;
        chitara.pret = pret;
        chitara.tipChitara = tipChitara;
        chitara.nrCorzi = nrCorzi;
        return chitara;
    }

    private static SetTobe creareSetTobe(String producator, double pret, TipTobe tipTobe, int nrTobe, int nrCinele) {
        SetTobe setTobe = new SetTobe("Pearl", 2500, TipTobe.ACUSTICE, 5, 3);
        setTobe.producator = producator;
        setTobe.pret = pret;
        setTobe.tipTobe = tipTobe;
        setTobe.nrTobe = nrTobe;
        setTobe.nrCinele = nrCinele;
        return setTobe;
    }

    private static void salvareInstrumente(Set<InstrumentMuzical> instrumente, String numeFisier) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(new File(numeFisier), instrumente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Set<InstrumentMuzical> incarcareInstrumente(String numeFisier) {
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            instrumente = new HashSet<>(Arrays.asList(mapper.readValue(new File(numeFisier), InstrumentMuzical[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instrumente;
    }

    private static void verificareSiEliminareDuplicate(Set<InstrumentMuzical> instrumente) {
        InstrumentMuzical chitaraDuplicate = creareChitara("Gibson", 2000, TipChitara.ELECTRICA, 6);
        if (!instrumente.add(chitaraDuplicate)) {
            System.out.println("Instrumentul duplicat nu poate fi adăugat.");
        }
    }
    private static void stergereInstrumenteCuPretMaiMare(Set<InstrumentMuzical> instrumente, double pret) {
        instrumente.removeIf(instrument -> instrument.pret > pret);
    }

    private static void afisareChitare(Set<InstrumentMuzical> instrumente) {
        instrumente.stream()
                .filter(instrument -> instrument instanceof Chitara)
                .forEach(InstrumentMuzical::afisareDetalii);
    }

    private static void afisareTobe(Set<InstrumentMuzical> instrumente) {
        instrumente.stream()
                .filter(instrument -> instrument instanceof SetTobe)
                .forEach(InstrumentMuzical::afisareDetalii);
    }

    private static void afisareChitaraCuCeleMaiMulteCorzi(Set<InstrumentMuzical> instrumente) {
        Optional<InstrumentMuzical> chitaraCuCeleMaiMulteCorzi = instrumente.stream()
                .filter(instrument -> instrument instanceof Chitara)
                .max(Comparator.comparingInt(instrument -> ((Chitara) instrument).nrCorzi));
        chitaraCuCeleMaiMulteCorzi.ifPresent(chitara -> {
            System.out.print("Chitara cu cele mai multe corzi: ");
            chitara.afisareDetalii();
        });
    }

    private static void afisareTobeAcusticeOrdonate(Set<InstrumentMuzical> instrumente) {
        instrumente.stream()
                .filter(instrument -> instrument instanceof SetTobe && ((SetTobe) instrument).tipTobe == TipTobe.ACUSTICE)
                .sorted(Comparator.comparingInt(instrument -> ((SetTobe) instrument).nrTobe))
                .forEach(InstrumentMuzical::afisareDetalii);
    }

}