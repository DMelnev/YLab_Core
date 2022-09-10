package Task2;
import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();


        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        Arrays.stream(RAW_DATA)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()))
                .forEach((key, value) -> System.out.printf("Key: %s\nValue: %d\n", key, value));
        //к сожалению, сортировка которая была до группировки потерялась, тк группировка запихивает все в мап,
        //а мап не сохраняет порядок, но результат как задано...


        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */
        int[] array1 = new int[]{3, 4, 2, 7};
        int[] array2 = new int[]{12, 45, 0, -10};
        int[] array3 = new int[]{-20, 30, 18, 11, 1, 0};

        System.out.println(Arrays.toString(array1) + ", 10 -> " + Arrays.toString(getPairOfNumbersSum(array1, 10)));

        assert Arrays.equals(new int[]{3, 7}, getPairOfNumbersSum(array1, 10));
        assert Arrays.equals(new int[]{45, -10}, getPairOfNumbersSum(array2, 35));
        assert Arrays.equals(new int[]{-20, 30}, getPairOfNumbersSum(array3, 10));



        /*
        Task3
            Реализовать функцию нечеткого поиска

                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
        assert fuzzySearch("car", "ca6$$#_rtwheel"); // true
        assert fuzzySearch("cwhl", "cartwheel"); // true
        assert fuzzySearch("cwhee", "/cartwheel"); // true
        assert fuzzySearch("cartwheel", "cartwheel"); // true
        assert fuzzySearch("cartwheel", "cartwheel/*-!@#$%^&*()_+"); // true
        assert fuzzySearch("c@$+", "cartwheel/*-!@#$%^&*()_+"); // true
        assert !fuzzySearch("cwheeel", "cartwheel"); // false
        assert !fuzzySearch("lw", "cartwheel"); // false
        assert !fuzzySearch("", "cartwheel"); // false
        assert !fuzzySearch("lw", ""); // false
        assert !fuzzySearch(null, "cartwheel"); // false
        assert !fuzzySearch("lw", null); // false


    }

    static boolean fuzzySearch(String key, String string){
        if (key == null || string == null){
            System.out.println("Data can't be null!");
            return false;
        }
        String[] keyArray = key.split("");
        int keyLength = key.length();
        String[] stringArray = string.split("");
        int index = 0;

        for (String word: stringArray){
            if (word.equals(keyArray[index])){
                if (++index >= keyLength) return true;
            }
        }
        return false;
    }

    static int[] getPairOfNumbersSum(int[] array, int sum) {
        if (array == null){
            System.out.println("Array can't be null!");
            return null;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int n = i + 1; n < length; n++) {
                if (array[i] + array[n] == sum) return new int[]{array[i], array[n]};
            }
        }
        return null;
    }
}

