package cz.muni.fi.PA165.tracker.mapping;


import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service
public class MappingServiceImpl implements MappingService{


    @Inject
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> Map<T, Integer> mapTo(Map<?, Integer> objects, Class<T> mapToClass) {
        Map<T, Integer> mappedCollection = new HashMap<>();
        for (Map.Entry<?, Integer> entry : objects.entrySet()) {
            mappedCollection.put(dozer.map(entry.getKey(), mapToClass), entry.getValue());
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return (u == null) ? null : dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}
