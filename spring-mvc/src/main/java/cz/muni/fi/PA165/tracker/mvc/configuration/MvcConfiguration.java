package cz.muni.fi.PA165.tracker.mvc.configuration;

import cz.muni.fi.PA165.sampledata.SampleDataConfiguration;
import cz.muni.fi.PA165.sampledata.SampleDataLoadingFacade;
import cz.muni.fi.PA165.tracker.mvc.convert.CustomDateConverter;
import cz.muni.fi.PA165.tracker.mvc.convert.CustomTimeDateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.inject.Inject;
import javax.validation.Validator;

/**
 * Configuration class for our web app.
 * @author pmikova 433345
 */
@Configuration
@EnableWebMvc
@Import({SampleDataConfiguration.class, TrackerSecurityConfiguration.class})
@ComponentScan("cz.muni.fi.PA165.tracker.mvc")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MvcConfiguration.class);

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    /**
     * Map views to jsp.
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * Get resources from WEB-INF
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }

    /**
     * Enable default tomcat servlet.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Provides localized texts.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Texts");
        return messageSource;
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomDateConverter());
        registry.addConverter(new CustomTimeDateConverter());

    }


}
