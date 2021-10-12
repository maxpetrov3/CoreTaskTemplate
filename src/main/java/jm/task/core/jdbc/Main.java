package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl service = new UserServiceImpl();

      //  Создание таблицы User(ов)
        service.createUsersTable();

      //  Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        service.saveUser("Max", "Petrov", (byte) 28);
        service.saveUser("Ivan", "Ivanov", (byte) 45);
        service.saveUser("Fedor", "Petrov", (byte) 12);
        service.saveUser("Maria", "Sidorova", (byte) 18);

        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        service.getAllUsers().forEach(System.out::println);

       // Очистка таблицы User(ов)
        service.cleanUsersTable();

      //  Удаление таблицы
        service.dropUsersTable();
    }
}
