package com.delicious.model;

import static org.junit.jupiter.api.Assertions.*;

import com.delicious.util.EntityConverter;
import org.junit.jupiter.api.Test;

class EntityConverterTest {

    @Test
    void convertLists() {
        EntityConverter<String,Integer> conv = new EntityConverter<>(
                Integer::parseInt,
                i -> Integer.toString(i)
        );
        assertEquals((Integer)123, conv.convertToDto("123"));
        assertEquals("456", conv.convertToEntity(456));
        assertEquals(java.util.List.of(1,2,3), conv.convertToDtoList(java.util.List.of("1","2","3")));
    }
}