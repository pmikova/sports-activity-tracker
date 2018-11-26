package cz.muni.fi.PA165.tracker.config;

import cz.muni.fi.PA165.tracker.PersistenceApplicationContext;
import cz.muni.fi.PA165.tracker.mapping.MappingServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {MappingServiceImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        // this is needed to support Java 8 time api with Dozer
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }
}