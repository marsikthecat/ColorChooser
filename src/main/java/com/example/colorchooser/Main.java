package com.example.colorchooser;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Generates Random Color.
 * 219 lines of code for whole projekt.
 */

public class Main extends Application {

  private final RgbColor color = new RgbColor(0, 0, 0);

  @Override
  public void start(Stage primaryStage) {
    Button createBtn = new Button("Create random Color");
    Button copyBtn = new Button("Copy RGB Value");
    style(createBtn, copyBtn);

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
    setupSliderLabels(sliderLabel1, sliderLabel2, sliderLabel3);

    VBox content = new VBox();
    content.setStyle(color.getStyleDefinition());
    content.setSpacing(10);
    content.setAlignment(Pos.CENTER);

    slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setR(newValue.intValue());
      content.setStyle(color.getStyleDefinition());
      sliderLabel1.setText("R: " + newValue.intValue());
    });
    slider2.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setG(newValue.intValue());
      content.setStyle(color.getStyleDefinition());
      sliderLabel2.setText("G: " + newValue.intValue());
    });
    slider3.valueProperty().addListener((observable, oldValue, newValue) -> {
      color.setB(newValue.intValue());
      content.setStyle(color.getStyleDefinition());
      sliderLabel3.setText("B: " + newValue.intValue());
    });

    HBox box1 = new HBox();
    box1.getChildren().addAll(sliderLabel1, slider1);
    HBox box2 = new HBox();
    box2.getChildren().addAll(sliderLabel2, slider2);
    HBox box3 = new HBox();
    box3.getChildren().addAll(sliderLabel3, slider3);
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
      color.createRandomColors();
      sliderLabel1.setText("R: " + color.getR());
      slider1.setValue(color.getR());
      sliderLabel2.setText("G: " + color.getG());
      slider2.setValue(color.getG());
      sliderLabel3.setText("B: " + color.getB());
      slider3.setValue(color.getB());
      content.setStyle(color.getStyleDefinition());
    });

    copyBtn.setOnAction(e -> {
      String text = color.getRgb();
      final Clipboard clipboard = Clipboard.getSystemClipboard();
      final ClipboardContent copyContent = new ClipboardContent();
      copyContent.putString(text);
      clipboard.setContent(copyContent);
    });
    Scene scene = new Scene(content, 400, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Color Generator");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * .
   */

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

  private void setupSliderLabels(Text ... texts) {
    for (Text text : texts) {
      text.setFont(Font.font("Arial", FontWeight.BOLD, 25));
      text.setStroke(Color.WHITE);
      text.setStrokeWidth(1);
      text.setFill(Color.BLACK);
      text.setWrappingWidth(80);
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