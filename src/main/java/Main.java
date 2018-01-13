import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");
	public static final String serverUrl = "http://localhost:8088/ezRoutingAPI/tsp-with-drone-tspdls-input-distance";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File folder = new File("tspd2/");
		File[] files = folder.listFiles();
		for (int i =0; i <files.length; i++) {
			try {
				System.out.println();
				Scanner scaner = new Scanner(files[i]);
				scaner.next();
				int customerSize = Integer.parseInt(scaner.next());
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.next();
				double droneSpeed = Double.parseDouble(scaner.next());
				scaner.next();
				double truckSpeed = Double.parseDouble(scaner.next());
				scaner.next();
				double truckCost = Double.parseDouble(scaner.next());
				double droneCost = truckCost / 25;
				scaner.next();
				double endurance = Double.parseDouble(scaner.next())*droneSpeed;
				scaner.next();
				double waittingtime = Double.parseDouble(scaner.next());
				System.out.println(files[i].getName() + "--" + customerSize
						+ "--droneSpeed " + droneSpeed + "--truckSpeed "
						+ truckSpeed + "--truckCost " + truckCost
						+ "--droneCost " + droneCost + "--endurance "
						+ endurance + "--waittingtime " + waittingtime);
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				scaner.nextLine();
				List<Point2> lp = new ArrayList<Point2>();
				for (int j = 0; j < customerSize + 1; j++) {
					Point2 p = new Point2(scaner.nextInt(),
							scaner.nextDouble(), scaner.nextDouble(),
							scaner.nextInt() == 0 ? false : true);
					lp.add(p);
				}
				Map<String, Double> distanceMap = new HashMap<String, Double>();
				for (int j = 0; j < lp.size() - 1; j++)
					for (int t = j + 1; t < lp.size(); t++) {
						String key = j + "_" + t;
						distanceMap.put(
								key,
								Math.abs(lp.get(j).getLat()
										- lp.get(t).getLat())
										+ Math.abs(lp.get(j).getLng()
												- lp.get(t).getLng()));
						key = t + "_" + j;
						distanceMap.put(
								key,
								Math.abs(lp.get(j).getLat()
										- lp.get(t).getLat())
										+ Math.abs(lp.get(j).getLng()
												- lp.get(t).getLng()));
					}
				Point2[] lpArr = new Point2[customerSize + 1];
				TSPDRequestwithDistance2 tspdRequestwithDistance2 = new TSPDRequestwithDistance2(
						truckSpeed, droneSpeed, (int) truckCost,
						(int) droneCost, waittingtime, endurance,
						lp.toArray(lpArr), distanceMap);

				OkHttpClient okHttpClient = new OkHttpClient.Builder()
						.connectTimeout(100000, TimeUnit.SECONDS)
						.readTimeout(100000, TimeUnit.SECONDS)
						.writeTimeout(100000, TimeUnit.SECONDS).build();
				Gson gson = new Gson();
				System.out.println(gson.toJson(tspdRequestwithDistance2));
				RequestBody requestBody = RequestBody.create(JSON,
						gson.toJson(tspdRequestwithDistance2));
				Request request = null;
				request = new Request.Builder()
						.header("content-type", "application/json")
						.url(serverUrl).post(requestBody).build();
				Response response = null;
				String resultString = null;
				try {
					response = okHttpClient.newCall(request).execute();
					resultString = response.body().string();
					File file = new File("tspd/" + "sol_" + files[i].getName());
					file.getParentFile().mkdirs();
					PrintWriter printWriter = new PrintWriter(file);
					printWriter.println(resultString);
					printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!response.isSuccessful()) {
					System.out.println("FAIL");
				}
				scaner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
