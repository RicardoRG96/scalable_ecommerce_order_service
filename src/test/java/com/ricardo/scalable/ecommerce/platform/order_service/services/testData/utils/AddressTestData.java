package com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Address;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.UserTestData.*;

public class AddressTestData {

    public static List<Address> createListOfAddresses() {
        Address address1 = createAddress001().orElseThrow();
        Address address2 = createAddress002().orElseThrow();
        Address address3 = createAddress003().orElseThrow();
        Address address4 = createAddress004().orElseThrow();

        return List.of(address1, address2, address3, address4);
    }

    public static Optional<Address> createAddress001() {
        User user = createUser001().orElseThrow();
        Address address = new Address();

        address.setId(1L);
        address.setUser(user);
        address.setTitle("Casa en Viña del Mar");
        address.setAddressLine1("Avenida San Martín 456");
        address.setAddressLine2("Departamento 3C");
        address.setCountry("Chile");
        address.setCity("Viña del Mar");
        address.setCommune("Viña del Mar");
        address.setPostalCode("2520000");
        address.setLandmark("Cerca del Reloj de Flores");
        address.setCreatedAt(Timestamp.from(Instant.now()));
        address.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(address);
    }

    public static Optional<Address> createAddress002() {
        User user = createUser002().orElseThrow();
        Address address = new Address();

        address.setId(2L);
        address.setUser(user);
        address.setTitle("Oficina en Concepción");
        address.setAddressLine1("Calle Barros Arana 789");
        address.setAddressLine2("Piso 10");
        address.setCountry("Chile");
        address.setCity("Concepción");
        address.setCommune("Concepción");
        address.setPostalCode("4030000");
        address.setLandmark("Frente a la Plaza de la Independencia");
        address.setCreatedAt(Timestamp.from(Instant.now()));
        address.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(address);
    }

    public static Optional<Address> createAddress003() {
        User user = createUser001().orElseThrow();
        Address address = new Address();

        address.setId(3L);
        address.setUser(user);
        address.setTitle("Casa en Valparaíso");
        address.setAddressLine1("Subida Ecuador 101");
        address.setAddressLine2("");
        address.setCountry("Chile");
        address.setCity("Valparaíso");
        address.setCommune("Valparaíso");
        address.setPostalCode("2340000");
        address.setLandmark("Cerca del Ascensor El Peral");
        address.setCreatedAt(Timestamp.from(Instant.now()));
        address.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(address);
    }

    public static Optional<Address> createAddress004() {
        User user = createUser002().orElseThrow();
        Address address = new Address();

        address.setId(4L);
        address.setUser(user);
        address.setTitle("Departamento en La Serena");
        address.setAddressLine1("Avenida del Mar 222");
        address.setAddressLine2("Departamento 12A");
        address.setCountry("Chile");
        address.setCity("La Serena");
        address.setCommune("La Serena");
        address.setPostalCode("1720000");
        address.setLandmark("Frente a la playa");
        address.setCreatedAt(Timestamp.from(Instant.now()));
        address.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(address);
    }

}
