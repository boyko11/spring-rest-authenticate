package com.app.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.app.client.interceptor.AuthenticationInterceptor;
import com.app.model.Donkey;

public class RestDonkeyClient {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		ClientHttpRequestInterceptor authenticationInterceptor = new AuthenticationInterceptor();
		restTemplate.setInterceptors(Collections.singletonList(authenticationInterceptor));
		
		//GET
		Donkey donkey = restTemplate.getForObject(
				"http://localhost:8080/spring-rest-authenticate/donkey/{id}",
				Donkey.class, "1");
		System.out.println("retrived donkey: " + donkey.getName());
		
		//PUT (Update)
		Map<String, String> updateParams = new HashMap<String, String>();
		updateParams.put("id", "1");
		updateParams.put("name", "UpdatedDonkeyName");
		updateParams.put("temper", donkey.getTemper());
		updateParams.put("age", String.valueOf(donkey.getAge()));
		restTemplate.put("http://localhost:8080/spring-rest-authenticate/donkey/update", updateParams);
		String updatedDonkey = restTemplate.getForObject(
						"http://localhost:8080/spring-rest-authenticate/donkey/{id}",
						String.class, "1");
		System.out.println("Update donkey: " + updatedDonkey);
		
		//POST (Create)
		Donkey newDonkey = new Donkey();
		newDonkey.setId("78");
		newDonkey.setName("SuperNewDonkey");
		newDonkey.setTemper("Laid-Back");
		newDonkey.setAge(7);
		String createdDonkey = restTemplate.postForObject(
				"http://localhost:8080/spring-rest-authenticate/donkey/new",
				newDonkey, String.class);
		System.out.println("created donkey: " + createdDonkey);
		createdDonkey = restTemplate.getForObject(
				"http://localhost:8080/spring-rest-authenticate/donkey/{id}",
				String.class, "78");
		System.out.println("Just to make sure the new donkey is there:" + createdDonkey);
		
		//DELETE
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("id", "78");
		restTemplate.delete("http://localhost:8080/spring-rest-authenticate/donkey/delete/{id}", vars);
		System.out.println("Deleted the donkey");
		String deletedDonkey = restTemplate.getForObject(
				"http://localhost:8080/spring-rest-authenticate/donkey/{id}",
				String.class, "78");
		System.out.println("Just to make sure the new donkey is NO LONGER there:" + deletedDonkey);
		
	}

}
