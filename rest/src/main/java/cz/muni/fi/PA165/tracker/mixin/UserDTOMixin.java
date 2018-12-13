package cz.muni.fi.PA165.tracker.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * UserDTO mix in
 * @author pmikova 433345
 */
@JsonIgnoreProperties({"passwordHash"})
public class UserDTOMixin {
}
