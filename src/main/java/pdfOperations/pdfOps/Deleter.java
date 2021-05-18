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
/**
 * Deleter
 */
public class Deleter extends pdfOps {
    static void deleteInterval(File f, int start, int end) throws IOException, IllegalArgumentException{
        PDDocument mainDoc = PDDocument.load(f);
        int fileSize = mainDoc.getNumberOfPages();
        if(start < 1  || start > fileSize || end < 1  || end > fileSize || end<start)
            throw new IllegalArgumentException(" Incorrect indexes");
        PDDocument newFile =  new PDDocument();
        for(int i = 0;i<fileSize;i++){
            if(i==start-1){
                i = end-1;
                continue;
            }
            newFile.addPage(mainDoc.getPages().get(i));

        }
        newFile.save(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf("\\")) + "\\"+
        f.getName().substring(0,f.getName().length()-4) + 
        "_updated.pdf" );
        newFile.close();
        System.out.println("A copy of updated file is saved...");
    }
    
}