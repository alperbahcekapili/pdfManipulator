package pdfOperations;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.nio.file.*;

import javax.lang.model.util.ElementScanner14;
import javax.print.attribute.standard.NumberUp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.io.MemoryUsageSetting;
public class pdfOps {
   
    static File[] getReadFile(String[] paths) throws IOException{

        System.out.println("Checking files...");
        File[] files = new File[paths.length];
        if(files.length==0)
            throw new IllegalArgumentException("No input files provided");
        for(int i = 0;i<files.length;i++){
            files[i] = new File(paths[i]);
            if(!files[i].exists())
                throw new IOException("File given as parameter: " + i + " do not exist. Given path: " + paths[i] +"\n");
            if(!files[i].canRead())
                throw new IOException("File given as parameter: " + i + " could not be read. Given path: " + paths[i] +"\n");
            
            if(!paths[i].substring(paths[i].length()-4).equals(".pdf")){
                throw new IOException("Directory must be formed from pdf files...");
            }

        } 

        return files;
    }

    static File getReadFile(String path) throws IOException{

        System.out.println("Checking files...");
        File file = new File(path);
        if(!file.exists())
            throw new IOException("File given as parameter: " + 0 + " do not exist. Given path: " + path +"\n");
        if(!file.canRead())
            throw new IOException("File given as parameter: " + 0 + " could not be read. Given path: " + path +"\n");
        
        if(!path.substring(path.length()-4).equals(".pdf")){
            throw new IOException("Directory must be formed from pdf files...");
        }

         

        return file;
    }

    static File[] getWriteFile(String[] paths) throws IOException{

                

        //check file to be written
        File[] files = new File[paths.length];
        boolean isPermitted = false;
        
        for(int i = 0;i<files.length;i++){
            files[i] = new File(paths[i]);

            if(!Files.isWritable(Paths.get(paths[i].substring(0, paths[i].lastIndexOf("\\") + 1))))
                throw new IOException("Do not have permission to write the file to the location: " + paths[i]);

            if(files[i].exists())
                throw new IllegalArgumentException("Destiny already exists:  " + paths[i]);
            
        }
        
        return files;

    }
    

    

    static void printText(File[] files) throws IOException {
      
      for(int i = 0;i<files.length;i++){
        PDDocument document = PDDocument.load(files[i]);
        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        System.out.println(text);
        //Closing the document
        document.close();
      }
   }
}