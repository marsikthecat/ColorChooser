package com.example.colorchooser;

import java.util.Random;

/**
 * Rgb Color Object which is a random RGB Color.
 * <p> </p>
 */

public class RgbColor {

  private final int blue;
  private final int green;
  private final int red;

  /**
   * Constructor with random values for red, green and blue.
   */

  public RgbColor() {
    Random random = new Random();
    this.blue = random.nextInt(256);
    this.green = random.nextInt(256);
    this.red = random.nextInt(256);
  }

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

  public String getStyleDefinition() {
    return String.format("-fx-background-color: rgb(%d, %d, %d);", red, green, blue);
  }

  public String getRgb() {
    return String.format("R: %d, G: %d, B: %d", red, green, blue);
  }
}