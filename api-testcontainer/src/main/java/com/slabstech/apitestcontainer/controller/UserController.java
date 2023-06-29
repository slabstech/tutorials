package com.slabstech.apitestcontainer.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slabstech.apitestcontainer.model.User;
import com.slabstech.apitestcontainer.repository.UserRepository;
import com.slabstech.apitestcontainer.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<User> getAllUsers( ) {

        return userRepository.findAll();
    }


    /**
     * Gets Users by id.
     *
     * @param userId the User id
     * @return the User by id
     * @throws Exception
     */
    @GetMapping("/{id}")    // GET Method for Read operation
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User " + userId + " not found"));
        return ResponseEntity.ok().body(user);
    }


    @GetMapping("/{name}")
    public List<User> getAllUsersWithName(@PathVariable(value = "name") @RequestParam(required = true) String name) {

        return (List<User>) userRepository.findAllByName(name);
    }

    /**
     * Delete User.
     *
     * @param userId the user id
     * @return the map of the deleted User
     * @throws Exception the exception
     */
    @DeleteMapping("/{id}")    // DELETE Method for Delete operation
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User " + userId + " not found"));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity<User> insertNewUsers(@RequestBody User user) {

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
        User savedUser = userRepository.save(user);
        notificationService.notifySomeoneAboutChange(savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Update User response entity.
     *
     * @param userId the User id
     * @param userDetails the User details
     * @return the response entity
     * @throws Exception
     */
    @PutMapping("/{id}")    // PUT Method for Update operation
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User " + userId + " not found"));


        // Set the values for User here to update

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }


        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

