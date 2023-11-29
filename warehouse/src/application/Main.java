package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Main extends Application {

    protected TextArea txaMessage;
    protected Button btnStart;
    protected Button btnTerminate;
    protected TextField packageIdTextField;
    protected Button addPackageButton;
    protected Button removePackageButton;
    protected Button checkPackageButton;

    private Deque<Integer> deque = new ArrayDeque<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create controls
            txaMessage = new TextArea();
            txaMessage.setPrefHeight(300);
            txaMessage.setPrefWidth(400);
            btnStart = new Button("Make Shipment");
            btnTerminate = new Button("Terminate Program");

            Label packageIdLabel = new Label("Package ID Number");

            packageIdTextField = new TextField();
            addPackageButton = new Button("Add Package");

            removePackageButton = new Button("Remove Package");

            checkPackageButton = new Button("Check Package");

            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newText = change.getControlNewText();
                if (Pattern.matches("[0-9]*", newText)) {
                    return change;
                }
                return null;
            };

            TextFormatter<String> textFormatter = new TextFormatter<>(filter);
            packageIdTextField.setTextFormatter(textFormatter);

            HBox packageIdBox = new HBox(10);
            packageIdBox.getChildren().addAll(packageIdLabel, packageIdTextField);

            HBox actionButtonBox = new HBox(10);
            actionButtonBox.getChildren().addAll(addPackageButton, removePackageButton, checkPackageButton);

            HBox buttonBox = new HBox(10); // spacing = 10
            buttonBox.getChildren().addAll(btnStart, btnTerminate);

            VBox root = new VBox(10); // spacing = 10
            root.setPadding(new Insets(20)); // padding = 20
            root.getChildren().addAll(packageIdBox, actionButtonBox, buttonBox, txaMessage);
            root.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            btnStart.setOnAction(e -> startProgram());
            btnTerminate.setOnAction(e -> terminateProgram());
            addPackageButton.setOnAction(e -> addPackage());
            removePackageButton.setOnAction(e -> removePackage());
            checkPackageButton.setOnAction(e -> checkPackage());

            Scene scene = new Scene(root, 600, 550);

            primaryStage.setTitle("Package Shipment Management Software");

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startProgram() {
        txaMessage.appendText("---Warehouse Inventory---\n");
		Random rand = new Random();
				int package1 = rand.nextInt(100000);
				int package2 = rand.nextInt(100000);
				int package3 = rand.nextInt(100000);
				deque.add(package1);
				deque.add(package2);
				deque.add(package3);
				txaMessage.appendText("Total Packages: "+ deque.size()+"\n");
				int first = deque.removeFirst();
				int last = deque.removeLast();
				txaMessage.appendText("FIFO: "+first+", LIFO: "+last+"\n");
				txaMessage.appendText("New size of shopcenter: "+deque.size()+"\n");
		}

    private void terminateProgram() {
        txaMessage.appendText("---Program terminated.---\n");
        System.exit(0);
    }

    private void addPackage() {
        int packageId = Integer.parseInt(packageIdTextField.getText());
        if (packageId > 0) {
            deque.addLast(packageId);
            txaMessage.appendText("(+) Added Package " + packageId + " to the back of the list.\n");
        }
    }

    private void removePackage() {
    	int packageId = Integer.parseInt(packageIdTextField.getText());
        if (!deque.isEmpty() && deque.contains(packageId)) {
            deque.remove(packageId);
            txaMessage.appendText("(-) Shipment cancelled; Package " + packageId + " has been removed from the warehouse.\n");
        } else {
            txaMessage.appendText("Warehouse is either empty, or package does not exist.\n");
        }
    }

    private void checkPackage() {
        if (!deque.isEmpty()) {
            int frontPackage = deque.getFirst();
            txaMessage.appendText("Front of the deque contains " + frontPackage + "\n");
        } else {
            txaMessage.appendText("Deque is empty\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}