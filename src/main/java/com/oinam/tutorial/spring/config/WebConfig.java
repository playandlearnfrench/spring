package com.oinam.tutorial.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.oinam.tutorial.spring")
public class WebConfig extends WebMvcConfigurerAdapter {

   @Bean
   public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }

   @Bean
   public MultipartResolver multipartResolver() {
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(1048576000 ); // 10MB
      multipartResolver.setMaxUploadSizePerFile(1048576000 ); // 1MB
      return multipartResolver;
   }

   @Bean
   public JavaMailSender getMailSender(){
       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
       //Using gmail
       mailSender.setHost("smtp.gmail.com");
       mailSender.setPort(587);
       mailSender.setUsername("oinam.thopper.singh@gmail.com");
       mailSender.setPassword("[mypassword]");
        
       Properties javaMailProperties = new Properties();
       javaMailProperties.put("mail.smtp.starttls.enable", "true");
       javaMailProperties.put("mail.smtp.auth", "true");
       javaMailProperties.put("mail.transport.protocol", "smtp");
       javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
        
       mailSender.setJavaMailProperties(javaMailProperties);
       return mailSender;
   }
   
    /*@Bean
	public JavaMailSenderImpl javaMailSenderImpl(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("oinam.thopper.singh@gmail.com");
		mailSender.setPassword("Kalu@213");
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		return mailSender;
	}*/
   
}
