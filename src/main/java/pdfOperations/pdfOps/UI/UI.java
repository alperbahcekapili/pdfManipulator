
package pdfOperations.pdfOps.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Sphere;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pdfOperations.pdfOps.basicOps;
import pdfOperations.pdfOps.visualizePDF;

public class UI extends Application implements Runnable{


    
    double mousePosX = -1;
    double mousePosY = -1;
    FileChooser fileChooser = new FileChooser();
    

    public static Stage primaryStage = null;


    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        System.out.println("start||||||||||||||||||||||||||<<");
        primaryStage.setTitle("PDF Manipulator");
        
        
        Image im2 = visualizePDF.saveAsImage(basicOps.getReadFile("C:\\Users\\Lenovo\\Desktop\\test\\b_2.pdf"))[0];
        ImageView view2 = new MyImageView(im2);
        

        

        




        
        Pane pane = new Pane();
        pane.getChildren().addAll(view2, new resimEkleButton());
        
       

        primaryStage.setScene(new Scene(pane, 800, 1000));
        view2.relocate(100, 100);


        primaryStage.show();
    }
    @Override
    public void run() {
        Application.launch(UI.class, new String[]{});
        
    }

    public static void resimEkle(){
        
    }

}

class resimEkleButton extends Button{

    final FileChooser fileChooser = new FileChooser();
    static File imageFile = null;


    public resimEkleButton(){
        this.setText("Resim Ekle");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
                fileChooser.setTitle("Open Image File");
                File imageFile = fileChooser.showOpenDialog(UI.primaryStage);
                resimEkleButton.imageFile =  imageFile;
                System.out.println(imageFile.getAbsolutePath());
                
            }
        });
    }
}


class pdfAcButton extends Button{
    
    
    final FileChooser fileChooser = new FileChooser();
    
    static File pdfFile = null;

    private ScrollPane createScrollPane(File f) throws Exception{
        
        Image[] pageImages = visualizePDF.saveAsImage(f);
        ScrollPane pane = new ScrollPane();
        HBox box = new HBox();
        for(int i = 0;i< pageImages.length;i++){
            box.getChildren().add(new pdfPagePane(pageImages[i]));
        }
        pane.setContent(box);
        return pane;
        
    }

    

    public pdfAcButton(){
        this.setText("PDF Ac");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
                fileChooser.setTitle("Open PDF File");
                File pdfFile = fileChooser.showOpenDialog(UI.primaryStage);
                pdfAcButton.pdfFile =  pdfFile;
                System.out.println(pdfFile.getAbsolutePath());
                
            }
        });
    }
}


class pdfPagePane extends Pane{

    MyImageView pdfImage;

    public pdfPagePane(Image image){
        
        pdfImage = new MyImageView(image);
        this.getChildren().add(pdfImage);

    }
}

class MyImageView extends ImageView {

    boolean isChosen = false;
    boolean isDragging = false;
    boolean isResizing = false;


    double mousePosX = -1;
    double mousePosY = -1;

    public MyImageView(Image image){
        
        super(image);
        

        this.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){

            
            @Override
            public void handle(MouseEvent event) {

                double x = event.getSceneX();
                double y = event.getSceneY();

                if(mousePosX==-1){
                    mousePosX = x;
                    mousePosY = y;
                }
                else{

                    
                    MyRelocate(x, y);
                    //System.out.println("degisim: " + (x-mousePosX)+"," + (y-mousePosY));
                    mousePosX=x;
                    mousePosY=y;
                }
                
            }
            
            
        });
        
    
 


    }
    boolean isInPositions(double x, double y){
        return this.contains(x, y);

    }

    void MyRelocate(double x, double y){

       File f1;
    try {
        f1 = basicOps.getReadFile("C:\\Users\\Lenovo\\Desktop\\test\\b.pdf");
        PDDocument doc = PDDocument.load(f1);
        PDPage page = doc.getPage(0);
        double height = basicOps.getHeightOfPage(page);

        f1 = basicOps.getReadFile("C:\\Users\\Lenovo\\Desktop\\test\\b_2.pdf");
        doc = PDDocument.load(f1);
        page = doc.getPage(0);
        double width = basicOps.getWidthOfPage(page);


        System.out.println("Height of b: " + height + ", width of page: " + width);

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       
        this.setX(this.getX() + x-mousePosX );
        this.setY(this.getY() +y- mousePosY );

    }
}