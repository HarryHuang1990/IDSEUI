import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String cmd = "xdg-open /home/harry/idse/data/test/Where to Find Help When You Meet An Emergency_wjh_2.docx";///WAIM14_BigEM_4_wjh_1.pptx";
//		xdg-open /home/harry/idse/data/test/WAIMÐû½²/WAIM14_BigEM_4_wjh_1.pptx
		String[] cmds = {"xdg-open",new String("/home/harry/idse/data/test/WAIMÐû½²".getBytes("UTF-8"), "GBK")};
		try {
			Process process = Runtime.getRuntime().exec(cmds);
			process.waitFor();
			if(process.exitValue() != 0){
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String line;
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
