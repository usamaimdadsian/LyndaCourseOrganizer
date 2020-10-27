package courseorganizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CourseOrganizer extends Application {
    Stage window;
    List <File> files;
    ArrayList <File> sortedFiles=new ArrayList();
    ArrayList <String> identifiers=new ArrayList();
    Label prompt;
    @Override
    public void start(Stage primaryStage) {
        this.window=primaryStage;
        prompt=new Label();
        Button btn = new Button("Choose Folder");
        btn.setOnAction(e->{
            FileChooser chooser=new FileChooser();
            files=chooser.showOpenMultipleDialog(window);
            if( files != null)
            {
                
//                folder.toString();                
                for (File f: files)
                {           
                    sortedFiles.add(f);
                    String name=f.getName();
                    System.out.println(name);
//                    System.out.println(name.indexOf("-"));
//                    name.lastIndexOf(".");
                        String n=name.substring(name.length()-11);                        
                        identifiers.add(n.substring(n.indexOf("-")+1, n.lastIndexOf(".")));              
                }
                identifiers.sort(String::compareToIgnoreCase);
//                for (String str: identifiers)
//                {
////                    System.out.println(identifiers.indexOf(str));
//                    System.out.println(str);
//                }
                for (File f: files)
                {
                    String name=f.getName();
//                    System.out.println(name);
                    String n=name.substring(name.length()-11);
                    String comparator=n.substring(n.indexOf("-")+1, n.lastIndexOf("."));
//                    System.out.println(comparator);
                    for(String str: identifiers)
                    {
                        if(comparator.equals(str))
                        {
//                            sortedFiles.add(identifiers.indexOf(str), f);
                            sortedFiles.set(identifiers.indexOf(str), f);
//                            System.out.println(identifiers.indexOf(str));
                        }
                    }
//                    sortedFiles.add(f);
                }
//                System.out.println("NOW WE STARYT TO FUCK");
//                for (File f: sortedFiles)
//                {
//                    String name=f.getName();
//                    System.out.println(name);
//                }
                prompt.setText("Files Extracted");
            }
        });
        
        Button renameBtn=new Button("Rename All files");
        renameBtn.setOnAction(e->{
            int i=0;
            for(File f: sortedFiles)
            {
//                f.renameTo();
//                System.out.println(f.getAbsolutePath());
//                System.out.println("\n\n\n\n\n\n\n");
//                System.out.println(f.getParent());
//                System.out.println("\n\n\n\n\n\n\n");
                ++i;
                File temp=new File(f.getParent()+"\\"+i+"."+f.getName());
//                System.out.println(temp.getName());
                f.renameTo(temp);
            }
            prompt.setText("Operation Successfull");
        });
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.add(btn,0,0);        
        root.add(renameBtn,0,1);
        root.add(prompt, 0, 3);
        Scene scene = new Scene(root, 300, 250);
        
        window.setTitle("Organize your course");
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
