package cz.muni.fi.PA165.tracker.configuration;

//import cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.tracker.controllers.ActivityRecordController;
import cz.muni.fi.PA165.tracker.controllers.SportActivityController;
import cz.muni.fi.PA165.tracker.controllers.UserController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


@EnableWebMvc
@Configuration
//@Import({SampleDataConfiguration.class})
@ComponentScan(basePackageClasses = {ActivityRecordController.class, SportActivityController.class, UserController.class})
public class MvcConfiguration // implements WebMvcConfigurer
{



//
//    @Override
//    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
//
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
//
//    }
//
//    }
}
