package org.polytech.covid.Helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

import org.polytech.covid.Entity.Centre;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    public static String TYPE = "text/csv";
    /*
     * static String[] HEADERs = { "gid", "nom", "arrete_pref_numero", "xy_precis",
     * "id_adr", "adr_num", "adr_voie",
     * "comp_cp", "com_insee", "com_nom", "long_coor1", "long_coor1",
     * "structure_siren", "structure_type",
     * "structure_rais", "structure_num", "structure_voie", "structure_cp",
     * "structure_insee", "structure_com",
     * "_userid_creation", "_userid_modification", "_edit_datemaj",
     * "lieu_accessibilite", "rdv_lundi", "rdv_mardi",
     * "rdv_mercredi", "rdv_jeudi", "rdv_vendredi", "rdv_samedi", "rdv_dimanche",
     * "rdv", "date_fermeture",
     * "date_ouverture", "rdv_site_web", "rdv_tel", "rdv_tel2", "rdv_modalites",
     * "centre_svi_repondeur",
     * "centre_fermeture", "reserve_professionels_sante", "centre_type" };
     */

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