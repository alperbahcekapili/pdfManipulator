package pdfOperations.pdfOps;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDPageTree;
/**
 * Inserter
 */
public class Inserter extends basicOps {
    public static void insertInto(File mainFile, File scndFile, File writeFile, int index) throws IOException, IllegalArgumentException{
        
        PDDocument mainDoc = PDDocument.load(mainFile);
        PDDocument scndDoc = PDDocument.load(scndFile);
        
        PDPageTree mainPages = mainDoc.getPages();
        PDPageTree scndPages = scndDoc.getPages();
        
        
        int firstFileSize = mainDoc.getNumberOfPages();
        int scndFileSize = scndDoc.getNumberOfPages();
        String file_path = mainFile.getAbsolutePath();




        boolean isWritingScnd = false;
        if(index < 1  || index > (firstFileSize))
            throw new IllegalArgumentException("index must be inside 1-mainFilePageNumber");
        PDDocument newDoc = new PDDocument();
        for(int i = 0;i<firstFileSize;i++){
            System.out.println("ilkten ekliom" + i);

            if(index-1 == i)
                isWritingScnd = true;

            if(isWritingScnd){
                for(int j = 0;j<scndFileSize;j++){
                    newDoc.addPage(scndPages.get(j));
                    System.out.println("ikinciden ekliyom" + j);
                }
                isWritingScnd = false;
            }

            newDoc.addPage(mainPages.get(i));

        }
        newDoc.save(file_path + "_" + "inserted" + ".pdf");
        mainDoc.close();
        scndDoc.close();
        newDoc.close();
        

    }
    
}