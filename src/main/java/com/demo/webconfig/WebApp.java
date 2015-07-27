package com.demo.webconfig;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.demo.annotation.Production;
import com.demo.config.ApplicationConfig;
import com.demo.controller.config.CurrentUserArgumentResolver;

@Production
public class WebApp extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebAppConfig.class };
	}

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		super.registerDispatcherServlet(servletContext);
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addListener(RequestContextListener.class);
		super.onStartup(servletContext);
	}

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = { "com.demo.controller" })
	public static class WebAppConfig extends WebMvcConfigurerAdapter {

		@Bean
		public ViewResolver getViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/views/");
			resolver.setSuffix(".jsp");
			resolver.setOrder(1);
			return resolver;
		}

		@Bean
		CommonsMultipartResolver multipartResolver() {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
			multipartResolver.setMaxUploadSize(10000000);
			return multipartResolver;
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**")
					.addResourceLocations("/resources/")
					.setCachePeriod(31556926);

			registry.addResourceHandler("/app/**")
					.addResourceLocations("/app/");
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
			webContentInterceptor.setCacheSeconds(0);
			webContentInterceptor.setUseExpiresHeader(true);
			webContentInterceptor.setUseCacheControlHeader(true);
			webContentInterceptor.setUseCacheControlNoStore(true);
			registry.addInterceptor(webContentInterceptor);
		}

		@Override
		public void addArgumentResolvers(
				List<HandlerMethodArgumentResolver> argumentResolvers) {
			argumentResolvers.add(new CurrentUserArgumentResolver());
			super.addArgumentResolvers(argumentResolvers);
		}

		@Override
		public void configureDefaultServletHandling(
				DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}

	}

}