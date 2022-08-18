package br.com.sw2you.realmeet.util;

import static java.util.Objects.*;

import br.com.sw2you.realmeet.exception.InvalidOrderByFieldException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.apache.commons.lang3.StringUtils;

public final class PageUtils {
 
    private PageUtils() {}

    public static Pageable newPageable(
            Integer page,
            Integer limit,
            int maxLimit,
            String orderBy,
            List<String> validSortableFields
    ) {
        int definePage = nonNull(page) ? page : 0;
        int defineLimit= nonNull(limit) ? Math.min(limit, maxLimit) : maxLimit;
        Sort defineSort = parseOrderByFields(orderBy, validSortableFields);
        return PageRequest.of(definePage, defineLimit, defineSort);
    }

    private static Sort parseOrderByFields(String orderBy, List<String> validSortableFields) {
        if (isNull(validSortableFields) || validSortableFields.isEmpty())
            throw new IllegalArgumentException("No valid sortable fields were defined");

        if (StringUtils.isBlank(orderBy))
            return Sort.unsorted();

        var orderList = Stream
                .of(orderBy.split(","))
                .map(
                        f -> {
                            String fieldName;
                            Sort.Order order;

                            if (f.startsWith("-")) {
                                fieldName = f.substring(1);
                                order = Sort.Order.desc(fieldName);
                            } else {
                                fieldName = f;
                                order = Sort.Order.asc(fieldName);
                            }

                            if (!validSortableFields.contains(fieldName))
                                throw new InvalidOrderByFieldException();

                            return order;
                        }
                ).collect(Collectors.toList());

        return Sort.by(orderList);
    }
}
