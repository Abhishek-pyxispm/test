package testapi;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.logging.*;

public class Test_GET {
	
	static Logger test_logger() throws IOException {
		
		Logger logger = Logger.getLogger("test logs");
		logger.setLevel(Level.ALL);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		logger.addHandler(ch);
		
		FileHandler fh = new FileHandler("log_file.log", true);
		fh.setFormatter(new SimpleFormatter());
		fh.setLevel(Level.ALL);
		logger.addHandler(fh);
		
	    return logger;
	  }
	@Test
	void test_1() throws IOException 
	{
		Logger log = test_logger();
		Response response = RestAssured.get("https://reqres.in/api/users?page=1");
		System.out.println(response.getStatusCode());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		log.info("test_1 finished.");
		
	}
	
	@Test
	void test_2() throws IOException
	{
		Logger log = test_logger();
		RestAssured.get("https://reqres.in/api/users?page=1").then().statusCode(200);
		log.info("test_2 finished.");
	}
	
	@Test
	void test_3() throws IOException
	{
		Logger log = test_logger();
		RestAssured.get("https://reqres.in/api/users?page=1").then().body("page", equalTo(1));
		log.info("test_3 finished.");
	}
}
