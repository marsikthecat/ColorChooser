package com.example.colorchooser;

import java.util.Random;

/**
 * Rgb Color Object which is a random RGB Color.
 */
public class RgbColor {

  private int blue;
  private int green;
  private int red;

  /**
   * Constructor with chosen values for red, green and blue.
   */
  public RgbColor(int red, int green, int blue) {
    this.blue = blue;
    this.green = green;
    this.red = red;
  }

  public int getB() {
    return blue;
  }

  public int getG() {
    return green;
  }

  public int getR() {
    return red;
  }

  public void setB(int blue) {
    this.blue = blue;
  }

  public void setG(int green) {
    this.green = green;
  }

  public void setR(int red) {
    this.red = red;
  }

  /**
   * Generates a random RGB Color.
   */
  public void createRandomColors() {
    Random random = new Random();
    this.blue = random.nextInt(256);
    this.green = random.nextInt(256);
    this.red = random.nextInt(256);
  }


  public String getStyleDefinition() {
    return String.format("-fx-background-color: rgb(%d, %d, %d);", red, green, blue);
  }

  public String getRgb() {
    return String.format("R: %d, G: %d, B: %d", red, green, blue);
  }

  /**
   * returns the Hex value of the rgb-color.
   */
  public String getHex() {
    String redHex = Integer.toHexString(red);
    String greenHex = Integer.toHexString(green);
    String blueHex = Integer.toHexString(blue);
    return String.format("#%s", redHex.concat(greenHex).concat(blueHex));
  }

  public String getCmy() {
    return String.format("C: %d, M: %d, Y: %d", 256 - red, 256 - green, 256 - blue);
  }
}