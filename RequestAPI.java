
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

public class RequestAPI {

	public static void implementation() {

		try {
			URL url = new URL("http://dadosabertos.almg.gov.br/ws/deputados/em_exercicio?formato=json");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Information.json")));

			while (br.readLine() != null) {
				bw.write(br.readLine() + "\n");
			}
			
			bw.close();
			conn.disconnect();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
