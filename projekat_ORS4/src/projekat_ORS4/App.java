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
	
        private TextFlow chat;
	private String oponentsName;
	private GridPane grid;
	Random random = new Random();
	private ImageView diceImage1;
	private ImageView diceImage2;
	private ImageView diceImage3;
	private ImageView diceImage4;
	private ImageView diceImage5;
	private Button rollButton;
	private Dice dice1;
	private Dice dice2;
	private Dice dice3;
	private Dice dice4;
	private Dice dice5;
	private Dice[] dices;
	private boolean firstRoll = true;
	private int numberRoll = 0;
	
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
				VBox cet=makeChat();
				VBox desno=getGame();
				desno.setStyle("-fx-background-color: #A7C7E7");
				HBox c=new HBox();
			     
				c.getChildren().addAll(desno,cet);
				c.setStyle("-fx-background-color: #A7C7E7; -fx-text-box-border: transparent;");
				Scene scene2 =new Scene(c,630,630);
				primaryStage.setScene(scene2);
				primaryStage.show();
				
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public  VBox getGame() {
	    	VBox root = null;
	    	try {
	    	
	    	ArrayList<Label> scores = new ArrayList<Label>();
			ArrayList<Label> players = new ArrayList<Label>();
			ArrayList<Label> column1 = new ArrayList<Label>();
			ArrayList<Label> column2 = new ArrayList<Label>();
			ArrayList<Label> s1 = new ArrayList<Label>();
			ArrayList<Label> s2 = new ArrayList<Label>();
			ArrayList<Label> t1 = new ArrayList<Label>();
			ArrayList<Label> t2 = new ArrayList<Label>();
			
			grid = new GridPane();    
		    grid.setMinSize(400, 300); 
		    grid.setStyle("-fx-background-color: #A7C7E7 ");
		    grid.setAlignment(Pos.CENTER);
		    grid.setPadding(new Insets(10, 10, 10, 10)); 
		    grid.setVgap(5); 
		    grid.setHgap(5);
		
		    Label empty = new Label("");
		    Label ones = new Label("Ones");
		    ones.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    ones.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label twos = new Label("Twos");
		    twos.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    twos.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label threes = new Label("Threes");
		    threes.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    threes.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label fours = new Label("Fours");
		    fours.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    fours.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label fives = new Label("Fives");
		    fives.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    fives.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label sixes = new Label("Sixes");
		    sixes.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    sixes.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label sum = new Label("SUM");
		    sum.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
		    sum.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label bonus = new Label("BONUS");
		    bonus.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
		    bonus.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label threeOfAKind = new Label("Three of a kind");
		    threeOfAKind.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
		    threeOfAKind.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label fourOfAKind = new Label("Four of a kind");
		    fourOfAKind.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    fourOfAKind.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label fullHouse = new Label("Full house");
		    fullHouse.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    fullHouse.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label smallStraight = new Label("Small straight");
		    smallStraight.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    smallStraight.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label largeStraight = new Label("Large straight");
		    largeStraight.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    largeStraight.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label chance = new Label("Chance");
		    chance.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    chance.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label yahtzee = new Label("Yahtzee");
		    yahtzee.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    yahtzee.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label total = new Label("TOTAL");
		    total.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
		    total.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    
		    scores.add(empty);
		    scores.add(ones);
		    scores.add(twos);
		    scores.add(threes);
		    scores.add(fours);
		    scores.add(fives);
		    scores.add(sixes);
		    scores.add(sum);
		    scores.add(bonus);
		    scores.add(threeOfAKind);
		    scores.add(fourOfAKind);
		    scores.add(fullHouse);
		    scores.add(smallStraight);
		    scores.add(largeStraight);
		    scores.add(chance);
		    scores.add(yahtzee);
		    scores.add(total);
		    
		    for (int i = 0; i < scores.size(); i++) {
		    	scores.get(i).setPrefSize(90, 25);
		    	scores.get(i).setAlignment(Pos.CENTER);
		    	scores.get(i).setTextFill(Color.BLACK);
		    	scores.get(i).setStyle("-fx-background-color: #0000FF");
		    }
		    
		    Label player1 = new Label("Player1");
		    player1.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    player1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    Label player2 = new Label("Player2");
		    player2.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
		    player2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    
		    players.add(player1);
		    players.add(player2);
		    
		    for (int i = 0; i < players.size(); i++) {
		    	players.get(i).setPrefSize(90, 25);
		    	players.get(i).setAlignment(Pos.CENTER);
		    	players.get(i).setTextFill(Color.BLACK);
		    	players.get(i).setStyle("-fx-background-color: #0000FF");
		    }
		    
	        Label ones1 = new Label("");
	        Label twos1 = new Label("");
	        Label threes1 = new Label("");
	        Label fours1 = new Label("");
	        Label fives1 = new Label("");
	        Label sixes1 = new Label("");
	        Label sum1 = new Label("");
	        Label bonus1 = new Label("");
	        Label threeOfAKind1 = new Label("");
	        Label fourOfAKind1 = new Label("");
	        Label fullHouse1 = new Label("");
	        Label smallStraight1 = new Label("");
	        Label largeStraight1 = new Label("");
	        Label chance1 = new Label("");
	        Label yahtzee1 = new Label("");
	        Label total1 = new Label("");
	        
	        column1.add(ones1);
	        column1.add(twos1);
	        column1.add(threes1);
	        column1.add(fours1);
	        column1.add(fives1);
	        column1.add(sixes1);
	        column1.add(sum1);
	        column1.add(bonus1);
	        column1.add(threeOfAKind1);
	        column1.add(fourOfAKind1);
	        column1.add(fullHouse1);
	        column1.add(smallStraight1);
	        column1.add(largeStraight1);
	        column1.add(chance1);
	        column1.add(yahtzee1);
	        column1.add(total1);
	        
	        s1.add(ones1);
	        s1.add(twos1);
	        s1.add(threes1);
	        s1.add(fours1);
	        s1.add(fives1);
	        s1.add(sixes1);
	        
	        t1.add(sum1);
	        t1.add(bonus1);
	        t1.add(threeOfAKind1);
	        t1.add(fourOfAKind1);
	        t1.add(fullHouse1);
	        t1.add(smallStraight1);
	        t1.add(largeStraight1);
	        t1.add(chance1);
	        t1.add(yahtzee1);
	        
	        for (int i = 0; i < column1.size(); i++) {
	        	column1.get(i).setPrefSize(90, 25);
	        	column1.get(i).setAlignment(Pos.CENTER);
	        	column1.get(i).setTextFill(Color.DARKBLUE);
	        	column1.get(i).setStyle("-fx-background-color: #b8e6bf");
			column1.get(i).setFont(Font.font("Ariel", FontWeight.BOLD, 13));
	        	column1.get(i).setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    }
	        
	        ones1.setOnMouseClicked(e -> {
	        	if (!ones1.getText().equals("") && (ones1.getTextFill() == Color.RED || ones1.getTextFill() == Color.GRAY)) {
	        		ones1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        twos1.setOnMouseClicked(e -> {
	        	if (!twos1.getText().equals("") && (twos1.getTextFill() == Color.RED || twos1.getTextFill() == Color.GRAY)) {
	        		twos1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        threes1.setOnMouseClicked(e -> {
	        	if (!threes1.getText().equals("") && (threes1.getTextFill() == Color.RED || threes1.getTextFill() == Color.GRAY)) {
	        		threes1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        fours1.setOnMouseClicked(e -> {
	        	if (!fours1.getText().equals("") && (fours1.getTextFill() == Color.RED || fours1.getTextFill() == Color.GRAY)) {
	        		fours1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        fives1.setOnMouseClicked(e -> {
	        	if (!fives1.getText().equals("") && (fives1.getTextFill() == Color.RED || fives1.getTextFill() == Color.GRAY)) {
	        		fives1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        sixes1.setOnMouseClicked(e -> {
	        	if (!sixes1.getText().equals("") && (sixes1.getTextFill() == Color.RED || sixes1.getTextFill() == Color.GRAY)) {
	        		sixes1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag1 = true;
	        		for (int i = 0; i < s1.size(); i++)
	        			if (s1.get(i).getText().equals(""))
	        				flag1 = false;
	        		if (flag1 == true) {
	        			Functions.setSum(s1, sum1);
	    				Functions.setBonus(sum1, bonus1);
	        		}
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        threeOfAKind1.setOnMouseClicked(e -> {
	        	if (!threeOfAKind1.getText().equals("") && (threeOfAKind1.getTextFill() == Color.RED || threeOfAKind1.getTextFill() == Color.GRAY)) {
	        		threeOfAKind1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        fourOfAKind1.setOnMouseClicked(e -> {
	        	if (!fourOfAKind1.getText().equals("") && (fourOfAKind1.getTextFill() == Color.RED || fourOfAKind1.getTextFill() == Color.GRAY)) {
	        		fourOfAKind1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        fullHouse1.setOnMouseClicked(e -> {
	        	if (!fullHouse1.getText().equals("") && (fullHouse1.getTextFill() == Color.RED || fullHouse1.getTextFill() == Color.GRAY)) {
	        		fullHouse1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        smallStraight1.setOnMouseClicked(e -> {
	        	if (!smallStraight1.getText().equals("") && (smallStraight1.getTextFill() == Color.RED || smallStraight1.getTextFill() == Color.GRAY)) {
	        		smallStraight1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        largeStraight1.setOnMouseClicked(e -> {
	        	if (!largeStraight1.getText().equals("") && (largeStraight1.getTextFill() == Color.RED || largeStraight1.getTextFill() == Color.GRAY)) {
	        		largeStraight1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        chance1.setOnMouseClicked(e -> {
	        	if (!chance1.getText().equals("") && (chance1.getTextFill() == Color.RED || chance1.getTextFill() == Color.GRAY)) {
	        		chance1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	        
	        yahtzee1.setOnMouseClicked(e -> {
	        	if (!yahtzee1.getText().equals("") && (yahtzee1.getTextFill() == Color.RED || yahtzee1.getTextFill() == Color.GRAY)) {
	        		yahtzee1.setTextFill(Color.BLACK);
	        		
	        		for (int i = 0; i < column1.size(); i++)
						if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
							column1.get(i).setText("");
	        		
	        		dices[0].setAside(false);
			    	dices[1].setAside(false);
			    	dices[2].setAside(false);
			    	dices[3].setAside(false);
			    	dices[4].setAside(false);
			    	
					diceImage1.setEffect(null);
					diceImage2.setEffect(null);
					diceImage3.setEffect(null);
					diceImage4.setEffect(null);
					diceImage5.setEffect(null);
					
					firstRoll = true;
	        		numberRoll = 0;
	        		rollButton.setDisable(false);
	        		
	        		boolean flag2 = true;
	        		for (int i = 0; i < t1.size(); i++)
	        			if (t1.get(i).getText().equals(""))
	        				flag2 = false;
	        		if (flag2 == true) 
	        			Functions.setTotal(t1, total1);
	        		
	        		if (!total1.getText().equals("")) 
	        			rollButton.setDisable(true);
	        	}
	        });
	          
	        Label ones2 = new Label("");
	        Label twos2 = new Label("");
	        Label threes2 = new Label("");
	        Label fours2 = new Label("");
	        Label fives2 = new Label("");
	        Label sixes2 = new Label("");
	        Label sum2 = new Label("");
	        Label bonus2 = new Label("");
	        Label threeOfAKind2 = new Label("");
	        Label fourOfAKind2 = new Label("");
	        Label fullHouse2 = new Label("");
	        Label smallStraight2 = new Label("");
	        Label largeStraight2 = new Label("");
	        Label chance2 = new Label("");
	        Label yahtzee2 = new Label("");
	        Label total2 = new Label("");
	        
	        column2.add(ones2);
	        column2.add(twos2);
	        column2.add(threes2);
	        column2.add(fours2);
	        column2.add(fives2);
	        column2.add(sixes2);
	        column2.add(sum2);
	        column2.add(bonus2);
	        column2.add(threeOfAKind2);
	        column2.add(fourOfAKind2);
	        column2.add(fullHouse2);
	        column2.add(smallStraight2);
	        column2.add(largeStraight2);
	        column2.add(chance2);
	        column2.add(yahtzee2);
	        column2.add(total2);
	        
	        s2.add(ones1);
	        s2.add(twos1);
	        s2.add(threes1);
	        s2.add(fours1);
	        s2.add(fives1);
	        s2.add(sixes1);
	        
	        t2.add(sum1);
	        t2.add(bonus1);
	        t2.add(threeOfAKind1);
	        t2.add(fourOfAKind1);
	        t2.add(fullHouse1);
	        t2.add(smallStraight1);
	        t2.add(largeStraight1);
	        t2.add(chance1);
	        t2.add(yahtzee1);
	        
	        for (int i = 0; i < column2.size(); i++) {
	        	column2.get(i).setPrefSize(90, 25);
	        	column2.get(i).setAlignment(Pos.CENTER);
	        	column2.get(i).setTextFill(Color.DARKBLUE);
	        	column2.get(i).setStyle("-fx-background-color: #b8e6bf");
			column2.get(i).setFont(Font.font("Ariel", FontWeight.BOLD, 13));
	        	column2.get(i).setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
		    }
	        
		    for (int i = 0; i < scores.size(); i++)
		    	grid.add(scores.get(i), 0, i+1);
		    
		    for (int i = 0; i < players.size(); i++)
		    	grid.add(players.get(i), i+1, 1);
		    
		    for (int i = 0; i < column1.size(); i++)
		    	grid.add(column1.get(i), 1, i+2);
		    
		    for (int i = 0; i < column2.size(); i++)
		    	grid.add(column2.get(i), 2, i+2);
		    
			diceImage1 = new ImageView();
			diceImage2 = new ImageView();
			diceImage3 = new ImageView();
			diceImage4 = new ImageView();
			diceImage5 = new ImageView();
			
			File file = new File("src/application/dice1.png");
			diceImage1.setImage(new Image(file.toURI().toString()));
			diceImage2.setImage(new Image(file.toURI().toString()));
			diceImage3.setImage(new Image(file.toURI().toString()));
			diceImage4.setImage(new Image(file.toURI().toString()));
			diceImage5.setImage(new Image(file.toURI().toString()));
	    	
	    	diceImage1.setFitHeight(50);
	        diceImage1.setFitWidth(50);
	        diceImage2.setFitHeight(50);
	        diceImage2.setFitWidth(50);
	        diceImage3.setFitHeight(50);
	        diceImage3.setFitWidth(50);
	        diceImage4.setFitHeight(50);
	        diceImage4.setFitWidth(50);
	        diceImage5.setFitHeight(50);
	        diceImage5.setFitWidth(50);
	       
	        ArrayList<ImageView> dicesImage = new ArrayList<>();
			dicesImage.add(diceImage1);
			dicesImage.add(diceImage2);
			dicesImage.add(diceImage3);
			dicesImage.add(diceImage4);
			dicesImage.add(diceImage5);
			
			dice1 = new Dice();
		    dice2 = new Dice();
			dice3 = new Dice();
			dice4 = new Dice();
			dice5 = new Dice();
	        
	        dices = new Dice[5];
			dices[0] = dice1;
			dices[1] = dice2;
			dices[2] = dice3;
			dices[3] = dice4;
			dices[4] = dice5;
			
			int[] dicesRoll = new int[6];
			dicesRoll[0] = dice1.getNumber();
			dicesRoll[1] = dice2.getNumber();
			dicesRoll[2] = dice3.getNumber();
			dicesRoll[3] = dice4.getNumber();
			dicesRoll[4] = dice5.getNumber();
			
			DropShadow borderGlow= new DropShadow();
	        borderGlow.setColor(Color.WHITE);
	        borderGlow.setOffsetY(0f);
	        borderGlow.setOffsetX(0f);
	        borderGlow.setWidth(50);
	        borderGlow.setHeight(50);
	        borderGlow.setRadius(10); 
	        borderGlow.setSpread(0.8);
	        
			diceImage1.setOnMouseClicked(e -> {
				if (!dice1.isAside() && firstRoll == false && numberRoll < 3) {
					 dice1.setAside(true);
					 diceImage1.setEffect(borderGlow);
				}
				else {
					dice1.setAside(false);
					diceImage1.setEffect(null);
				}
			});
			
			diceImage2.setOnMouseClicked(e -> {
				if (!dice2.isAside() && firstRoll == false && numberRoll < 3) {
					 dice2.setAside(true);
					 diceImage2.setEffect(borderGlow);
				}
				else {
					dice2.setAside(false);
					diceImage2.setEffect(null);
				}
			});
			
			diceImage3.setOnMouseClicked(e -> {
				if (!dice3.isAside() && firstRoll == false && numberRoll < 3) {
					dice3.setAside(true);
					diceImage3.setEffect(borderGlow);
				}
				else {
					dice3.setAside(false);
					diceImage3.setEffect(null);
				}
			});
			
			diceImage4.setOnMouseClicked(e -> {
				if (!dice4.isAside() && firstRoll == false && numberRoll < 3) {
					dice4.setAside(true);
				    diceImage4.setEffect(borderGlow);
				}
				else {
					dice4.setAside(false);
					diceImage4.setEffect(null);
				}
			});
			
			diceImage5.setOnMouseClicked(e -> {
				if (!dice5.isAside() && firstRoll == false && numberRoll < 3) {
					dice5.setAside(true);
					diceImage5.setEffect(borderGlow);
				}
				else {
					dice5.setAside(false);
					diceImage5.setEffect(null);
				}
			});
			
			rollButton = new Button("Roll");
			rollButton.setTextFill(Color.BLACK);
			rollButton.setStyle("-fx-background-color: #EE2C2C");
			rollButton.setPrefHeight(50);
			rollButton.setPrefWidth(50);
			rollButton.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
			rollButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
			
			rollButton.setOnAction (e -> {
				if (numberRoll < 3) {
				for (int i = 0; i < column1.size(); i++)
					if (column1.get(i).getTextFill() == Color.RED || column1.get(i).getTextFill() == Color.GRAY)
						column1.get(i).setText("");
				firstRoll = false;
				numberRoll++;
		        rollButton.setDisable(true);
		        
				Thread thread = new Thread() {
					public void run() {
						try {
							for (int i = 0; i < 3; i++) {
						        for (int j = 0; j < dices.length; j++) {
						        	if (!dices[j].isAside()) {
						        		int number = random.nextInt(6) + 1;
								        File file = new File("src/application/dice" + number + ".png");
								        dicesImage.get(j).setImage(new Image(file.toURI().toString()));
						        	}
						        }
						        Thread.sleep(5);
						    }
							
							for (int j = 0; j < dices.length; j++) {
					        	if (!dices[j].isAside()) {
					        	int number = dices[j].getNumber();
						        File file = new File("src/application/dice" + number + ".png");
						        dicesImage.get(j).setImage(new Image(file.toURI().toString()));	
					        	}
							}
							
						    rollButton.setDisable(false);
						    if (numberRoll == 3) {
						    	dices[0].setAside(false);
						    	dices[1].setAside(false);
						    	dices[2].setAside(false);
						    	dices[3].setAside(false);
						    	dices[4].setAside(false);
								diceImage1.setEffect(null);
								diceImage2.setEffect(null);
								diceImage3.setEffect(null);
								diceImage4.setEffect(null);
								diceImage5.setEffect(null);
								rollButton.setDisable(true);
						    }
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				};
				
				 for (int j = 0; j < dices.length; j++) 
			        	if (!dices[j].isAside()) 
			        	dices[j].setNumber();							
			    
				dicesRoll[0] = dice1.getNumber();
				dicesRoll[1] = dice2.getNumber();
				dicesRoll[2] = dice3.getNumber();
				dicesRoll[3] = dice4.getNumber();
				dicesRoll[4] = dice5.getNumber();
				
				if (ones1.getText().equals(""))
					Functions.setOnes(dicesRoll, ones1);
				
				if (twos1.getText().equals(""))
					Functions.setTwos(dicesRoll, twos1);
				
				if (threes1.getText().equals(""))
					Functions.setThrees(dicesRoll, threes1);
				
				if (fours1.getText().equals(""))
					Functions.setFours(dicesRoll, fours1);
				
				if (fives1.getText().equals(""))
					Functions.setFives(dicesRoll, fives1);
				
				if (sixes1.getText().equals(""))
					Functions.setSixes(dicesRoll, sixes1);
				
				if (threeOfAKind1.getText().equals(""))
					Functions.setThreeOfAKind(dicesRoll, threeOfAKind1);
				
				if (fourOfAKind1.getText().equals(""))
					Functions.setFourOfAKind(dicesRoll, fourOfAKind1);
				
				if (fullHouse1.getText().equals(""))
					Functions.setFullHouse(dicesRoll, fullHouse1);
				
	    		if (smallStraight1.getText().equals(""))
	    			Functions.setSmallStraight(dicesRoll, smallStraight1);
	    		
	    		if (largeStraight1.getText().equals(""))
	    			Functions.setLargeStraight(dicesRoll, largeStraight1);
	    		
	    		if (chance1.getText().equals(""))
	    			Functions.setChance(dicesRoll, chance1);
	    		
	    		if (yahtzee1.getText().equals(""))
	    			Functions.setYahtzee(dicesRoll, yahtzee1);
				
				thread.start();
				}
			});
			
			HBox t = new HBox(15);
			t.getChildren().addAll(grid);
			
			
			HBox d = new HBox(15);
			d.getChildren().addAll(rollButton, diceImage1, diceImage2, diceImage3, diceImage4, diceImage5);
			d.setStyle("-fx-background-color: #A7C7E7");
			Insets insets = new Insets(10,10,10,10);
			d.setPadding(insets);
			
			root = new VBox(15);
			root.getChildren().addAll(t,d);
			root.setStyle("-fx-background-color: #A7C7E7");
			
			
		         } catch(Exception e5) {
			e5.printStackTrace();
		}
	    	return root;
	    	
	}
	 private VBox makeChat() {
	    	Label label=new Label("Chat : ");
	    	label.setTextFill(Color.BLACK );
			label.setTextAlignment(TextAlignment.JUSTIFY);
			label.setAlignment(Pos.TOP_CENTER);
			label.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
	    	
	    	chat=new TextFlow();
	    	chat.setPrefWidth(180);
	    	chat.setPrefHeight(330);
	    	chat.setStyle("-fx-background-color: #336699; -fx-text-box-border: transparent;");
	    	chat.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
	    	chat.setPadding(new Insets(10));
	        
	    	
	    	TextField input =new TextField();
	    	input.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
	    	Button send= new Button("SEND");
	    	send.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
	    	
	    	HBox toSend=new HBox(10);
	    	toSend.setAlignment(Pos.BASELINE_RIGHT);
	    	toSend.getChildren().addAll(input,send);
	    	
	    	Button leave= new Button("LEAVE");
	    	leave.setFont(Font.font("Ariel", FontWeight.BOLD, 13));
	   
	    	
	    	VBox chatBox=new VBox(10);
	    	chatBox.getChildren().addAll(leave, label, chat, toSend);
	    	
	    	 return chatBox;
	    }
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
