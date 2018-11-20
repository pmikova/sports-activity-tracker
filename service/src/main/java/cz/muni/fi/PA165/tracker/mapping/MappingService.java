package cz.muni.fi.PA165.tracker.mapping;

import org.dozer.Mapper;

import java.util.*;

/**
 * Mapping service interface.
 * @author pmikova 433345
 */

public interface MappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> Map<T, Integer> mapTo(Map<?, Integer> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();

}
