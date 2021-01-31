package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {
  /**
   * Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
   * Используем метод skip
   * @param persons
   * @return
   */
  public List<String> getNames(List<Person> persons) {
    return persons
      .stream()
      .skip(1)
      .map(Person::getFirstName)
      .collect(Collectors.toList());
  }

  /**
   * ну и различные имена тоже хочется
   * Set - уже множество уникальных элементов поэтому distinct лишний
   * также stream api лишний
   * @param persons
   * @return
   */
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  /**
   * Для фронтов выдадим полное имя, а то сами не могут
   * Похоже опечатка вместо middle name второй раз выводится second name
   * Используя метод join, можно легко добавлять или удалять элементы без копирования кода
   * @param person
   * @return
   */
  public String convertPersonToString(Person person) {
    return Stream
      .of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
      .filter(item -> item != null)
      .collect(Collectors.joining(" "));
  }

  /**
   * словарь id персоны -> ее имя
   * Проще сделать с помощью stream api
   * @param persons
   * @return
   */
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons
      .stream()
      .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  /**
   * есть ли совпадающие в двух коллекциях персоны?
   * Map для быстрой проверки и сразу true, если есть совпадение
   * @param persons1
   * @param persons2
   * @return
   */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Map<Person, Boolean> personsMap = persons1
      .stream()
      .collect(Collectors.toMap(Function.identity(), person -> true));
    for (Person person2 : persons2) {
      if (personsMap.containsKey(person2)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Уже есть стандартный метод для подсчёта количества
   * если использовать старый вариант, то вместо поля count
   * лучше создать локальную переменную
   * @param numbers
   * @return
   */
  public long countEven(Stream<Integer> numbers) {
    return numbers
      .filter(num -> num % 2 == 0)
      .count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = true;
    return codeSmellsGood || reviewerDrunk;
  }
}
