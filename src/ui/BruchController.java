package ui;

import domain.Bruch;
import domain.Bruchs;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BruchController {

    @FXML
    TextField zaehler1;
    @FXML
    TextField nenner1;
    @FXML
    TextField zaehler2;
    @FXML
    TextField nenner2;
    @FXML
    Label ergebnis;
    @FXML
    ChoiceBox choiceboxi;
    @FXML
    Button ganzzahlAnButton;
    @FXML
    Button ganzzahlAusButton;

    private String operator = "";
    private Bruch aktuellesErgebnis = new Bruch();

    public void los() {
        Bruch bruch1 = new Bruch(0, (Integer.parseInt(zaehler1.getText())), (Integer.parseInt(nenner1.getText())));
        final Bruch bruch2 = new Bruch(0, (Integer.parseInt(zaehler2.getText())), (Integer.parseInt(nenner2.getText())));
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
            default: aktuellesErgebnis = bruch1.mul(bruch2);
                ergebnis.setText(aktuellesErgebnis.anzeigen());
        }
        ganzzahlAnButton.setVisible(true);
        ganzzahlAusButton.setVisible(true);
    }

    public void selectOperator() {
        operator = choiceboxi.getValue().toString();
    }

    public void ganzzahlAn() {
        aktuellesErgebnis = new Bruchs().zuGanzzahl(aktuellesErgebnis);
        ergebnis.setText(aktuellesErgebnis.anzeigen());
    }

    public void ganzzahlAus() {
        aktuellesErgebnis = new Bruchs().vonGanzzahl(aktuellesErgebnis);
        ergebnis.setText(aktuellesErgebnis.anzeigen());
    }
}

