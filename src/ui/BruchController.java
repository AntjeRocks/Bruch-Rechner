package ui;

import domain.Bruch;
import domain.Bruchs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BruchController {

    @FXML
    TextField zaehler1 = new TextField();
    @FXML
    TextField nenner1 = new TextField();
    @FXML
    TextField zaehler2 = new TextField();
    @FXML
    TextField nenner2 = new TextField();
    @FXML
    Label ergebnis = new Label();
    @FXML
    ChoiceBox choiceboxi = new ChoiceBox();
    @FXML
    Button ganzzahlAnButton = new Button();
    @FXML
    Button ganzzahlAusButton = new Button();

    private String operator = "";
    private Bruch aktuellesErgebnis = new Bruch();

    public void los(final ActionEvent actionEvent) {
        Bruch bruch1 = new Bruch((Integer.parseInt(zaehler1.getText())), (Integer.parseInt(nenner1.getText())),0);
        final Bruch bruch2 = new Bruch((Integer.parseInt(zaehler2.getText())), (Integer.parseInt(nenner2.getText())),0);
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

    public void ganzzahlAn(ActionEvent actionEvent) {
        aktuellesErgebnis = new Bruchs().zuGanzzahl(aktuellesErgebnis);
        ergebnis.setText(aktuellesErgebnis.anzeigen());
    }

    public void ganzzahlAus(ActionEvent actionEvent) {
        aktuellesErgebnis = new Bruchs().vonGanzzahl(aktuellesErgebnis);
        ergebnis.setText(aktuellesErgebnis.anzeigen());
    }
}

