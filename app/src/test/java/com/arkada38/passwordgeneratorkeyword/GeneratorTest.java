package com.arkada38.passwordgeneratorkeyword;

import com.arkada38.passwordgeneratorkeyword.Models.Generator;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void testMethod1() throws Exception {
        String expected = Generator.generatePassword("amazon", "horse", 12, true);
        String actual = "t4gF9N]e@J5s";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod2() throws Exception {
        String expected = Generator.generatePassword("amazon", "horse", 12, false);
        String actual = "wFe9NxJ4c1Vq";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod3() throws Exception {
        String expected = Generator.generatePassword("Просто длинное имя для сервиса",
                "Просто длинное ключевое слово", 32, true);
        String actual = "Ky9gEe5V#6Hw0Ov!J?7o:4Gm2Yd%L^3i";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod4() throws Exception {
        String expected = Generator.generatePassword("Просто длинное имя для сервиса",
                "Просто длинное ключевое слово", 32, false);
        String actual = "KkEoVcHv5yOh6J9GnYb2L8xRw7f0Z3eI";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod5() throws Exception {
        String expected = Generator.generatePassword("Just too long service name",
                "Just too long keyword", 32, true);
        String actual = "WwX4kYo9O,3a>E2i:8Cj6J*x]R7h&0Dr";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod6() throws Exception {
        String expected = Generator.generatePassword("Just too long service name",
                "Just too long keyword", 32, false);
        String actual = "WoXuYhOd4xE2cC5qJ0lRiD8K3kU7s6bH";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod7() throws Exception {
        String expected = Generator.generatePassword(
                "Just too long service name Просто длинное имя для сервиса",
                "Just too long keyword Просто длинное ключевое слово", 32, true
        );
        String actual = "pG2VzS8t`9We=P4f6N{b@I3v+1Bd0E<n";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod8() throws Exception {
        String expected = Generator.generatePassword(
                "Just too long service name Просто длинное имя для сервиса",
                "Just too long keyword Просто длинное ключевое слово", 32, false
        );
        String actual = "eGdVwSpW0Ph8Na3Ij4Bo6Ev1Rz5Qm7Zc";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod9() throws Exception {
        String expected = Generator.generatePassword(
                "Just too long !@#$%^&*()_ имя для сервиса",
                "Just too long !@#$%^&*()_ ключевое слово", 32, true
        );
        String actual = "rVuZ7Of0C+3a-8Ty5Wp]Q&6z)2Lq4Us{";
        assertEquals(expected, actual);
    }

    @Test
    public void testMethod10() throws Exception {
        String expected = Generator.generatePassword(
                "Just too long !@#$%^&*()_ имя для сервиса",
                "Just too long !@#$%^&*()_ ключевое слово", 32, false
        );
        String actual = "tVeZlOuC9rT0WfQx4iL6U2m7RqB5cA8h";
        assertEquals(expected, actual);
    }
}
