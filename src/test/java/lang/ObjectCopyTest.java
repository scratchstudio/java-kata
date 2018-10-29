package lang;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ObjectCopyTest {

    @Test
    public void oldAddress_가_바뀔때_참조변수_newAddress_도_영향받는다() {
        //given
        Address oldAddress = new Address("Korea", "Seoul");
        Address newAddress = oldAddress;

        //when
        String newYork = "newYork";
        oldAddress.setCity(newYork);

        //then
        assertThat(newAddress.getCity()).isEqualToIgnoringCase(newYork);
    }

    @Test
    public void address_가_바뀔때_shallowCopy_도_영향_받는다() {
        //given
        Address address = new Address("Korea", "Seoul");
        User user = new User("iamkyu", address);

        //when
        User shallowCopy = new User(user.getName(), user.getAddress());
        address.setCity("NewYork");

        //then
        assertThat(shallowCopy.getAddress().getCity()).isEqualTo(user.getAddress().getCity());
    }

    @Test
    public void deep_copy_with_constructor() {
        //given
        Address address = new Address("Korea", "Seoul");
        User user = new User("iamkyu", address);

        //when
        User newUser = new User(user);
        address.setCity("newYork");

        //then
        assertThat(newUser.getAddress().getCity())
                .isNotEqualTo(user.getAddress().getCity());
    }

    static class Address {
        private String country;
        private String city;

        public Address(String country, String city) {
            this.country = country;
            this.city = city;
        }

        public Address(Address address) {
            // constructor for deep copy
            this(address.getCountry(), address.getCity());
        }

        public String getCountry() {
            return country;
        }

        public String getCity() {
            return city;
        }

        public Address setCountry(String country) {
            this.country = country;
            return this;
        }

        public Address setCity(String city) {
            this.city = city;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Address address = (Address) o;

            if (country != null ? !country.equals(address.country) : address.country != null)
                return false;
            return city != null ? city.equals(address.city) : address.city == null;
        }
    }

    static class User {
        private String name;
        private Address address;

        public User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public User(User user) {
            // constructor for deep copy
            this(user.getName(), new Address(user.address));
        }

        public String getName() {
            return name;
        }

        public Address getAddress() {
            return address;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public User setAddress(Address address) {
            this.address = address;
            return this;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (name != null ? !name.equals(user.name) : user.name != null) return false;
            return address != null ? address.equals(user.address) : user.address == null;
        }
    }
}
