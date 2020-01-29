package Start.Controllers;

import Start.Model.Driver;
import Start.Dao.DriversDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerDelDriver {
    private ControllerMain controllerMain;
    private Stage stage;

    @FXML
    Button DelButton;
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
    public void getDriver(){
        Driver driver=driversDao.get(Integer.parseInt(idTextField.getText()));

        nameTextField.setText(driver.getFirstName());
        nameTextField.setVisible(true);


        surnameTextField.setText(driver.getLastName());
        surnameTextField.setVisible(true);


        salaryTextField.setText(String.valueOf(driver.getSalary()));
        salaryTextField.setVisible(true);
        DelButton.setVisible(true);
    }
    public void delButtonAction() {
        driversDao.del(Integer.parseInt(idTextField.getText()));
        outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        outputText.setText("Wypelnij poprawnymi danymi, duplikaty niedozwolone");
        outputText.setVisible(true);

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
