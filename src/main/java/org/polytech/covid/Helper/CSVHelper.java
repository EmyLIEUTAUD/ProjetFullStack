package org.polytech.covid.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.polytech.covid.Entity.Centre;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    /***
     * Ancien fichier permettant de parser le fichier csv importé pour ajouter les
     * centres dans la base de données
     ***/

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Centre> csvToCentres(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase()
                                .withTrim());) {

            List<Centre> centres = new ArrayList<Centre>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Centre centre = new Centre(
                        csvRecord.get("nom"),
                        csvRecord.get("com_nom"),
                        csvRecord.get("adr_num"),
                        csvRecord.get("adr_voie"),
                        Integer.parseInt(csvRecord.get("com_cp")),
                        csvRecord.get("rdv_lundi"),
                        csvRecord.get("rdv_mardi"),
                        csvRecord.get("rdv_mercredi"),
                        csvRecord.get("rdv_jeudi"),
                        csvRecord.get("rdv_vendredi"),
                        csvRecord.get("rdv_samedi"),
                        csvRecord.get("rdv_dimanche"));

                centres.add(centre);
            }

            return centres;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}