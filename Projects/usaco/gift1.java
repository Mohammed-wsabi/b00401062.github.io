/*
ID: rombin82
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("gift1.in"));
		PrintWriter stdout = new PrintWriter(new File("gift1.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int name_cnt = in.nextInt();
		List<String> names = new ArrayList<String>(name_cnt);
		Map<String, Integer> table = new HashMap<String, Integer>(name_cnt);
		for (int i = 0; i < name_cnt; i++) {
			String name = in.next();
			names.add(name);
			table.put(name, 0);
		}
		for (int i = 0; i < name_cnt; i++) {
			String gvr_name = in.next();
			int money = in.nextInt();
			int rcv_cnt = in.nextInt();
			if (rcv_cnt == 0)
				continue;
			table.put(gvr_name, table.get(gvr_name) - money / rcv_cnt * rcv_cnt);
			for (int j = 0; j < rcv_cnt; j++) {
				String rcv_name = in.next();
				table.put(rcv_name, table.get(rcv_name) + money / rcv_cnt);
			}
		}
		for (String name : names)
			stdout.println(name + " " + table.get(name));
	}
}
