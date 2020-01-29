package Start.Controllers;

import Start.Model.Driver;
import Start.Dao.DriversDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerUpdateDriver {
    private ControllerMain controllerMain;
    private Stage stage;

    @FXML
    Button UpdateButton;
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
    @FXML
    TextField godzinyPracyTextField;

    private DriversDao driversDao = new DriversDao();
    public void getDriver(){
        Driver driver=driversDao.get(Integer.parseInt(idTextField.getText()));

        nameTextField.setText(driver.getFirstName());
        nameTextField.setVisible(true);

        surnameTextField.setText(driver.getLastName());
        surnameTextField.setVisible(true);

        salaryTextField.setText(String.valueOf(driver.getSalary()));
        salaryTextField.setVisible(true);

        godzinyPracyTextField.setText(String.valueOf(driver.getHours_worked()));
        godzinyPracyTextField.setVisible(true);
        UpdateButton.setVisible(true);
    }
    public void updateButtonAction() {
        Driver driver = new Driver(Integer.parseInt(idTextField.getText()), nameTextField.getText(),
                surnameTextField.getText(), Double.parseDouble(salaryTextField.getText()));
        driver.setHours_worked(Integer.parseInt(godzinyPracyTextField.getText()));
        driversDao.update(driver, null);
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
