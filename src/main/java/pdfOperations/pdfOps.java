package pdfOperations;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
import javax.print.attribute.standard.NumberUp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.io.MemoryUsageSetting;
public class pdfOps {
    static void mergePDFfiles(String destiny, String paths[]) throws IOException{

        System.out.println("Checking files...");


        int file_count = paths.length;
        if(file_count==0)
            throw new IllegalArgumentException("No input files provided");

        //check files intended to be merged
        File files[] = new File[file_count];
        for(int i = 0;i<file_count;i++){
            files[i] = new File(paths[i]);
            if(!files[i].exists())
                throw new IOException("File given as parameter: " + i + " do not exist. Given path: " + paths[i] +"\n");
            if(!files[i].canRead())
                throw new IOException("File given as parameter: " + i + " could not be read. Given path: " + paths[i] +"\n");
        }

        //check file to be written
        File f = new File(destiny);


        boolean isPermitted = false;
        
        if(!f.canWrite())
            throw new IOException("Do not have permission to write the file to the location: " + destiny);

        if(f.exists())
            throw new IllegalArgumentException("Destiny already exists:  " + destiny);
        
        
        
        

        
        System.out.println("Starting merging...");
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
		
        PDFmerger.setDestinationFileName(destiny);

        System.out.println("Following files are going to be merged. Please write the ordering you want for files. \n For example: a.pdf\nb.pdf\nc.pdf \n input: 2,1,3  \n"+
            " provided order will be b.pdf\na.pdf\nc.pdf");
        int i = 1;
        for(File file : files){
            System.out.println("" + i + "- " + file.getName());
            i++;
        }
        Scanner k = new Scanner(System.in);
        String orderStr = k.nextLine();
        System.out.println("You entered" +  orderStr);
        String orderArStr[] = orderStr.split(",");
        int orderInt[] = new int[orderArStr.length];
        for( i = 0;i<orderArStr.length;i++){
            try {
                orderInt[i] = Integer.parseInt(orderArStr[i]) -1 ;
            } catch (Exception e) {
                System.out.println("Ordering must contain integers ");
                return;
            }
            if(orderInt[i]>orderArStr.length){
                System.out.println("File numbers and given ordering do not match");
                return;
            }
        }
        for(i = 0;i<orderArStr.length;i++ ){
            PDFmerger.addSource(files[orderInt[i]]);
        }
        PDFmerger.setDestinationFileName(destiny);
        PDFmerger.mergeDocuments();

        System.out.println("Files has been merged succesfully...");




    }
    static void splitPDF(File f) throws IOException{
        
        PDDocument document = PDDocument.load(f);
        int num_pages = document.getNumberOfPages();
        String file_path = f.getAbsolutePath();
        for(int i = 0;i<num_pages;i++){
            PDDocument tempDoc = new PDDocument();
            tempDoc.addPage(document.getPages().get(i));
            tempDoc.save(file_path.substring(0, file_path.length()-4) + "_" + i + ".pdf");
            tempDoc.close();
        }    
        System.out.println("Files have been created successfully");
    
    }

    static void insertInto(File mainFile, File scndFile, int index) throws IOException, IllegalArgumentException{
        
        PDDocument mainDoc = PDDocument.load(mainFile);
        PDDocument scndDoc = PDDocument.load(scndFile);
        int newFileSize = mainDoc.getNumberOfPages() + scndDoc.getNumberOfPages();
        int scndFileSize = scndDoc.getNumberOfPages();
        String file_path = mainFile.getAbsolutePath();

        if(index < 1  || index > newFileSize - scndFileSize)
            throw new IllegalArgumentException("index must be inside 1-mainFilePageNumber");
        PDDocument newDoc = new PDDocument();
        for(int i = 0;i<newFileSize;i++){

            if( i+1 >= index && i+1 < index + scndFileSize){
                newDoc.addPage(scndDoc.getPages().get(i+1-index));
            }else if(i+1>=index){
                newDoc.addPage(mainDoc.getPages().get(i-scndFileSize));               
            }else{
                newDoc.addPage(mainDoc.getPages().get(i));
            }

        }
        newDoc.save(file_path.substring(0, file_path.length()-4) + "_" + "inserted" + ".pdf");
        newDoc.close();
        

    }
    static void printText(String filename) throws IOException {
      //Loading an existing document
      File file = new File(filename);
      PDDocument document = PDDocument.load(file);
      //Instantiate PDFTextStripper class
      PDFTextStripper pdfStripper = new PDFTextStripper();
      //Retrieving text from PDF document
      String text = pdfStripper.getText(document);
      System.out.println(text);
      //Closing the document
      document.close();
   }
}