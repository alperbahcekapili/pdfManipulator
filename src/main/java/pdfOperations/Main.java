package pdfOperations;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;


import pdfOperations.pdfOps.*;

import static pdfOperations.pdfOps.basicOps.getReadFile;

import java.nio.file.*;

public class Main extends Thread{

    public String getGreeting() {
        return "Hello world.";
    }

    static private void Merge() throws IOException{
        System.out.println("Please put the files you want to merge to under same directory. \n Directory must contain only pdf files which you want to merge. ");;
        System.out.println("Please write the full path for the directory");
        Scanner k = new Scanner(System.in);
        String path_to_dir = k.nextLine();
        
        if(path_to_dir.equals("q"))
            return;
        if(path_to_dir.charAt(path_to_dir.length()-1) != '\\')
            path_to_dir=path_to_dir+"\\";    
        
        
        File directory = basicOps.getWriteFile(new String[]{path_to_dir + "a.pdf"})[0];
        String file_list[] = new File(path_to_dir).list();
        for(int i = 0;i<file_list.length;i++)
            file_list[i] = path_to_dir+ "\\" + file_list[i];
        
        File[] readFiles  = basicOps.getReadFile(file_list);
        Merger.mergePDFfiles(directory,readFiles);

        

    }

    static private void Split() throws IOException{
        System.out.println("Please write full path of the file you want to split. For example:\n C:\\Users\\Ahmet\\splitme.pdf");
        Scanner k = new Scanner(System.in);
        String path = k.nextLine();
        if(path.equals("q"))
            return;
        System.out.println("Please give a number to split from. \t second file will start from given page\n Give 0 to split each page individually");
        int index = k.nextInt();
        File f = basicOps.getReadFile(new String[]{path})[0];
        Splitter.splitPDF(f, index);
       


    }

    private static void Help(){
        System.out.println("This tool helps orginizing and manipulating pdf files TOTALLY FREE!!! ");
        System.out.println("Written by Alper Bahcekapili, a cs student from TOBB ETU. \n\n\n "+
        "\n\n\n ___  ______ "+" \n"+
        " / _ \\ | ___ \\"+" \n"+
       "/ /_\\ \\| |_/ /"+" \n"+
       "|  _  || ___\\"+" \n"+
       "| | | || |_/ /"+" \n"+
       "\\_| |_/\\____/ ");





        System.out.println("\n\n\n\n1- Merge Files");
        System.out.println("If you have 2 or more pdf files you can merge them in any ordering...");
        System.out.println("Before going, you should put the pdf files you want to merge to same folder. \nFolder should not contain any other things");
        System.out.println("Then you will be asked to write full path of that folder");
        System.out.println("After following instructions a pdf called 'a.pdf' will be created under the same folder. ");
        System.out.println("But before merging folder can not contain a.pdf file!");
        
        
        
        System.out.println("\n\n\n\n2- Split a file"+
        "\n This Option will take a pdf file, and create new pdf files from each page. "+
        "\n New files will be created under the same folder with the pdf file at the beginning "+
        "\n New files will be named as follows:"+
        "\n     let name of the file at the beginning is a.pdf "+
        "\n     new files are going to be named as a_1.pdf, a_2.pdf, a_3.pdf ..."+
        "\n ! At the beginning folder should not contain files a_1.pdf, a_2.pdf ....");




        System.out.println("\n\n\n\n3- Insert a file"+
        "\n This option allows you to insert a file into another from desired page"+
        "\n For example:"+
        "\n Let a.pdf is 5 pages, b.pdf is 2 pages and we want to insert b.pdf into a.pdf from 4th page"+
        "\n This means new file will be created as follows:"+
        "\n a1, a2, a3, b1, b2, a4, a5");



        System.out.println("\n\n\n\n4- Delete Interval"+
        "\n This option allows you to delete a page interval"+
        "\n New file will not contain the pages at borders and between."
        );


        System.out.println("\n\n\n\n5- Swap two pages"+
        "\n This option allows you to swap two pages in a file");


        System.out.println("\n\n\n\n6- Rotate pages clockwise"+
        "\n This option allows you to rotate all pages of a file with desired angle"
        );


        System.out.println("\n\n\n\n7- Reverse page ordering"+
        "\n This option allows you to reverse order a file "
        );
    }


    private static void Insert() throws IOException{
        Scanner k = new Scanner(System.in);
        System.out.println("Please write full path of main file...");
        String main_file = k.nextLine();
        if(main_file.equals("q"))
            return;
        System.out.println("Please write the full path of the file you want to insert into main file...");
        String scnd_file = k.nextLine();
        System.out.println("Please write  the index you want to insert to...");
        int index = k.nextInt();

        File[] readFile = basicOps.getReadFile(new String[]{scnd_file});
        File[] readMainFile = basicOps.getReadFile(new String[]{main_file});
        File[] writeFile = basicOps.getWriteFile(new String[]{main_file + "_inserted.pdf"});
        try {
            Inserter.insertInto(readMainFile[0], readFile[0], writeFile[0], index);
        } catch (Exception e) {
            System.out.println("A problem occured during the execution...");
            e.printStackTrace();
        }

    }

    private static void Delete() throws IOException{
        
        System.out.println("Please write the full path of the file you want to modify...");
        Scanner k = new Scanner(System.in);
        String filePath = k.nextLine();
        if(filePath.equals("q"))
            return;
        System.out.println("Please write the interval you want to delete. For example 3-5, give i-i for deletin one page");
        System.out.println("For input i-j, file will not contain i,j");
        String interval = k.nextLine();
        int start = -1;
        int end = -1;
        try {
            
            start = Integer.parseInt(interval.substring(0, interval.indexOf("-")));
            end = Integer.parseInt(interval.substring(interval.indexOf("-")+1));

        } catch (Exception e) {
            System.out.println("Given arguments should be formed int-int");
            return;
        }

        
        if(start>0 && end>0){
            
            File f = basicOps.getReadFile(new String[]{filePath})[0];
            

            try {
                Deleter.deleteInterval(f, start, end);
            } catch (Exception e) {
                System.out.println("Some problem occured during execution...");
                e.printStackTrace();
            }

        }

    }

    private static void Swap() throws Exception{
        System.out.println("Please write the full path of the file you want to modify...");
        Scanner k = new Scanner(System.in);
        String filePath = k.nextLine();
        if(filePath.equals("q"))
            return;

        File f = basicOps.getReadFile(filePath);
        System.out.println("Write 1st index");
        int i1 = k.nextInt();
        System.out.println("Write 2nd index");
        int i2 = k.nextInt();

        Swapper.swapPages(f, i1, i2);



    }
    private static void Reverse()throws Exception{
        System.out.println("Please write the full path of the file you want to modify...");
        Scanner k = new Scanner(System.in);
        String filePath = k.nextLine();
        if(filePath.equals("q"))
            return;

        File f = basicOps.getReadFile(filePath);

        Reverser.Reverse(f);
    }
    private static void Rotate()throws Exception{
        System.out.println("Please write the full path of the file you want to modify...");
        Scanner k = new Scanner(System.in);
        String filePath = k.nextLine();
        if(filePath.equals("q"))
            return;

        File f = basicOps.getReadFile(filePath);
        System.out.println("Please write the degrees you want to rotate clockwise...");
        Rotater.Rotate(f, k.nextInt());

    }

    

    public void run() {

        Scanner k = new Scanner(System.in);
        int option =11;
        
        while(option != 9){
            System.out.println("\n\n\n\n");
            System.out.println("Which operation do you want to make ? "+
            "\n 0- Help"+
            "\n 1- Merge Files"+ 
            "\n 2- Split a file"+ 
            "\n 3- Insert a file"+ 
            "\n 4- Delete a page interval"+ 
            "\n 5- Swap pages"+ 
            "\n 6- Rotate pages"+ 
            "\n 7- Reverse Ordering"+ 
            "\n 8- Empty"+
            "\n 9- Exit");
            try {
                option = k.nextInt();
            } catch (Exception e) {
                option = 11;
            }
            k.nextLine();
            try {
                switch (option) {
                    case 0:
                        
                        Help();
                        break;
                    case 1:
                        System.out.println("\n Going to merge option. Give 'q' as input to go back...\n");
                        Merge();
                        break;
                    case 2:
                        System.out.println("\n Going to Split option. Give 'q' as input to go back...\n");
                        Split();
                        break;
                    case 3:
                        System.out.println("\n Going to Insert option. Give 'q' as input to go back...\n");
                        Insert();
                        break;
                    case 4:
                        System.out.println("\n Going to Delete option. Give 'q' as input to go back...\n");
                        Delete();
                        break;
                    case 5:
                        System.out.println("\n Going to Swap option. Give 'q' as input to go back...\n");
                        Swap();
                        break;
                    case 6:
                        System.out.println("\n Going to Rotate option. Give 'q' as input to go back...\n");
                        Rotate();
                        break;
                    case 7:
                        System.out.println("\n Going to Reverse option. Give 'q' as input to go back...\n");
                        Reverse();
                        break;
                    case 8:
                        System.out.println("In progress");
                        break;
                    case 9:
                        componentAdder.doIt();
                        System.out.println("See you later");
                        break;
                    case 10:
                        Scanner k2 = new Scanner(System.in);
                        String path = k2.next();
                        File f = basicOps.getReadFile(path);
                        visualizePDF.saveAsImage(f);

                    default:
                        System.out.println("You entered a wrong key. Please repeat...");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        
        

        
        
    }
}
