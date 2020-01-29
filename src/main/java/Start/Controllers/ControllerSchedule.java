package Start.Controllers;

import Start.Dao.DriversDao;
import Start.Dao.ScheduleDao;
import Start.Model.Driver;
import Start.Model.ScheduleRow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerSchedule {
    private ControllerMain controllerMain;
    private Stage stage;
    private ScheduleDao scheduleDao=new ScheduleDao()  ;
    @FXML
    Button UpdateButton;
    @FXML
    Text outputText;
    @FXML
    CheckBox ponCheckBox;
    @FXML
    CheckBox wtCheckBox;
    @FXML
    CheckBox srCheckBox;
    @FXML
    CheckBox czwCheckBox;
    @FXML
    CheckBox ptCheckBox;
    @FXML
    CheckBox sobCheckBox;
    @FXML
    CheckBox niedzCheckBox;
    @FXML
    Text driverText;
    @FXML
    TextField idTextField;

    @FXML
    TextField idWierszaTextField;
    DriversDao driversDao = new DriversDao();
    ScheduleRow scheduleRow;
    Driver driver;
    public void updateButtonAction() {
        outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        outputText.setText("Wypelnij poprawnymi danymi, duplikaty niedozwolone");
        outputText.setVisible(true);

        controllerMain.updateHarmonogram();
        stage.close();
        String text=driverText.getText();
        if(ponCheckBox.isSelected())
            scheduleRow.setPon(text);
        if(wtCheckBox.isSelected())
            scheduleRow.setWt(text);
        if(srCheckBox.isSelected())
            scheduleRow.setSr(text);
        if(czwCheckBox.isSelected())
            scheduleRow.setCzw(text);
        if(ptCheckBox.isSelected())
            scheduleRow.setPt(text);
        if(sobCheckBox.isSelected())
            scheduleRow.setSob(text);
        if(niedzCheckBox.isSelected())
            scheduleRow.setNiedz(text);

        scheduleDao.update(scheduleRow,null);
        controllerMain.updateHarmonogram();
        stage.close();
    }

    public void getDriverScheduleRowSet(){
        scheduleRow=scheduleDao.get(Integer.parseInt(idWierszaTextField.getText()));
        driver=driversDao.get(Integer.parseInt(idTextField.getText()));
        driverText.setText(driver.getFirstName()+ " "+ driver.getLastName());
        driverText.setVisible(true);

        UpdateButton.setVisible(true);
    }
    public void setControllerMain(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
