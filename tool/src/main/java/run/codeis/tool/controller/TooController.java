package run.codeis.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.codeis.tool.util.Base64Util;

/**
 * @author liubinqiang
 * @date 2022-3-3
 */
@RequestMapping("/tool")
@RestController
public class TooController {

	@RequestMapping("/base64Encode")
	public String base64Encode(String str) {
		return Base64Util.encode(str);
	}

}
