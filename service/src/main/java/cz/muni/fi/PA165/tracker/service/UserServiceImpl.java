package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.dao.UserDAO;
import cz.muni.fi.PA165.tracker.entities.User;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Implementation of UserService
 * @author pmikova 433345
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public void register(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password can not be null!");
        }
        try {
            user.setPasswordHash(hash(password));
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
            return validate(password, user.getPasswordHash());
        } catch (Exception e) {
            throw new IllegalStateException("Error while validating password.");
        }
    }

    @Override
    public boolean isAdministrator(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null!");
        }
        User getUser = getById(user.getId());
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
        //TODO delete all burnedCalories and all activityRecords
        userDAO.delete(getUser);

    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can not be null!");
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

    /**
     * This method creates a hashed password with algorithm PBKDF2 with hmac SHA1
     * @param password password to create hash of
     * @return hashed password
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private String hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Validate method checks if the password is valid.
     * @param originalPassword user input password
     * @param storedPassword password hash in database
     * @return true if the password is valid, false otherwise
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static boolean validate(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Helper method to create bytes from hex
     * @param hex to convert to byte
     * @return byte array
     */
    private static byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * Provide some random salt for the passwords so they are never the same
     * @return string that will be appended to password hash (a.k.a. salt)
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Helper method to convert byte array to hex
     * @param array bytes to convert to hex
     * @return hex in string
     * @throws NoSuchAlgorithmException
     */
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
