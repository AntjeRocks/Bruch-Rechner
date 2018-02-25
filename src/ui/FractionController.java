package ui;

import domain.Fraction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Random;

public class FractionController {

  @FXML
  TextField fractionalInteger1;
  @FXML
  TextField numerator1;
  @FXML
  TextField denominator1;
  @FXML
  TextField fractionalInteger2;
  @FXML
  TextField numerator2;
  @FXML
  TextField denominator2;
  @FXML
  Label result;
  @FXML
  ChoiceBox<String> choiceBoxOperator;
  @FXML
  Button buttonFractionalIntegerOn;
  @FXML
  Button buttonFractionalIntegerOff;

  private String operator = "";
  private Fraction currentFraction = new Fraction();

  public void calculate() {
      try {
          final Fraction fraction1 = new Fraction(Integer.parseInt(fractionalInteger1.getText()), (Integer.parseInt(numerator1.getText())),
                  (Integer.parseInt(denominator1.getText())));
          final Fraction fraction2 = new Fraction(Integer.parseInt(fractionalInteger2.getText()), (Integer.parseInt(numerator2.getText())),
                  (Integer.parseInt(denominator2.getText())));
          switch (operator) {
              case "+":
                  currentFraction = fraction1.addition(fraction2);
                  result.setText(currentFraction.toStringForLabel());
                  break;
              case "-":
                  currentFraction = fraction1.subtraction(fraction2);
                  result.setText(currentFraction.toStringForLabel());
                  break;
              case "*":
                  currentFraction = fraction1.multiplication(fraction2);
                  result.setText(currentFraction.toStringForLabel());
                  break;
              case "/":
                  currentFraction = fraction1.division(fraction2);
                  result.setText(currentFraction.toStringForLabel());
                  break;
              default:
                  currentFraction = fraction1.multiplication(fraction2);
                  result.setText(currentFraction.toStringForLabel());
          }
          buttonFractionalIntegerOn.setVisible(true);
          buttonFractionalIntegerOff.setVisible(true);
      } catch (IllegalArgumentException e) {
          Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
          alert.show();
      }
  }

  public void selectOperator() {
    operator = choiceBoxOperator.getValue();
  }

  public void fractionalIntegerOn() {
    currentFraction = currentFraction.transformToFractionWithFractionalInteger();
    result.setText(currentFraction.toStringForLabel());
  }

  public void fractionalIntegerOff() {
    currentFraction = currentFraction.transformToFractionWithoutFractionalInteger();
    result.setText(currentFraction.toStringForLabel());
  }

  public void randomFraction1(ActionEvent actionEvent) {
    fractionalInteger1.setText(String.valueOf(randomIntSigned()));
    numerator1.setText(String.valueOf(randomIntSigned()));
    denominator1.setText(String.valueOf(randomIntPositive()));
  }

  public void randomFraction2(ActionEvent actionEvent) {
    fractionalInteger2.setText(String.valueOf(randomIntSigned()));
    numerator2.setText(String.valueOf(randomIntSigned()));
    denominator2.setText(String.valueOf(randomIntPositive()));
  }

  private int randomIntSigned() {
    Random random = new Random();
    int nextInt = random.nextInt(510)-255;
    if (nextInt == 0) {
      nextInt = 255;
    }
    return nextInt;
  }

  private int randomIntPositive() {
    Random random = new Random();
    int nextInt = random.nextInt(254);
      return nextInt+1;
  }

}
