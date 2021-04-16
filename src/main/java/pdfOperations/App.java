package pdfOperations;

import java.io.File;
import java.util.Scanner;

import java.net.URL;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    static private void Merge(){
        System.out.println("Please put the files you want to merge to under same directory. \n Directory must contain only pdf files which you want to merge. ");;
        System.out.println("Please write the full path for the directory");
        Scanner k = new Scanner(System.in);
        String path_to_dir = k.nextLine();
        if(path_to_dir.equals("q"))
            return;
        path_to_dir = path_to_dir.replace("\\", "\\\\");
        File directory = new File(path_to_dir);
        System.out.println(path_to_dir + "\n");
        if(!directory.exists() || !directory.isDirectory()){
            System.out.println("Directory do not exists. Aborting");
            return;
        }
        


    String file_list[] = directory.list();
    for(int i = 0;i<file_list.length;i++){
    
        if(!file_list[i].substring(file_list[i].length()-4).equals(".pdf")){
            System.out.println("Directory must be formed from pdf files...");
            return;
        }
        file_list[i] = directory.getAbsolutePath()+ "\\" + file_list[i];
    
    }
    try {
        pdfOps.mergePDFfiles(directory.getAbsolutePath()+ "\\a.pdf",file_list );

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    }

    static private void Split(){
        System.out.println("Please write full path of the file you want to split. For example:\n C:\\Users\\Ahmet\\splitme.pdf");
        Scanner k = new Scanner(System.in);
        String path = k.nextLine();
        if(path.equals("q"))
            return;
        File f = new File(path);

        if(!f.exists()){
            System.out.println("File do not exist at : " + path);
            return;
        }

        if(!f.canRead()){
            System.out.println("Do not have permmission to read the file: " +path);
            return;
        }

        try {
            pdfOps.splitPDF(f);
        } catch (Exception e) {
            System.out.println("Some problem occured... ");
            e.printStackTrace();
        }


    }

    private static void Help(){
        System.out.println("This tool helps orginizing and manipulating pdf files TOTALLY FREE!!! ");
        System.out.println("Written by Alper Bahcekapili, a cs student from TOBB ETU. \n\n\n "+
        " ___  ______ "+" \n"+
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




        System.out.println("\n\n\n\n"+
        "\n This option allows you to insert a file into another from desired page"+
        "\n For example:"+
        "\n Let a.pdf is 5 pages, b.pdf is 2 pages and we want to insert b.pdf into a.pdf from 4th page"+
        "\n This means new file will be created as follows:"+
        "\n a1, a2, a3, b1, b2, a4, a5");
    }

    private static void Insert(){
        Scanner k = new Scanner(System.in);
        System.out.println("Please write full path of main file...");
        String main_file = k.nextLine();
        if(main_file.equals("q"))
            return;
        System.out.println("Please write the full path of the file you want to insert into main file...");
        String scnd_file = k.nextLine();
        System.out.println("Please write  the index you want to insert to...");
        int index = k.nextInt();

        File mainFile = new File(main_file);
        if(!mainFile.exists()){
            System.out.println("File do not exist at : " + main_file);
            return;
        }

        if(!mainFile.canRead()){
            System.out.println("Do not have permmission to read the file: " +main_file);
            return;
        }

        File scndFile = new File(scnd_file);
        if(!scndFile.exists()){
            System.out.println("File do not exist at : " + scnd_file);
            return;
        }

        if(!scndFile.canRead()){
            System.out.println("Do not have permmission to read the file: " +scnd_file);
            return;
        }

        try {
            pdfOps.insertInto(mainFile, scndFile, index);
        } catch (Exception e) {
            System.out.println("A problem occured during the execution...");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        Scanner k = new Scanner(System.in);
        int option =11;
        
        while(option != 9){
            System.out.println("\n\n\n\nFor now only 1-3 operations are working :(");
            System.out.println("Which operation do you want to make ? "+
            "\n 0- Help"+
            "\n 1- Merge Files"+ 
            "\n 2- Split a file"+ 
            "\n 3- Insert a file"+ 
            "\n 4- Delete a page interval"+ 
            "\n 5- Swap pages"+ 
            "\n 6- Rotate pages"+ 
            "\n 7- Reverse Ordering"+ 
            "\n 8- Swap two pages of a file"+
            "\n 9- Exit");
            try {
                option = k.nextInt();
            } catch (Exception e) {
                option = 11;
            }
            k.nextLine();
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
                    System.out.println("In progress");
                    break;
                case 5:
                    System.out.println("In progress");
                    break;
                case 6:
                    System.out.println("In progress");
                    break;
                case 7:
                    System.out.println("In progress");
                    break;
                case 8:
                    System.out.println("In progress");
                    break;
                case 9:
                    System.out.println("See you later");
                    break;
            
                default:
                    System.out.println("You entered a wrong key. Please repeat...");
                    break;
            }
        }

        
        

        
        
    }
}
