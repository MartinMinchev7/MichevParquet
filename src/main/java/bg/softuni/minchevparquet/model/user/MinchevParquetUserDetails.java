package bg.softuni.minchevparquet.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class MinchevParquetUserDetails extends User {

    private final String firstName;
    private final String lastName;
    private final UUID uuid;
    private final Long id;

    public MinchevParquetUserDetails(
            UUID uuid,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String firstName,
            String lastName,
            Long id
    ) {

        super(email, password, authorities);
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(lastName);
        }

        return fullName.toString();
    }
}
