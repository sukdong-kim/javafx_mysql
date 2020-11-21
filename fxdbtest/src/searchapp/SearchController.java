package searchapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchController {
	Connection conn;
	Statement stmt = null;
	ResultSet srs;
	@FXML
	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
			System.out.println("DB 연결 완료");
			
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_UPDATABLE );
    		srs = stmt.executeQuery("select * from student");
    		
    		if(srs.next()) {
    			tf1.setText(srs.getString("id"));
    			tf2.setText(srs.getString("name"));
    			tf3.setText(srs.getString("email"));
    			tf4.setText(srs.getString("phone"));
    			label1.setText("confirm");
    		}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		} 
	}
	private void repeat() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
	
		stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_UPDATABLE );
		srs = stmt.executeQuery("select * from student");
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
    		if(srs.previous()) {
    			tf1.setText(srs.getString("id"));
    			tf2.setText(srs.getString("name"));
    			tf3.setText(srs.getString("email"));
    			tf4.setText(srs.getString("phone"));
    			label1.setText("confirm");
    		}
    		else
    			label1.setText("더이상 자료가 없음 !!!");   			       		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void onClickb2(ActionEvent event) {
    	try {
    		if(srs.next()) {
    			tf1.setText(srs.getString("id"));
    			tf2.setText(srs.getString("name"));
    			tf3.setText(srs.getString("email"));
    			tf4.setText(srs.getString("phone"));
    			label1.setText("confirm");
    		}
    		else
    			label1.setText("더이상 자료가 없음 !!!");   			       		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    

    @FXML
    void onClickb3(ActionEvent event) {
    	try {
    		String s1 = tf1.getText();
    		String s2 = tf2.getText();
    		String s3 = tf3.getText();
    		String s4 = tf4.getText();
    		
			stmt.executeUpdate("insert into student (id,name, email, phone) values ('" + s1 + "','" +s2+ "','" + s3+ "','" + s4 +"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				repeat();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
    }

    @FXML
    void onClickb4(ActionEvent event) {
    	try {
    		String s1 = tf1.getText();
    		String s2 = tf2.getText();
    		String s3 = tf3.getText();
    		String s4 = tf4.getText();
    	
    		stmt.executeUpdate("update student set name='" + s2 + "' where id='" +s1+ "'");
    		stmt.executeUpdate("update student set email='" + s3 + "' where id='" +s1+ "'");
    		stmt.executeUpdate("update student set phone='" + s4 + "' where id='" +s1+ "'");
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				repeat();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    }
    

    @FXML
    void onClickb5(ActionEvent event) {
    	try {
    		String s1 = tf1.getText();
    	
    		stmt.executeUpdate("delete from student where id='" +s1+ "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				repeat();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    }
    
    @FXML
    void onClickb6(ActionEvent event) {
    	try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.exit(0);
    }
    
}



