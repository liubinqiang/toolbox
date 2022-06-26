package run.codeis.tool.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author liubinqiang
 * @date 2022-3-3
 */
public class Base64Util {
	public static String encode(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
	}
}
