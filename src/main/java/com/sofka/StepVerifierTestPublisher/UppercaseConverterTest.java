package com.sofka.StepVerifierTestPublisher;

import org.junit.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class UppercaseConverterTest {
    final TestPublisher<String> testPublisher = TestPublisher.create();

    @Test
    public void testUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();

        //Test publisher lo que hace es generar datos para desencadenar un comportamiento esperado
    }

    @Test
    public void testUpperCaseNull(){
        TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL)
                .emit("Hola", null, "mundo");
    }

}
