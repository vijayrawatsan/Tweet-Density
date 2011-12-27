package in.xebia.vijay.controller;

import in.xebia.vijay.domain.DataToReturn;
import in.xebia.vijay.domain.DensityInfoWrapper;
import in.xebia.vijay.domain.TweetDensity;
import in.xebia.vijay.service.DensityService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private DensityService densityService;
	
	@RequestMapping(value = "/tweetdensity", params="type=json", method = RequestMethod.GET)
	public void tweetDensityJson(@RequestParam String handle, @RequestParam int count, HttpServletResponse httpServletResponse) {
		logger.info("tweetDensity");
		DensityInfoWrapper densityInfoWrapper = densityService.getDensity(handle,count);
		DataToReturn dataToReturn = getDataToReturn(densityInfoWrapper);
		if(count > densityInfoWrapper.getCountReturned())
			httpServletResponse.setHeader("API_MESSAGE", "Either TWITTER was not working properly or there are not enough tweets");
		ObjectMapper mapper = new ObjectMapper();
		httpServletResponse.setContentType("application/json");
		try {
			mapper.writeValue(httpServletResponse.getOutputStream(), dataToReturn);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/tweetdensity", params="type=xml", method = RequestMethod.GET)
	public void tweetDensityXml(@RequestParam String handle, @RequestParam int count, HttpServletResponse httpServletResponse) throws JAXBException {
		logger.info("tweetDensity");
		DensityInfoWrapper densityInfoWrapper = densityService.getDensity(handle,count);
		DataToReturn dataToReturn = getDataToReturn(densityInfoWrapper);
		if(count > densityInfoWrapper.getCountReturned())
			httpServletResponse.setHeader("API_MESSAGE", "Either TWITTER was not working properly or there are not enough tweets");
		JAXBContext jc = JAXBContext.newInstance(TweetDensity.class);
		Marshaller m = jc.createMarshaller();
		httpServletResponse.setContentType("text/xml");
		try {
			m.marshal(dataToReturn.getTweetdensity(), httpServletResponse.getOutputStream());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/tweetdensity", params="type=html", method = RequestMethod.GET)
	public String tweetDensityHtml(@RequestParam String handle, @RequestParam int count, Model model) {
		logger.info("tweetDensity");
		DensityInfoWrapper densityInfoWrapper = densityService.getDensity(handle,count);
		model.addAttribute("listOfData", densityInfoWrapper.getDensityData());
		if(count > densityInfoWrapper.getCountReturned())
			model.addAttribute("API_MESSAGE", "Either TWITTER was not working properly or there are not enough tweets");
		return "tweetDensity";
	}
	
	private DataToReturn getDataToReturn(DensityInfoWrapper densityInfoWrapper) {
		DataToReturn dataToReturn = new DataToReturn();
		TweetDensity tweetDensity = new TweetDensity();
		tweetDensity.setData(densityInfoWrapper.getDensityData());
		dataToReturn.setTweetdensity(tweetDensity);
		return dataToReturn;
	}
}

