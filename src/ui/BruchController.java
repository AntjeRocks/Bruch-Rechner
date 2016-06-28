package ui;

import domain.Bruch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;

public class BruchController {

  @FXML
  TextField ganzzahl1;
  @FXML
  TextField zaehler1;
  @FXML
  TextField nenner1;
  @FXML
  TextField ganzzahl2;
  @FXML
  TextField zaehler2;
  @FXML
  TextField nenner2;
  @FXML
  Label ergebnis;
  @FXML
  ChoiceBox<String> choiceboxi;
  @FXML
  Button ganzzahlAnButton;
  @FXML
  Button ganzzahlAusButton;

  private String operator = "";
  private Bruch aktuellesErgebnis = new Bruch();

  public void los() {
      try {
          final Bruch bruch1 = new Bruch(Integer.parseInt(ganzzahl1.getText()), (Integer.parseInt(zaehler1.getText())),
                  (Integer.parseInt(nenner1.getText())));
          final Bruch bruch2 = new Bruch(Integer.parseInt(ganzzahl2.getText()), (Integer.parseInt(zaehler2.getText())),
                  (Integer.parseInt(nenner2.getText())));
          switch (operator) {
              case "+":
                  aktuellesErgebnis = bruch1.add(bruch2);
                  ergebnis.setText(aktuellesErgebnis.anzeigen());
                  break;
              case "-":
                  aktuellesErgebnis = bruch1.sub(bruch2);
                  ergebnis.setText(aktuellesErgebnis.anzeigen());
                  break;
              case "*":
                  aktuellesErgebnis = bruch1.mul(bruch2);
                  ergebnis.setText(aktuellesErgebnis.anzeigen());
                  break;
              case "/":
                  aktuellesErgebnis = bruch1.div(bruch2);
                  ergebnis.setText(aktuellesErgebnis.anzeigen());
                  break;
              default:
                  aktuellesErgebnis = bruch1.mul(bruch2);
                  ergebnis.setText(aktuellesErgebnis.anzeigen());
          }
          ganzzahlAnButton.setVisible(true);
          ganzzahlAusButton.setVisible(true);
      } catch (IllegalArgumentException e) {
          Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
          alert.show();
      }
  }

  public void selectOperator() {
    operator = choiceboxi.getValue();
  }

  public void ganzzahlAn() {
    aktuellesErgebnis = aktuellesErgebnis.zuGanzzahlUmwandeln();
    ergebnis.setText(aktuellesErgebnis.anzeigen());
  }

  public void ganzzahlAus() {
    aktuellesErgebnis = aktuellesErgebnis.zuBruchUmwandeln();
    ergebnis.setText(aktuellesErgebnis.anzeigen());
  }

  public void zufallBruch1(ActionEvent actionEvent) {
    ganzzahl1.setText(String.valueOf(randomIntSigned()));
    zaehler1.setText(String.valueOf(randomIntSigned()));
    nenner1.setText(String.valueOf(randomIntPositiv()));
  }

  public void zufallBruch2(ActionEvent actionEvent) {
    ganzzahl2.setText(String.valueOf(randomIntSigned()));
    zaehler2.setText(String.valueOf(randomIntSigned()));
    nenner2.setText(String.valueOf(randomIntPositiv()));
  }

  private int randomIntSigned() {
    Random random = new Random();
    int nextInt = random.nextInt(510)-255;
    if (nextInt == 0) {
      nextInt = 255;
    }
    return nextInt;
  }

  private int randomIntPositiv() {
    Random random = new Random();
    int nextInt = random.nextInt(254);
    return nextInt+1;
  }

}
