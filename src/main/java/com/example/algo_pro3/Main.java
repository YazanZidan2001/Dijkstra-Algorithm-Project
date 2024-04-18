package com.example.algo_pro3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.text.Font;
import com.example.algo_pro3.Graph.Dijkstra;
import com.example.algo_pro3.Graph.Graph;
import com.example.algo_pro3.Graph.Vertex;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
	private Pane pane;
	private Graph graph;
	private ChoiceBox<Vertex> sourceBox;
	private TextField txMaster;
	private ChoiceBox<Vertex> distinationBox;
	// private List<Circle> locationLsit;
	private final Double minX = 34.14792671298243;
	private final Double maxY = 31.215929927124133;
	private final Double maxX = 34.57028440004385;
	private final Double minY = 31.604174213399;
	private final Double maxWindowX = 772.0;
	private final Double maxWindowY = 827.0;
	private static int lock = 0;
	private Dijkstra dijkstra;

	private TextArea textArea;
	private TextField textField;
	private List<Line> Linelist = new ArrayList<>();
//	private final Label startLabel = null;

	Line line;
	Image image;
	ImageView imageView;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage)  {

//		// this for the main page
//		VBox mainPage = new VBox(120);
//		mainPage.setAlignment(Pos.CENTER);
//		mainPage.setPadding(new Insets(100, 100, 100, 100));
//		mainPage.setStyle("-fx-background-color:burlywood");
//		// the image in the first scene
//		Image imagePalestine = new Image("file:palestineimageFinal.png");
//		ImageView mainImage = new ImageView(imagePalestine);
//		mainImage.setFitHeight(imagePalestine.getHeight());
//		mainImage.setFitWidth(imagePalestine.getWidth());
//		mainImage.setEffect(glow(Color.RED));
//		// some effects on the image
//		mainImage.setOnMouseEntered(e -> {
//			mainImage.setEffect(glow(Color.GOLD));
//		});
//		mainImage.setOnMouseExited(e -> {
//			mainImage.setEffect(glow(Color.RED));
//		});
//		// the label in the first scene
//		Label mainLabel = new Label("Gaza Maps System (GMS)");
//		mainLabel.setStyle("-fx-text-fill:darkred;-fx-font-size:60px;-fx-font-family:italy");
//		mainPage.getChildren().addAll(mainLabel, mainImage);

//				mainImage.setOnMouseClicked(e -> {
//			stage.setScene(new Scene(sp, 1200, 850));
//		});


		image = new Image("file:map.jpg");
		imageView = new ImageView(image);
//		System.out.println(image.getHeight() + " , " + image.getWidth());
		imageView.prefHeight(image.getHeight());
		imageView.prefWidth(image.getWidth());
		imageView.setPickOnBounds(true);
		pane = new Pane(imageView);

		VBox vb = new VBox(30);
		vb.setAlignment(Pos.TOP_CENTER);
		vb.setPadding(new Insets(50, 50, 50, 50));

		txMaster = new TextField();
		txMaster.setPrefSize(100, 50);
		txMaster.setEditable(false);
		txMaster.setStyle("-fx-font-size:30px;-fx-text-fill:red");

		HBox hb1 = new HBox(5);
		hb1.setAlignment(Pos.CENTER);
		Label sourceLabel = new Label("Source:");
		sourceLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));

		sourceBox = new ChoiceBox<>();
		sourceBox.setStyle("-fx-font: 20px \"Serif\"; -fx-border-color: black; -fx-background-color:tan ");

		hb1.getChildren().addAll(sourceLabel, sourceBox);

		Label distinationLabel = new Label("Distination:");
		distinationLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));

		distinationBox = new ChoiceBox<>();
		distinationBox.setStyle("-fx-font: 20px \"Serif\"; -fx-border-color: black; -fx-background-color:tan ");

		HBox hb2 = new HBox(5);
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(distinationLabel, distinationBox);

		VBox PathBox = new VBox(20);
		PathBox.setAlignment(Pos.CENTER);
		Label pathLabel = new Label("Path:");
		pathLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));

		textArea = new TextArea();
		textArea.setPrefSize(120, 200);
		textArea.setEditable(false);
		textArea.setStyle("-fx-border-color : black;");
		Font font = Font.font("Monospaced", 16);
		textArea.setFont(font);

		Label disLabel = new Label("Distance");
		disLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));

		textField = new TextField();
		textField.setEditable(false);
		textField.setPrefSize(100, 30);
		textField.setStyle("-fx-border-color : black;");
		textField.setFont(font);

		Button runBut = new Button("Run");
		runBut.setPrefSize(150, 30);
		runBut.setStyle("-fx-font: 20px \"Serif\"; -fx-border-color: black; -fx-background-color:tan ");
		runBut.setOnAction(e -> runTheAction());

		PathBox.getChildren().addAll(pathLabel, textArea, disLabel, textField);

//		vb.getChildren().add(txMaster);

		Button restBut = new Button("Rest");
		restBut.setPrefSize(150, 30);
		restBut.setStyle("-fx-font: 20px \"Serif\"; -fx-border-color: black; -fx-background-color:tan ");
		restBut.setOnAction(q->{
			sourceBox.getSelectionModel().clearSelection();
			distinationBox.getSelectionModel().clearSelection();
			textArea.setText("");
			textField.setText("");
			clearMap();
		});

		vb.getChildren().addAll( hb1, hb2, runBut, PathBox,restBut);
		vb.setStyle("-fx-background-color:lightblue");

		readFromFile();

		dijkstra = new Dijkstra(graph);

		pane.setMinSize(image.getWidth(), image.getHeight());
		SplitPane sp = new SplitPane( pane,vb);
		Scene scene = new Scene(sp,1250,image.getHeight());

		stage.setScene(scene);
		stage.show();

	}

	private void readFromFile() {
		int vertexNumber ;
//		int EdgeNumber = 0;
		try (Scanner scan = new Scanner(new File("mapFinal.txt"))) {
			String[] orders = scan.nextLine().split(",");
			if (orders.length != 0) {
				vertexNumber = Integer.parseInt(orders[0]);
				graph = new Graph(vertexNumber);

//				EdgeNumber = Integer.parseInt(orders[1]);
				for (int i = 0; i < vertexNumber; i++) {
					String[] s = scan.nextLine().split(",");
					String name = s[0];
					Double lat = Double.parseDouble(s[1]);
					Double lon = Double.parseDouble(s[2]);
					boolean isCity = s[3].equals("C");
                    Vertex vertex = new Vertex(lat, lon, name, isCity);
					graph.add(vertex);
					if (vertex.isCity())
						newLocationAdd(vertex);
				}
			}
			while (scan.hasNextLine()) {
				String[] s = scan.nextLine().split(",");
//				System.out.println(s[0] + "," + s[1]);
                graph.addEdge(new Vertex(s[0]), new Vertex(s[1]));
            }
		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e);
		}
	}

	private void newLocationAdd(Vertex vertex) {
		sourceBox.getItems().add(vertex);

		distinationBox.getItems().add(vertex);
		double x = (maxWindowX * (vertex.getLongitude() - minX)) / (maxX - minX);
		double y = (maxWindowY * (vertex.getLatitude() - minY)) / (maxY - minY);
//		System.out.println(x + " ," + y);
		Label label = new Label(vertex.getLocation());
		label.setLayoutX(x + 5);
		label.setLayoutY(y + 5);
//		label.setPrefSize(30,30);
		pane.getChildren().add(label);
		Circle circle = new Circle(x, y, 4);
		circle.setStyle("-fx-fill:red");
		circle.setId(vertex.getLocation());
		pane.getChildren().add(circle);
		circle.setOnMouseEntered(e -> txMaster.setText(circle.getId()));
		circle.setOnMouseExited(e ->
			txMaster.setText("")
		);
		circle.setOnMouseClicked(e -> {
			if (lock == 0) {
				circle.setFill(Color.BLACK);
				lock++;
				sourceBox.getSelectionModel().select(graph.get(new Vertex(circle.getId())));
			} else if (lock == 1) {
				if (circle.getFill().equals(Color.BLACK)) {
					lock = 0;
					sourceBox.getSelectionModel().select(null);
					distinationBox.getSelectionModel().select(null);
					pane.getChildren().forEach(node -> {
						if (node instanceof Circle) {
							((Circle) node).setFill(Color.RED);
						}
					});
				} else {
					circle.setFill(Color.BLACK);
					lock++;
					distinationBox.getSelectionModel().select(graph.get(new Vertex(circle.getId())));

				}
			} else if (lock == 2) {
				if (circle.getFill().equals(Color.BLACK)) {
					lock = 0;
					sourceBox.getSelectionModel().select(null);
					distinationBox.getSelectionModel().select(null);
					pane.getChildren().forEach(node -> {
						if (node instanceof Circle) {
							((Circle) node).setFill(Color.RED);
						}
					});
				}
			}
		});

	}

	private void runTheAction() {
//		if (pane.getChildren().get(0) != null && pane.getChildren().get(0) instanceof Polygon) {
//			pane.getChildren().remove(0);
//		}
//		if (startLabel != null)
//			pane.getChildren().remove(startLabel);

		Vertex source = sourceBox.getSelectionModel().getSelectedItem();
		Vertex distination = distinationBox.getSelectionModel().getSelectedItem();
		if (source != null && distination != null) {
			List<Vertex> list = dijkstra.getShortestPath(source, distination);
			if (list.isEmpty())
				textArea.setText("There is no path from " + source.getLocation() + " to " + distination.getLocation());
			else {
				StringBuilder s = new StringBuilder();
				for (int i = list.size() - 1; i >= 0; i--) {
					s.append("-->").append(list.get(i)).append("\n");
				}
				textArea.setText(s.toString());
				textField.setText(String.format("%.3f", dijkstra.getCost(distination)) + " Km");
				if (!Linelist.isEmpty()) {
					for (Line line : Linelist)
						pane.getChildren().remove(line);
				}
				Linelist = new ArrayList<>();
//				int c = 0;
				for (int i = 0; i < list.size() - 1; i++) {
					 line = new Line();
					line.setStroke(Color.BLACK);
					line.setStrokeWidth(3);

					double x1 = (maxWindowX * (list.get(i).getLongitude() - minX)) / (maxX - minX);
					double y1 = (maxWindowY * (list.get(i).getLatitude() - minY)) / (maxY - minY);
					double x2 = (maxWindowX * (list.get(i + 1).getLongitude() - minX)) / (maxX - minX);
					double y2 = (maxWindowY * (list.get(i + 1).getLatitude() - minY)) / (maxY - minY);
					line.setStartX(x1);
					line.setStartY(y1);
					line.setEndX(x2);
					line.setEndY(y2);


					line.setOnMouseClicked(e ->clearMap());
//					glowObject(line);
					pane.getChildren().add(line);
					Linelist.add(line);

				}
//				startLabel = new Label("Start");
//				startLabel.setStyle("-fx-font-size:20");
//				double x = (maxWindowX * (source.getLongitude() - minX)) / (maxX - minX);
//				double y = (maxWindowY * (source.getLatitude() - minY)) / (maxY - minY);
//				startLabel.setLayoutX(x - 10);
//				startLabel.setLayoutY(y - 30);
//				pane.getChildren().add(startLabel);
			}
		}

		if(dijkstra.getCost(distination)>10000){
			textArea.setText("no path");
			textField.setText("");
		}
	}

//	void glowObject(javafx.scene.Node o) {
//		DropShadow glow = new DropShadow();
//		glow.setWidth(5);
//		glow.setHeight(5);
//		glow.setRadius(5);
//		glow.setSpread(0.2);
//		glow.setColor(Color.BLACK); // Neon color
//		o.setEffect(glow);
//
//	}
//
//	DropShadow glow(Color color) {
//		DropShadow glow = new DropShadow();
//		glow.setWidth(25);
//		glow.setHeight(25);
//		glow.setRadius(15);
//		glow.setSpread(0.5);
//		glow.setColor(color); // Neon color
//		return glow;
//
//	}

	void clearMap() {
		if (!Linelist.isEmpty()) {
			for (Line line : Linelist)
				pane.getChildren().remove(line);
		}
		lock=0;
		readFromFile();

	}

}
