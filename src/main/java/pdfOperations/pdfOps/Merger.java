
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

class Merger extends pdfOps{
    static void mergePDFfiles(File destiny, File[] files) throws IOException{

        System.out.println("Starting merging...");
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
            if(orderInt[i]>orderArStr.length || orderInt[i]<0){
                System.out.println("File numbers and given ordering do not match");
                return;
            }
        }
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        PDFmerger.setDestinationFileName(destiny.getAbsolutePath());
        for(i = 0;i<orderArStr.length;i++ ){
            PDFmerger.addSource(files[orderInt[i]]);
        }
        PDFmerger.mergeDocuments();
        System.out.println("Files has been merged succesfully...");



    }
}