package application;

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

public class ClientApp extends Application{
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
			
			InputStream stream =new FileInputStream("src/pictures/yahtzee.jpg");
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

			rules.setOnAction(e->{
				VBox rule;
				try {
					rule = getRules();
					Button back= new Button("BACK");
					back.setAlignment(Pos.BOTTOM_LEFT);
					back.setFont(Font.font("Ariel",FontWeight.BOLD, 12));
					
					VBox b=new VBox();
					b.getChildren().addAll(rule,back);
					b.setStyle("-fx-background-color: #A7C7E7; -fx-text-box-border: transparent;");
					Scene scene3 =new Scene(b, 600,600);
					primaryStage.setScene(scene3);
					primaryStage.show();
					
					back.setOnAction( e1-> {
						primaryStage.setScene(scene);
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		private VBox getRules() throws IOException {
		Label l=new Label("\n YAHTZEE RULES \n\n");
		//l.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY )));
		l.setFont(Font.font("Ariel", FontWeight.BOLD, 15));
		l.setTextFill(Color.BLACK );
		l.setTextAlignment(TextAlignment.JUSTIFY);
		l.setAlignment(Pos.TOP_CENTER);
		
	
		TextArea t=new TextArea();
		t.setEditable(false);
	        t.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
	        t.setMinHeight(450); 
		try {
			BufferedReader br=new BufferedReader(new FileReader("C:/Users/KORISNIK/Desktop/rules.txt"));
			StringBuilder sb= new StringBuilder();
			String line;
			while((line=br.readLine())!=null) {
				sb.append(line);
				sb.append("\n");
			}
			
		 	t.setText(sb.toString());
			br.close();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VBox r=new VBox();
		r.getChildren().addAll(l,t);
		r.setAlignment(Pos.CENTER);
		return r;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



