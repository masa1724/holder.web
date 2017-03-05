package holder.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XMLSample {
	@RequestMapping(value = "/xml1", produces = MediaType.APPLICATION_XML_VALUE)
	public String xml1() {
		return "<a><b>content</b></a>";
	}
}
