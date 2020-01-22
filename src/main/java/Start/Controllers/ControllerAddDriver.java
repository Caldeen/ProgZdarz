package Start.Controllers;

import Start.DriversDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerAddDriver {
    private ControllerMain controllerMain;
    private Stage stage;

    @FXML
    Text outputText;
    @FXML
    TextField nameTextField;
    @FXML
    TextField surnameTextField;
    @FXML
    TextField salaryTextField;
    @FXML
    TextField idTextField;

    private DriversDao driversDao = new DriversDao();

    public void addButtonAction() {
        Integer id = driversDao.getMax()+1;
        if(!idTextField.getText().equals("")){
            id=Integer.parseInt(idTextField.getText());
        }
        outputText.setText("Wypelnij poprawnymi danymi, duplikaty niedozwolone");
        outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

        outputText.setVisible(true);
            driversDao.add(id,nameTextField.getText(),
                    surnameTextField.getText(),Double.parseDouble(salaryTextField.getText()));


        controllerMain.show();
        controllerMain.showWynagrodzenie();
        stage.close();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }





    public void setControllerMain(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }
}
