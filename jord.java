import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Word extends Application {

    TextArea textArea;
    Font x;
    public Spinner<Integer> spinner;

    public void start(Stage primaryStage) {

        textArea = new TextArea();
        textArea.setLayoutX(27);
        textArea.setPrefSize(400,400);

        Menu menu = new Menu("Fonts");
        MenuItem menuItem1 = new MenuItem("Times New Roman");
        menuItem1.setOnAction(this::processButtonOne);
        MenuItem menuItem2 = new MenuItem("Item 2");


        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);

        Menu menu3 = new Menu("FontSize");

        //Slider slider = new Slider(0, 100, 50);
        spinner = new Spinner(0, 100, 12);

        CustomMenuItem customMenuItem = new CustomMenuItem();
        customMenuItem.setContent(spinner);
        customMenuItem.setHideOnClick(false);
        menu3.getItems().add(customMenuItem);

        Button button = new Button("Change Font Size");
        CustomMenuItem customMenuItem2 = new CustomMenuItem();
        customMenuItem2.setContent(button);
        customMenuItem2.setHideOnClick(false);
        menu3.getItems().add(customMenuItem2);

        customMenuItem2.setOnAction(this::changeSize);


        Menu menu1 =  new Menu("File");

        MenuItem menuSaveAs = new MenuItem("Save As");
        menuSaveAs.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if(file != null){
                SaveAs(textArea.getText(), file);
            }
        });
        menu1.getItems().add(menuSaveAs);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu3);

        VBox vb = new VBox(menuBar, textArea);
        primaryStage.setTitle("JORD");
       
        Scene scene = new Scene(vb, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void processButtonOne(ActionEvent event) {
        textArea.setFont(Font.font("Times New Roman"));
    }
    public void SaveAs(String content, File file) {
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Word.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void changeSize(ActionEvent event) {
        Spinner<Integer> mySpinner = (Spinner<Integer>) spinner;
        textArea.setFont(Font.font(mySpinner.getValue()));
    }
}
