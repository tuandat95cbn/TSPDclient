import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



import com.google.gson.Gson;

public class Main2 {

	public static void main(String[] args) {
		File folder = new File("tspd/");
		File solution = new File("sol_solTSPD.txt");
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(solution);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			try {
				Scanner scaner = new Scanner(files[i]);
				String sol = scaner.nextLine();
				Gson gson = new Gson();
				System.out.println(sol);
				TSPDSolution tspSOL = gson.fromJson(sol, TSPDSolution.class);
				Tour[] tour = tspSOL.getTours();
				String str = String.format("%10.2f %10.2f %10.2f %10.2f",
						tour[0].getTotalCost(),
						tour[1].getTotalCost(), tour[2].getTotalCost(),
						tour[3].getTotalCost());
				if(i==files.length-1) printWriter.write("["+str+"]\n");
				else
				printWriter.write("["+str+"],\n");
				
				scaner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printWriter.close();
	}

}
