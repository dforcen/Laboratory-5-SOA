package soa.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q,
    	@RequestParam(value = "numberTweets", required = false) Integer numberTweets) {
    	Map<String,Object> headers = new HashMap<String,Object>();
    	headers.put("CamelTwitterKeywords",q); //Keywords added
	if(numberTweets != null){
	    	headers.put("CamelTwitterCount",numberTweets); //Number of Tweets added
	 }
	return producerTemplate.requestBodyAndHeaders("direct:search", "", headers);
    }
}
