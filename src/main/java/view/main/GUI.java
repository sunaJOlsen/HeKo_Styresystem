package view.main;

import controller.excel.ExcelConnection;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import view.GuiSingleton;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GUI {
	private Scene scene;
	static GuiSingleton gui;

	public GUI() {
		gui = GuiSingleton.getInstance();
	}



	public void filplacering(Stage primaryStage) {

		GridPane gP = new GridPane();
		Label l = new Label("Filplacering:");
		TextField text = new TextField();
		// finder fil-stien
		String filename = "test.txt";
		String excelPath = null;
		//FileReader
		Map<String, String> filplaceringer = new HashMap<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){

			List<String[]> paths = new ArrayList<>();

			while(bufferedReader.readLine()!= null)
			excelPath = bufferedReader.readLine();


			text.setText(excelPath);



		} catch (Exception e) {
			e.printStackTrace();
		}

		Button findFilButton = new Button("Ændr filplacering");
		findFilButton.setOnAction(event -> {
			DirectoryChooser dir = new DirectoryChooser();
			dir.setTitle("Vælg filplacering");
			File excelplacering = dir.showDialog(primaryStage);
			text.setText(excelplacering.getAbsolutePath() + "\\IndstillingsInfo.xlsx");

		});

		Button startButton = new Button("Start");
		startButton.setOnAction(event -> startButtonClicked(text, filename, primaryStage));
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(event -> primaryStage.close());

		gP.add(l, 3, 3);
		gP.add(text, 3, 4);
		// gP.add(labelFil, 5, 4);
		gP.add(findFilButton, 4, 4);
		gP.add(startButton, 3, 5);
		gP.add(annullerButton, 4, 5);

		scene = new Scene(gP);
		primaryStage.setScene(scene);
		
		// The name of the file to open.

		primaryStage.show();
	}

	private void startButtonClicked(TextField text, String filename, Stage primaryStage) {
		{
			String s = text.getText();
			try {
				gui.ec = new ExcelConnection(s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
				//TODO change to new setup
				//Write Map to it
				bufferedWriter.write(text.getText());

			} catch (IOException ex1) {

			}
				gui.hovedMenu.hovedMenu(primaryStage);
		}
	}

	public Scene getScene() {
		return scene;
	}

}
