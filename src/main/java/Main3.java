import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x[][]= new double[35][4];
		try {
			Scanner scanner= new Scanner(new File("solutionconvert2.txt"));
			for(int i=0;i<10;i++)
				for(int j=0;j<4;j++)
					x[i][j]=scanner.nextDouble();
			PrintWriter printWriter = new PrintWriter(new File("solutionconvertlatex2.txt"));
			
			
			for(int i=0;i<4;i++){
				String str="";
				for(int j=9;j>=0;j--) str+="("+(9-j)+","+x[j][i]+") ";
				printWriter.println(str);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
