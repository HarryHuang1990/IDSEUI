import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/harry/idse/data/test", "Where to Find Help When You Are in An Emergency-20140313-Kain.docx");
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(file.getPath());
		System.out.println(file.length());	//byte
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日， HH时mm分ss秒");
		
		System.out.println(sdf.format(new Date(file.lastModified())));	// mms
		
	}

}
