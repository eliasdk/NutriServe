package edu.bzu.soa.nutriserve;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.bzu.soa.nutriserve.service.RecipeService;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NutriServeApplication {

	public static void main(String[] args) {
		
		RecipeService recipeService = new RecipeService();
		try {
			recipeService.getRecipes("Chicken");
			recipeService.getRecipes("Meat");
			recipeService.getRecipes("vegetables");
			recipeService.getRecipes("carrots");
			recipeService.getRecipes("beans");
			recipeService.getRecipes("seeds");
			recipeService.getRecipes("onions");
			recipeService.getRecipes("pizza");
			recipeService.getRecipes("salad");
			recipeService.getRecipes("tuna");
			recipeService.getRecipes("tomato");
			recipeService.getRecipes("broccoli");
			System.out.println("+++++++++++++++++++++++++");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SpringApplication.run(NutriServeApplication.class, args);
	}
	
	 
}
