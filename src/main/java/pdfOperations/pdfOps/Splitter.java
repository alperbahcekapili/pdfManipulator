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
/**
 * Splitter
 */
public class Splitter extends pdfOps{

    static void splitPDF(File f, int from) throws IOException{
        
        

        PDDocument document = PDDocument.load(f);
        int num_pages = document.getNumberOfPages();
        String file_path = f.getAbsolutePath();
        PDPageTree docTree = document.getPages();
        if(from == 0){
            for(int i = 0;i<num_pages;i++){
                PDDocument tempDoc = new PDDocument();
                pdfOps.getWriteFile(new String[]{file_path.substring(0, file_path.length()-4) + "_" + (i+1) + ".pdf"});
                tempDoc.addPage(docTree.get(i));
                tempDoc.save(file_path.substring(0, file_path.length()-4) + "_" + (i+1) + ".pdf");
                tempDoc.close();
            }
        }else if(from < num_pages + 1 && from > 0){
            PDDocument doc1 = new PDDocument();
            for(int i = 0;i<from -1 ;i++){
                pdfOps.getWriteFile(new String[]{file_path.substring(0, file_path.length()-4) + "_" + 1 + ".pdf"});
                doc1.addPage(docTree.get(i));
            }
            doc1.save(file_path.substring(0, file_path.length()-4) + "_" + 1 + ".pdf");
            doc1.close();

            PDDocument doc2 = new PDDocument();
            for(int i = from;i<num_pages+1;i++){
                pdfOps.getWriteFile(new String[]{file_path.substring(0, file_path.length()-4) + "_" + (i-1) + ".pdf"});
                doc2.addPage(docTree.get(i -1));
            }
            doc2.save(file_path.substring(0, file_path.length()-4) + "_" + 2 + ".pdf");
            doc2.close();
        }   
        System.out.println("Files have been created successfully");
        document.close();
    
    }
    
}