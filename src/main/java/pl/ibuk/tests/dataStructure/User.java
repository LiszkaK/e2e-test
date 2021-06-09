package pl.ibuk.tests.dataStructure;

import com.github.javafaker.Faker;
import lombok.Getter;

@Getter
public class User {

    private String firstName, lastName, emailAddress, country;

    public static final class UserBuilder {
        private String firstName, lastName, emailAddress, country;

        public UserBuilder() {
            Faker faker = new Faker();
            this.firstName = faker.name().firstName();
            this.lastName = faker.name().lastName();
            this.emailAddress = faker.internet().emailAddress();
            this.country = faker.country().name();
        }

        public UserBuilder(String emailAddress) {
            Faker faker = new Faker();
            this.firstName = faker.name().firstName();
            this.lastName = faker.name().lastName();
            this.emailAddress = emailAddress;
            this.country = faker.country().name();
        }

        public User build() {
            User user = new User();
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.emailAddress = this.emailAddress;
            user.country = this.country;
            return user;
        }
    }
}
