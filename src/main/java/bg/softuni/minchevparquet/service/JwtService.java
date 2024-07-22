package bg.softuni.minchevparquet.service;

import java.util.Map;

public interface JwtService {
    String generateJwtToken(String userId, Map<String, Object> claims);
}
