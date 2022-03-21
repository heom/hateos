package me.study.hateoas.events;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class EventTest {
    @ParameterizedTest
    @MethodSource("parametersForTestFree")
    public void testFree(int basePrice, int MaxPrice, boolean isFree){
        //Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(MaxPrice)
                .build();

        //When
        event.update();

        //Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private static Stream<Arguments> parametersForTestFree(){
        return Stream.of(
                arguments(0, 0, true)
                , arguments(100, 0, false)
                , arguments(0, 100, false)
                , arguments(100, 100, false)
        );
    }

    @ParameterizedTest
    @MethodSource("parametersForTestOffline")
    public void testOffline(String location, boolean isOffline){
        //Given
        Event event = Event.builder()
                .location(location)
                .build();

        //When
        event.update();

        //Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private static Stream<Arguments> parametersForTestOffline(){
        return Stream.of(
                arguments("강남역 네이버 D2 스타럽 팩토리", true)
                , arguments("         ", false)
                , arguments(null, false)
        );
    }
}