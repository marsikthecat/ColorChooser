package com.example.colorchooser;

import java.util.Objects;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * App that generates random color with clipboard feature.
 * 219 lines + 37 lines of css.
 */

public class Main extends Application {

  private final RgbColor color = new RgbColor(0, 0, 0);

  @Override
  public void start(Stage primaryStage) {
    Button createBtn = new Button("Create random Color");
    createBtn.getStyleClass().addAll("button");

    MenuItem item1 = new MenuItem("RGB");
    item1.setOnAction(e -> toClickBoard(color.getRgb()));
    MenuItem item2 = new MenuItem("HEX");
    item2.setOnAction(e -> toClickBoard(color.getHex()));
    MenuItem item3 = new MenuItem("CMY");
    item3.setOnAction(e -> toClickBoard(color.getCmy()));

    MenuButton copyBtn = new MenuButton("Copy RGB Value", null, item1, item2, item3);
    copyBtn.getStyleClass().add("menu-button");

    ObservableList<Integer> numbers = FXCollections.observableArrayList();
    for (int i = 0; i <= 256; i++) {
      numbers.add(i);
    }

    Slider slider1 = new Slider(0, 256, color.getR());
    Slider slider2 = new Slider(0, 256, color.getG());
    Slider slider3 = new Slider(0, 256, color.getB());
    setupSlider(slider1, slider2, slider3);

    Text sliderLabel1 = new Text("R: " + (int) slider1.getValue());
    Text sliderLabel2 = new Text("G: " + (int) slider2.getValue());
    Text sliderLabel3 = new Text("B: " + (int) slider3.getValue());
    sliderLabel1.getStyleClass().add("slider-label");
    sliderLabel2.getStyleClass().add("slider-label");
    sliderLabel3.getStyleClass().add("slider-label");

    VBox labelBox = new VBox();
    labelBox.getChildren().addAll(sliderLabel1, sliderLabel2, sliderLabel3);
    labelBox.getStyleClass().add("label-box");

    VBox sliderBox = new VBox();
    sliderBox.getChildren().addAll(slider1, slider2, slider3);
    sliderBox.setStyle(color.getStyleDefinition());
    sliderBox.getStyleClass().add("slider-box");


    VBox content = new VBox();
    content.setSpacing(10);
    content.setAlignment(Pos.CENTER);

    slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setR(newValue.intValue());
      sliderBox.setStyle(color.getStyleDefinition());
      sliderLabel1.setText("R: " + newValue.intValue());
    });
    slider2.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setG(newValue.intValue());
      sliderBox.setStyle(color.getStyleDefinition());
      sliderLabel2.setText("G: " + newValue.intValue());
    });
    slider3.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setB(newValue.intValue());
      sliderBox.setStyle(color.getStyleDefinition());
      sliderLabel3.setText("B: " + newValue.intValue());
    });

    HBox btnContainer = new HBox(20);
    btnContainer.getChildren().addAll(createBtn, copyBtn);
    btnContainer.setAlignment(Pos.CENTER);
    btnContainer.setPrefHeight(50);
    HBox upperHalf = new HBox(labelBox, sliderBox);
    content.getChildren().addAll(upperHalf, btnContainer);

    createBtn.setOnAction(e -> {
      color.createRandomColors();
      sliderLabel1.setText("R: " + color.getR());
      slider1.setValue(color.getR());
      sliderLabel2.setText("G: " + color.getG());
      slider2.setValue(color.getG());
      sliderLabel3.setText("B: " + color.getB());
      slider3.setValue(color.getB());
      sliderBox.setStyle(color.getStyleDefinition());
    });

    Scene scene = new Scene(content, 400, 200);
    scene.getStylesheets().add(Objects
            .requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.setTitle("Color Generator");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private void setupSlider(Slider ... slider) {
    for (Slider sliders : slider) {
      sliders.setValue(0);
      sliders.setMajorTickUnit(16);
      sliders.setMinorTickCount(0);
      sliders.setPrefWidth(250);
    }
  }

  private void toClickBoard(String txt) {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent copyContent = new ClipboardContent();
    copyContent.putString(txt);
    clipboard.setContent(copyContent);
  }

  /**
   * Main method. What else do I got to say.
   */
  public static void main(String[] args) {
    launch(args);
  }
}