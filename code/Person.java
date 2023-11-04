public class Person {
    int age;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj) {
            return false;
        }
        Person p = (Person) obj;
        return this.age == p.age && this.name.equals(obj.name);
    }
}