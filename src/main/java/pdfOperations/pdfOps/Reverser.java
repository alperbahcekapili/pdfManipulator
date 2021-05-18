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


public class Reverser extends pdfOps{
    public static void Reverse(File f) throws IllegalArgumentException, IOException{
        PDDocument mainDoc = PDDocument.load(f);
        int fileSize = mainDoc.getNumberOfPages();
        PDPageTree pages = mainDoc.getPages();
        PDDocument newFile =  new PDDocument();
        for(int i = 0;i<fileSize;i++){
            newFile.addPage(pages.get(fileSize - i - 1));
        }
        newFile.save(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf("\\")) + "\\"+
        f.getName().substring(0,f.getName().length()-4) + 
        "_reversed.pdf" );
        newFile.close();
        System.out.println("A copy of updated file is saved...");
    }
}