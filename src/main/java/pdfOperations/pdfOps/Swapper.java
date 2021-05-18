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
import org.apache.pdfbox.pdmodel.PDPageTree;

public class Swapper extends pdfOps {
    public static void swapPages(File f, int i1, int i2)throws IllegalArgumentException, IOException{
        if(i1==i2)
            return;
        PDDocument mainDoc = PDDocument.load(f);
        int fileSize = mainDoc.getNumberOfPages();
        if(i1 < 0  || i2<0 ||i1>fileSize  || i2 > fileSize)
            throw new IllegalArgumentException(" Incorrect indexes");
        PDPageTree pages = mainDoc.getPages();
        PDDocument newFile =  new PDDocument();
        for(int i = 0;i<fileSize;i++){
            if(i+1==i1){
                newFile.addPage(pages.get(i2 - 1));
                continue;
            }
            if(i+1==i2){
                newFile.addPage(pages.get(i1 - 1));
                continue;
            }
            newFile.addPage(pages.get(i));
        }
        newFile.save(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf("\\")) + "\\"+
        f.getName().substring(0,f.getName().length()-4) + 
        "_swapped.pdf" );
        newFile.close();
        System.out.println("A copy of updated file is saved...");
    }
}