package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class SampleController {
	Connection conn;
	Statement stmt = null;
	ResultSet srs;
	@FXML
	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
			System.out.println("DB ���� �Ϸ�");
			
			stmt = conn.createStatement();
    		srs = stmt.executeQuery("select * from student");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("SQL ���� ����");
		} 
	}
    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;
    @FXML
    private Label label1;

    @FXML
    void onClickb1(ActionEvent event) {
    	try {
    		//		printTable(stmt);
    	//	ResultSet srs = stmt.executeQuery("select * from student");
    		if(srs.next()) {
    			tf1.setText(srs.getString("id"));
    			tf2.setText(srs.getString("name"));
    			tf3.setText(srs.getString("email"));
    			tf4.setText(srs.getString("phone"));
    		}
    		else
    			label1.setText("���̻� �ڷᰡ ���� !!!");
    			
   
    			

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

}
