/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static gui.MasterController.primaryStage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Histogram {
    
    float group[] = new float[4];
    String topic,x,y;
    String title;
    public Histogram()
    {
        topic ="Avalanche Effect";
        x="Algorithm";
        y="Percentage";
        title = "Avalanche Effect";
    }
    public Histogram(String topic,String x,String y,String title)
    {
        this.topic = topic;
        this.x = x;
        this.y =y;
        this.title = title;
    }
    public void display(float aes, float des, float rsa, float hybrid) {
        
        Stage primaryStage= new Stage(StageStyle.DECORATED);
        group[0] = aes;
        group[1] = des;
        group[2] = rsa;
        group[3] = hybrid;
        
        Label labelInfo = new Label();
        labelInfo.setText(topic);
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setStyle("-fx-font-size:26px");
        yAxis.setStyle("-fx-font-size:26px");
        final BarChart<String,Number> barChart = 
            new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(10);
        barChart.setBarGap(10);
        barChart.setMinHeight(600.0d);
        barChart.setBackground(Background.EMPTY);
        xAxis.setLabel(x);       
        yAxis.setLabel(y);
        
        XYChart.Series series1 = new XYChart.Series();
        
        series1.setName("Histogram");       
        
        series1.getData().add(new XYChart.Data("AES", group[0]));
        series1.getData().add(new XYChart.Data("ECC", group[1]));
        series1.getData().add(new XYChart.Data("RSA", group[2]));
        series1.getData().add(new XYChart.Data("PROPOSED\nMULTILEVEL", group[3]));
        
        
        barChart.setStyle("-fx-font-size:23px");
        barChart.getData().addAll(series1);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelInfo, barChart);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        Scene scene = new Scene(root, 800, 750);
        
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    } 
}