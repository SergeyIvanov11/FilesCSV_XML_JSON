# Задача 1: CSV - JSON парсер

## Входные данные:
Файл `data.csv` со следующим содержимым в корневой папке проекта:
```csv
1,John,Smith,USA,25
2,Ivan,Petrov,RU,23
```
А также класс `Employee`, который будет содержать информацию о сотрудниках.
```java
public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}
``` 
## Выходные данные:
В резльтате работы программы в корне проекта появится файл `data.json` со следующим содержимым:
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Smith",
    "country": "USA",
    "age": 25
  },
  {
    "id": 2,
    "firstName": "Ivan",
    "lastName": "Petrov",
    "country": "RU",
    "age": 23
  }
]
```

# Задача 2: XML - JSON парсер

## Входные данные:
Файл data.xml в корне проекта, со следующим содержимым:
```csv
<staff>
    <employee>
        <id>1</id>
        <firstName>John</firstName>
        <lastName>Smith</lastName>
        <country>USA</country>
        <age>25</age>
    </employee>
    <employee>
        <id>2</id>
        <firstName>Inav</firstName>
        <lastName>Petrov</lastName>
        <country>RU</country>
        <age>23</age>
    </employee>
</staff>
```
## Выходные данные:
В резльтате работы программы в корне проекта появится файл `data1.json` со следующим содержимым:
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Smith",
    "country": "USA",
    "age": 25
  },
  {
    "id": 2,
    "firstName": "Inav",
    "lastName": "Petrov",
    "country": "RU",
    "age": 23
  }
]
```
