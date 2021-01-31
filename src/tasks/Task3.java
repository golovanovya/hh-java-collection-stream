package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Пример взял из презентации
 * отдельный класс, достаточно объёмно получилось
 * тернарный оператор не использовал, потому что 3 сравнения нужно
 */
class PersonCompare implements Comparator<Person> {
  @Override
  public int compare(Person p1, Person p2) {
    int secondNameComp = p1.getSecondName().compareTo(p2.getSecondName());
    if (secondNameComp != 0) {
      return secondNameComp;
    }
    int firstNameComp = p1.getFirstName().compareTo(p2.getFirstName());
    if (firstNameComp != 0) {
      return firstNameComp;
    }
    int createdAtComp = p1.getCreatedAt().compareTo(p2.getCreatedAt());
    return createdAtComp;
  }
}
/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 implements Task {

  /**
   * stream api потому что Collection в любом случае надо преобразовывать перед сортировкой
   * @param persons
   * @return
   */
  private List<Person> sort(Collection<Person> persons) {
    return persons
      .stream()
      .sorted(new PersonCompare())
      .collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    Instant time = Instant.now();
    List<Person> persons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(2, "Vasya", "Petrov", time),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
    );
    List<Person> sortedPersons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(2, "Vasya", "Petrov", time)
    );
    return sortedPersons.equals(sort(persons));
  }
}
