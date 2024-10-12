package com.example.applicationtuto;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloController {

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private Button uploadButton;

    // Méthode appelée après que les composants FXML sont initialisés
    @FXML
    public void initialize() {
        // Configurer l'action du bouton pour charger un fichier CSV
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                plotCsvData(file);
            }
        });
    }

    private void plotCsvData(File file) {
        lineChart.getData().clear(); // Effacer les anciennes données du graphe
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Données CSV");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Ligne lue : " + line);  // Debug
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);

                // Créer un point de données
                XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(x, y);
                series.getData().add(dataPoint);

                // Installer un Tooltip pour afficher les coordonnées

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format dans le fichier CSV.");
        }

        lineChart.getData().add(series);
    }
}
