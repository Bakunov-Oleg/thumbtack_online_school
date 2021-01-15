package net.thumbtack.school.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Functional {

    //1.	Используя функциональный интерфейс java.util.function.Function и лямбда-выражения, создайте:
    //        -	функцию split (String s) -> List<String>, разбивающую строку по пробелам
    //        -	функцию count (List<?> list) -> Integer, считающую количество элементов в любом списке
    //Примените split к строке, содержащей пробелы, а после этого примените count к ее результату.
    public List<String> split(String s) {
        Function<String, List<String>> list = t -> Arrays.asList((t.split(" ")));
        return list.apply(s);
    }

    public int count(List<String> list) {
        Function<List<String>, Integer> count = t -> t.size();
        return count.apply(list);
    }

    //2.	Попробуйте избавиться от декларации типов в параметрах функций из пункта 1. Почему это возможно?
    public List<?> splitWitoutType(String s) {
        Function<String, List<?>> list = t -> Arrays.asList((t.split(" ")));
        return list.apply(s);
    }

    //3.	Попробуйте заменить лямбда-выражение на method reference, в каких случаях это возможно и почему?
    public int countOnReference(List<String> list) {
        Function<List<String>, Integer> count = List::size;
        return count.apply(list);
    }

    //4.	Перепишите решение из п. 1, композируя функции split и count при помощи default-методов интерфейса Function, в новую функцию splitAndCount:
    //        a.	используйте andThen
    //        b.	используйте compose
    //      Чем данный подход отличается от count.apply(split.apply(str)) ?
    public int splitAndCountWithAndThen(String s) {
        Function<String, List<String>> list = t -> Arrays.asList((t.split(" ")));
        Function<String, Integer> splitAndCount = list.andThen(List::size);
        return splitAndCount.apply(s);
    }

    public int splitAndCountWithCompose(String s) {
        Function<List<String>, Integer> count = List::size;
        Function<String, Integer> splitAndCount = count.compose(t -> Arrays.asList((t.split(" "))));
        return splitAndCount.apply(s);
    }

    //5.	Напишите функцию create, принимающую в качестве аргумента строку и возвращающую Person с именем равным переданной строке. Перепишите при помощи method reference.
    public Person create(String name) {
        Function<String, Person> create = Person::new;
        return create.apply(name);
    }

    //6.	Реализуйте функцию max, используя method reference к Math.max. Какой интерфейс из java.util.function подойдет для функции с двумя параметрами?
    public Double max(double a, double b) {
        DoubleBinaryOperator max = Math::max;
        return max.applyAsDouble(a, b);
    }

    //7.	Реализуйте функцию getCurrentDate, возвращающую текущую дату () -> java.util.Date. Какой интерфейс из java.util.function подойдет для функции без параметров?
    public Date getCurrentDate() {
        Supplier<Date> date = Date::new;
        return date.get();
    }

    //8.	Реализуйте функцию isEven (Integer a) -> Boolean. Какой интерфейс из java.util.function для этого подойдет?
    public Boolean isEven(Integer a) {
        Predicate<Integer> result = t -> t == 0;
        return result.test(a);
    }

    //9.	Реализуйте функцию areEqual (Integer a, Integer b) -> Boolean. Какой интерфейс из java.util.function для этого подойдет?
    public Boolean areEqual(Integer a, Integer b) {
        BiPredicate<Integer, Integer> result = Integer::equals;
        return result.test(a, b);
    }

    //10.	Создайте интерфейс MyFunction, декларирующий единственный метод K apply(T arg). Замените Function на MyFunction.
    public List<String> splitByMyFunction(String s) {
        MyFunction<String, List<String>> list = t -> Arrays.asList((t.split(" ")));
        return list.apply(s);
    }

    //12.	 Необходимо реализовать метод getMothersMotherFather, возвращающего отца бабушки по материнской линии, двумя способами:
    //      a.	Реализовать класс Person с двумя полями Person father, Person mother (задавать их значения в конструкторе).
    //    Метод getMothersMotherFather должен либо вернуть экземпляр класса Person, либо null.
    //    Должна быть защита от NPE в цепочке условий.
    //      b.	Реализовать класс Person с двумя полями Optional<Person> father, Optional<Person> mother (передавать в конструктор Person или null,
    //    оборачивать в Optional через Optional#ofNullable). Метод getMothersMotherFather должен вернуть Optional<Person> и быть реализованным только на базе вызова цепочки Optional.flatMap.

    public Person getMothersMotherFatherOne(Person person) {
        Person result = person.getMother();
        if (result == null) {
            return null;
        }
        result = result.getMother();
        if (result == null) {
            return null;
        }
        return result.getFather();
    }

    public Optional<Person> getMothersMotherFatherTwo(Person person) {
        return Optional.of(person)
                .flatMap(Person::getMotherOpt)
                .flatMap(Person::getMotherOpt)
                .flatMap(Person::getFatherOpt);
    }

    //13.    Напишите метод IntStream transform(IntStream stream, IntUnaryOperator op), трансформирующий каждый элемент при помощи операции op. Выведите результат на консоль.
    public IntStream transform(IntStream stream, IntUnaryOperator op) {
        IntStream result = stream.map(op);
        System.out.println(result);
        return result;
    }

    //14.	 Задача аналогичная предыдущей, только теперь нужно трансформировать входящий Stream в параллельный, обратите внимание на изменившийся вывод на консоль.
    public IntStream transformParallel(IntStream stream, IntUnaryOperator op) {
        IntStream result = stream.parallel().map(op);
        System.out.println(result);
        return result;
    }

    //15.	 Реализуйте класс Person(String name, int age). Имея список List<Person>,
    // при помощи Stream API необходимо вернуть уникальные имена для всех людей старше 30 лет, отсортированные по длине имени.
    public List<String> getUniquePersonOldest30(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getAge() > 30)
                .map(Person::getName)
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    //16.	 Имея список List<Person>, при помощи Stream API необходимо вернуть уникальные имена для всех людей старше 30 лет,
    // отсортированные по количеству людей с одинаковым именем. Используйте Collectors.groupingBy
    public List<String> getUniquePersonOldest30GroupingBy(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //17.	 Реализуйте sum(List<Integer> list) и product(List<Integer> list) через Stream.reduce
    public int sum(List<Integer> list) {
        return list.stream().mapToInt(value -> value).reduce(0, Integer::sum);
    }

    public int product(List<Integer> list) {
        return list.stream().mapToInt(value -> value).reduce(1, (x, y) -> x * y);
    }
}
