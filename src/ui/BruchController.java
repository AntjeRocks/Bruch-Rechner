package ui;

import domain.Bruch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}
