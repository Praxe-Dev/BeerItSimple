package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import view.LoginView;


public class Main extends Application{
    public static void main(String[] args) throws Exception {
        // test connection DB
//        Connection conn = DBConnection.getDBConnection();
//        String sqlInstruction = "select * from entity where id = 1";
//        PreparedStatement preparedStatement = conn.prepareStatement(sqlInstruction);
//
//        ResultSet data = preparedStatement.executeQuery();
//        data.next();
//
//        int id = data.getInt("id");
//        String mail = data.getString("mail");
//        String contactName = data.getString("contactName");
//        String phoneNumber = data.getString("phoneNumber");
//        int houseNumber = data.getInt("houseNumber");
//        String street = data.getString("street");
//        String fax = data.getString("fax");
//        String bankAccountNumber = data.getString("bankAccountNumber");
//        String VATNumber = data.getString("VATNumber");
//        String cityLabel = data.getString("cityLabel");
//        int cityZipCode = data.getInt("cityZipCode");
//
//        Entity test = new Entity(id, mail, contactName, phoneNumber, houseNumber, street, fax, bankAccountNumber, VATNumber, cityLabel, cityZipCode);
//        System.out.println(test);


        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));

        stage.setTitle("BeerItSimple - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}