package projekat_ORS4;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			Group start=new Group();
			Button rules = new Button("RULES");
			rules.setMaxSize(100, 20);
			rules.setLayoutX(20);
			rules.setLayoutY(30);
			rules.setFont(Font.font("Ariel",FontWeight.BOLD, 13));
			
			
			Button leave = new Button("LEAVE");
			leave.setMaxSize(100,20);
			leave.setLayoutX(520);
			leave.setLayoutY(30);
			leave.setFont(Font.font("Ariel",FontWeight.BOLD, 13));
			
			InputStream stream =new FileInputStream("C:/Users/KORISNIK/Desktop/yahtzee.jpg");
			Image image=new Image(stream);
			ImageView iv=new ImageView();
			iv.setImage(image);
			iv.setX(0);
			iv.setY(0);
			iv.setFitWidth(600);
			iv.setPreserveRatio(true);
			
			
			Label label1= new Label("ENTER USERNAME:");
			label1.setLayoutX(250);
			label1.setLayoutY(340);
			label1.setTextFill(Color.rgb(0, 0, 0));
			label1.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
			
			TextField userName = new TextField();
			userName.setMaxWidth(260);
			userName.setMaxHeight(40);
			userName.setLayoutX(230);
			userName.setLayoutY(370);
			//userName.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
			
		
			Button play = new Button(" PLAY ");
			play.setMaxWidth(250);
			play.setMinHeight(30);
			play.setLayoutX(270);
			play.setLayoutY(410);
			play.setFont(Font.font("Ariel",FontWeight.BOLD, 13));
			
			Label error =new Label(" ENTER USERNAME! ");
			error.setTextFill(Color.rgb(255, 0, 0));
			error.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
			error.setVisible(false);
			error.setLayoutX(245);
			error.setLayoutY(460);
			
			start.getChildren().addAll(iv,rules, leave, label1, userName, play,error);
			
			Scene scene = new Scene(start,600,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("YAHTZEE"); 
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();
			
			
			play.setOnAction(e-> {
				if(userName.getText().isEmpty()) {
					error.setVisible(true);
					return;
				}
				error.setText(" ");
				
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
