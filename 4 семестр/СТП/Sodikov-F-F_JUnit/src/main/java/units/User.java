package units;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private int age;
    private Sex sex;

    private static Map<Integer, User> allUsers;

    private static int countId = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, sex);
    }

    public User(String name, int age, Sex sex) {
        if (allUsers == null){
            allUsers = new HashMap<>();
        }

        this.name = name;
        this.age = age;
        this.sex = sex;

        if (!hasUser()){
            countId++;
            this.id = countId;
            allUsers.put(id, this);
        }
    }

    private boolean hasUser(){
        for (User user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    //список всех пользователей
    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    // список всех пользователей по полу
    public static List<User> getAllUsersBySex(Sex sex){
        List<User> listAllUsers = new ArrayList<>();
        for (User user : allUsers.values()){
            if (user.sex == sex){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }

    //кол-во пользователей
    public static int getUsersCount(){
        return allUsers.size();
    }

    //kол-во пользователей по полу
    public static int getUsersCountBySex(Sex sex){
        return getAllUsersBySex(sex).size();
    }

    //общ. сумма по возрасту
    public static int getAllAgeUsers(){
       int countAge = 0;
       for (User user : allUsers.values()){
           countAge += user.age;
       }
       return countAge;
    }

    //общ сумма по возрасту и по полу
    public static int getAllAgeUsersBySex(Sex sex){
        int countAge = 0;
        for (User user : getAllUsersBySex(sex)){
            countAge += user.age;
        }
        return countAge;
    }

    // Средний возраст всего списка
    public static int getAverageAgeOfAllUsers(){
        return getAllAgeUsers() / getUsersCount();
    }

    // Средний возраст всего списка по полу
    public static int getAverageAgeOfAllUsersBySex(Sex sex){
        return getAllAgeUsersBySex(sex) / getUsersCountBySex(sex);
    }


}
