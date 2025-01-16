package com.example.colorchooser;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/***
 * <p> </p>
 * Generates Random Color.
 * 214 lines of code for whole projekt.
 */

public class Main extends Application {

  private RgbColor color;

  @Override
  public void start(Stage primaryStage) {
    Button createBtn = new Button("Create random Color");
    Button copyBtn = new Button("Copy RGB Value");
    style(createBtn, copyBtn);

    ObservableList<Integer> numbers = FXCollections.observableArrayList();
    for (int i = 0; i <= 256; i++) {
      numbers.add(i);
    }

    Slider slider1 = new Slider(0, 256, 1);
    Slider slider2 = new Slider(0, 256, 1);
    Slider slider3 = new Slider(0, 256, 1);
    Label sliderValueLabel1 = new Label("R: " + (int) slider1.getValue());
    Label sliderValueLabel2 = new Label("G: " + (int) slider2.getValue());
    Label sliderValueLabel3 = new Label("B: " + (int) slider3.getValue());
    HBox.setMargin(sliderValueLabel1, new Insets(0, 10, 0, 0));
    HBox.setMargin(sliderValueLabel2, new Insets(0, 10, 0, 0));
    HBox.setMargin(sliderValueLabel3, new Insets(0, 10, 0, 0));
    setupSliderValueLabels(sliderValueLabel1, sliderValueLabel2, sliderValueLabel3);
    setupSlider(slider1, slider2, slider3);

    VBox content = new VBox();
    content.setSpacing(10);
    content.setAlignment(Pos.CENTER);

    slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
      RgbColor inputColor = new RgbColor(newValue.intValue(), (int) slider2.getValue(),
              (int) slider3.getValue());
      content.setStyle(inputColor.getStyleDefinition());
      sliderValueLabel1.setText("R: " + newValue.intValue());
    });
    slider2.valueProperty().addListener((observable, oldValue, newValue) -> {
      RgbColor inputColor = new RgbColor((int) slider1.getValue(), newValue.intValue(),
              (int) slider3.getValue());
      content.setStyle(inputColor.getStyleDefinition());
      sliderValueLabel2.setText("G: " + newValue.intValue());
    });
    slider3.valueProperty().addListener((observable, oldValue, newValue) -> {
      RgbColor inputColor = new RgbColor((int) slider1.getValue(), (int) slider2.getValue(),
              newValue.intValue());
      content.setStyle(inputColor.getStyleDefinition());
      sliderValueLabel3.setText("B: " + newValue.intValue());
    });

    HBox box1 = new HBox();
    box1.getChildren().addAll(sliderValueLabel1, slider1);
    HBox box2 = new HBox();
    box2.getChildren().addAll(sliderValueLabel2, slider2);
    HBox box3 = new HBox();
    box3.getChildren().addAll(sliderValueLabel3, slider3);
    VBox.setMargin(box1, new Insets(10, 0, 0, 0));
    VBox.setMargin(box2, new Insets(10, 0, 0, 0));
    VBox.setMargin(box3, new Insets(10, 0, 0, 0));
    styleBox(box1, box2, box3);

    HBox btnContainer = new HBox(20);
    btnContainer.setPadding(new Insets(5, 0, 5, 0));
    btnContainer.getChildren().addAll(createBtn, copyBtn);
    btnContainer.setAlignment(Pos.CENTER);
    content.getChildren().addAll(box1, box2, box3, btnContainer);

    createBtn.setOnAction(e -> {
      color = new RgbColor();
      sliderValueLabel1.setText("R: " + color.getR());
      slider1.setValue(color.getR());
      sliderValueLabel2.setText("G: " + color.getG());
      slider2.setValue(color.getG());
      sliderValueLabel3.setText("B: " + color.getB());
      slider3.setValue(color.getB());
      content.setStyle(color.getStyleDefinition());
    });

    copyBtn.setOnAction(e -> {
      if (color != null) {
        String text = color.getRgb();
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent copyContent = new ClipboardContent();
        copyContent.putString(text);
        clipboard.setContent(copyContent);
      }
    });
    Scene scene = new Scene(content, 400, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Color Generator");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void setupSlider(Slider ... slider) {
    for (Slider sliders : slider) {
      sliders.setValue(0);
      sliders.setMajorTickUnit(16);
      sliders.setMinorTickCount(0);
      sliders.setPrefWidth(250);
    }
  }

  private void styleBox(HBox ... boxes) {
    for (HBox hbox : boxes) {
      hbox.setMaxWidth(Double.MAX_VALUE);
      hbox.setAlignment(Pos.CENTER);
    }
  }

  private void setupSliderValueLabels(Label ... l) {
    for (Label labels : l) {
      labels.setPrefWidth(60);
      labels.setFont(new Font("Arial", 15));
      labels.setStyle("-fx-background-color: white; -fx-padding: 5px");
    }
  }

  private void style(Button ... btn) {
    for (Button buttons : btn) {
      buttons.setPrefWidth(Button.USE_COMPUTED_SIZE);
      buttons.setStyle("-fx-background-color: #4CAF50; " + "-fx-text-fill: white; "
              + "-fx-font-size: 14px;" + "-fx-font-weight: bold");
      buttons.setOnMouseEntered(e -> buttons.setStyle("-fx-background-color: #4DDD50; "
              + "-fx-text-fill: white; " + "-fx-font-size: 14px;" + "-fx-font-weight: bold"));
      buttons.setOnMouseExited(e -> buttons.setStyle("-fx-background-color: #4CAF50; "
              + "-fx-text-fill: white; " + "-fx-font-size: 14px;" + "-fx-font-weight: bold"));
    }
  }
}