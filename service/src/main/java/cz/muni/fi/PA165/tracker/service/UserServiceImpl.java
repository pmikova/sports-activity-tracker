package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.ActivityRecordDAO;
import cz.muni.fi.PA165.tracker.dao.BurnedCaloriesDAO;
import cz.muni.fi.PA165.tracker.dao.UserDAO;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.UserType;
import cz.muni.fi.PA165.tracker.exceptions.NotExistingEntityException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Implementation of UserService
 * @author pmikova 433345
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private ActivityRecordDAO ard;

    @Inject
    private BurnedCaloriesDAO bcd;

    @Override
    public void register(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password can not be null!");
        }
        try {
            user.setPasswordHash(createHash(password));
        }catch (Exception e){
            throw new IllegalStateException("Error while creating password hash.");
        }
        userDAO.create(user);
    }
    @Override
    public boolean authenticate(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null!");
        }
        try {
            return validatePassword(password, user.getPasswordHash());
        } catch (Exception e) {
            throw new IllegalStateException("Error while validating password.");
        }
    }

    @Override
    public boolean isAdministrator(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        User getUser = getById(id);
        return getUser.getUserType() == UserType.ADMIN;
    }

    @Override
    public void delete(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User has no ID!");
        }
        User getUser = getById(user.getId());
        if (getUser == null){
            throw new NotExistingEntityException("No user with given id!");
        }
        bcd.deleteByUser(getUser);
        ard.deleteByUser(getUser);
        userDAO.delete(getUser);

    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of user can not be null!");
        }
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email can not be null!");
        }
        return userDAO.getByEmail(email);
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        return userDAO.update(user);
    }

    @Override
    public int getAge(User user) {
        return Period.between(user.getBirthdate(), LocalDate.now()).getYears();
    }

    private static String createHash(String password) {
        final int saltByteSize = 24;
        final int hashByteSize = 24;
        final int numberIterations = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltByteSize];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, numberIterations, hashByteSize);
        // format iterations:salt:hash
        return numberIterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("password hash is null");
        }
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bigInt = new BigInteger(1, array);
        String hex = bigInt.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
