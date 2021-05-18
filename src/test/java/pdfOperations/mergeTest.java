package pdfOperations;

import org.junit.Test;
import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;


public class mergeTest{

    /*
    @Test public void createsFile() {
        
        String strs[] = {"./src/test/resources/test1.pdf", "./src/test/resources/test2.pdf"} ;
        try {
            Merger.mergePDFfiles("./src/test/resources/a.pdf", strs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        File dir = new File("./src/test/resources/a.pdf");
        boolean fileCreated = dir.exists();
        assertTrue(fileCreated);
        
    }
    */

    /*@Test public void throwsWhenNoFileProvided(){

        String strs[] = {};
        assertThrows(IllegalArgumentException.class, ()->{Merger.mergePDFfiles("./src/test/resources", strs);});

    }

   /* @Test public void throwsWhenNotExistingFileProvided(){

        String strs[] = {"./src/test/resources/test1.pdf", "./src/test/resources/test2.pdf", "./src/test/resources/doNotExist.pdf"} ;
        assertThrows(IOException.class, ()->{Merger.mergePDFfiles("./src/test/resources", strs);});

    }

    /*
    @Test public void throwsWhenNoAccessToDestiny(){
        String strs[] = {"./src/test/resources/test1.pdf", "./src/test/resources/test2.pdf"} ;
        assertThrows(IOException.class, ()->{Merger.mergePDFfiles("C:/Users/Lenovo/prohibited/a.pdf", strs);});
    }
    */
    /*@Test public void throwsWhenDestinyAlreadyExists(){
        String strs[] = {"./src/test/resources/test1.pdf", "./src/test/resources/test2.pdf"} ;
        assertThrows(IllegalArgumentException.class, ()->{Merger.mergePDFfiles("./src/test/resources/test2.pdf", strs);});
    }
    @Test public void throwsWhenNoAccessToSource(){
        String strs[] = {"./src/test/resources/test1.pdf", "C:/Kullanıcılar/Lenovo/prohibitedd.pdf",  "./src/test/resources/test2.pdf"} ;
        assertThrows(IOException.class, ()->{Merger.mergePDFfiles("./src/test/resources", strs);});
    }
    */
}