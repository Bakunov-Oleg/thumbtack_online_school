package net.thumbtack.school.functional;


import org.junit.Test;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;


public class TestFunctional {
    @Test
    public void testSplit() {
        Functional functional = new Functional();
        List<String> split = functional.split("раз, два, три, четыре, пять");
        assertEquals(5, split.size());
    }

    @Test
    public void testCount() {
        Functional functional = new Functional();
        List<String> split = functional.split("раз, два, три, четыре, пять");
        int count = functional.count(split);
        assertEquals(split.size(), count);
    }

    @Test
    public void testSplitWitoutType() {
        Functional functional = new Functional();
        List<?> split = functional.splitWitoutType("раз, два, три, четыре, пять");
        assertEquals(5, split.size());
    }

    @Test
    public void testCountOnReference() {
        Functional functional = new Functional();
        List<String> split = functional.split("раз, два, три, четыре, пять");
        int count = functional.countOnReference(split);
        assertEquals(split.size(), count);
    }

    @Test
    public void testSplitAndCountWithAndThen() {
        Functional functional = new Functional();
        int result = functional.splitAndCountWithAndThen("раз, два, три, четыре, пять");
        assertEquals(5, result);
    }

    @Test
    public void testSplitAndCountWithCompose() {
        Functional functional = new Functional();
        int result = functional.splitAndCountWithCompose("раз, два, три, четыре, пять");
        assertEquals(5, result);
    }

    @Test
    public void testCreate() {
        Functional functional = new Functional();
        Person newPerson = functional.create("Вася");
        assertEquals("Вася", newPerson.getName());
    }

    @Test
    public void testMax() {
        Functional functional = new Functional();
        double a = 10.5;
        double b = 21;
        double result = functional.max(a, b);
        assertTrue(Double.compare(b, result) == 0);
    }

    @Test
    public void testGetDate() {
        Functional functional = new Functional();
        Supplier<Date> date = Date::new;
        assertEquals(date.get().toString(), functional.getCurrentDate().toString());
    }

    @Test
    public void testIsEven() {
        Functional functional = new Functional();
        assertAll(
                () -> assertFalse(functional.isEven(1)),
                () -> assertTrue(functional.isEven(0)),
                () -> assertFalse(functional.isEven(10))
        );
    }

    @Test
    public void testAreEqual() {
        Functional functional = new Functional();
        assertAll(
                () -> assertFalse(functional.areEqual(1, 25)),
                () -> assertTrue(functional.areEqual(15, 15)),
                () -> assertFalse(functional.areEqual(10, 100))
        );
    }

    @Test
    public void testSplitByMyFunction() {
        Functional functional = new Functional();
        List<String> split = functional.splitByMyFunction("раз, два, три, четыре, пять");
        assertEquals(5, split.size());
    }

    @Test
    public void testGetMothersMotherFather() {
        Functional functional = new Functional();
        Person person = new Person("Человек");
        assertNull(functional.getMothersMotherFatherOne(person));
        Person mother = new Person("Мама");
        person.setMother(mother);
        assertNull(functional.getMothersMotherFatherOne(person));
        Person mothersMother = new Person("Бабушка");
        mother.setMother(mothersMother);
        assertNull(functional.getMothersMotherFatherOne(person));
        Person mothersMotherFather = new Person("ОтецБабушки");
        mothersMother.setFather(mothersMotherFather);
        assertEquals(mothersMotherFather, functional.getMothersMotherFatherOne(person));
    }


    @Test
    public void testGetMothersMotherFatherOption() {
        Functional functional = new Functional();

        Optional<Person> mothersMotherFather = Optional.ofNullable(new Person("ОтецБабушки"));
        Optional<Person> mothersMother = Optional.ofNullable(new Person("Бабкушка"));
        Optional<Person> mother = Optional.ofNullable(new Person("Мама"));
        Optional<Person> person = Optional.ofNullable(new Person("Человек"));

        mothersMother.get().setFatherOpt(mothersMotherFather.get());
        mother.get().setMotherOpt(mothersMother.get());
        person.get().setMotherOpt(mother.get());

        assertEquals(mothersMotherFather, functional.getMothersMotherFatherTwo(person.get()));
    }

    @Test
    public void testTransform() {
        Functional functional = new Functional();
        IntUnaryOperator op = a -> 10 * a;
        IntStream intSream = functional.transform(IntStream.of(1, 2, 3, 4, 5, 6), op);
        intSream.forEach(System.out::println);
    }

    @Test
    public void testTransformParallel() {
        Functional functional = new Functional();
        IntUnaryOperator op = a -> 10 * a;
        IntStream intSream = functional.transformParallel(IntStream.of(1, 2, 3, 4, 5, 6), op);
        intSream.forEach(System.out::println);
    }

    @Test
    public void testGetUniquePersonOldest30() {
        Functional functional = new Functional();
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Василий", 20));

        personList.add(new Person("Василий", 30));
        personList.add(new Person("Василий", 80));
        personList.add(new Person("Василий", 45));

        personList.add(new Person("Иван", 31));
        personList.add(new Person("Иван", 96));

        personList.add(new Person("Екатерина", 60));

        personList.add(new Person("Тимур", 22));

        List<String> uniqueName = new ArrayList<>();
        uniqueName.add("Иван");
        uniqueName.add("Василий");
        uniqueName.add("Екатерина");

        assertEquals(uniqueName, functional.getUniquePersonOldest30(personList));
    }

    @Test
    public void testGetUniquePersonOldest30GroupingBy() {
        Functional functional = new Functional();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Василий", 20));

        personList.add(new Person("Василий", 31));
        personList.add(new Person("Василий", 80));
        personList.add(new Person("Василий", 45));

        personList.add(new Person("Иван", 31));
        personList.add(new Person("Иван", 96));

        personList.add(new Person("Екатерина", 60));

        personList.add(new Person("Тимур", 22));

        List<String> uniqueNameSortedBy = new ArrayList<>();
        uniqueNameSortedBy.add("Екатерина");
        uniqueNameSortedBy.add("Иван");
        uniqueNameSortedBy.add("Василий");

        assertEquals(uniqueNameSortedBy, functional.getUniquePersonOldest30GroupingBy(personList));
    }

    @Test
    public void testSum() {
        Functional functional = new Functional();
        assertEquals(5, functional.sum(Arrays.asList(1, 3, 1)));
    }

    @Test
    public void testProduct() {
        Functional functional = new Functional();
        assertEquals(144, functional.product(Arrays.asList(2, 8, 9)));
    }
}
