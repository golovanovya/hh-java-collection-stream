package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  /**
   * O(n + m)
   * n - persons
   * m - personIds
   * сначала пробегаем по persons, чтобы собрать hashmap
   * потом пробегаем по personsIds чтобы собрать массив в нужном порядке
   * Через stream api потому что удобно создавать hashmap
   * а потом нужен метод map, которого нет у List.
   *
   * @param personIds
   * @return persons
   */
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Map<Integer, Person> personMap = PersonService.findPersons(personIds).stream()
      .collect(Collectors.toMap(Person::getId, Function.identity()));
    return personIds.stream()
      .map(personMap::get)
      .collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
