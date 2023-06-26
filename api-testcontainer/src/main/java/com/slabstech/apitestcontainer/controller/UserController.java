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
    public List<User> getAllUsers( @RequestParam(required = false) String deliveryDate) {
        if(deliveryDate == null )
            return userRepository.findAll();

        return userRepository.findAll();
    }


    /**
     * Gets Users by id.
     *
     * @param parcelId the User id
     * @return the User by id
     * @throws Exception
     */
    @GetMapping("/{id}")    // GET Method for Read operation
    public ResponseEntity<User> getHomeById(@PathVariable(value = "id") Long parcelId)
            throws Exception {

        User parcel = userRepository.findById(parcelId)
                .orElseThrow(() -> new Exception("User " + parcelId + " not found"));
        return ResponseEntity.ok().body(parcel);
    }


    @GetMapping("/{userName}")
    public List<User> getAllUsersWithUserName(@PathVariable(value = "userName") @RequestParam(required = true) String userName) {

        return userRepository.findAllByUserName(userName);
    }

    /**
     * Delete User.
     *
     * @param parcelId the parcel id
     * @return the map of the deleted User
     * @throws Exception the exception
     */
    @DeleteMapping("/{id}")    // DELETE Method for Delete operation
    public Map<String, Boolean> deleteHome(@PathVariable(value = "id") Long parcelId) throws Exception {
        User parcel = userRepository.findById(parcelId)
                .orElseThrow(() -> new Exception("User " + parcelId + " not found"));

        userRepository.delete(parcel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping(value = "/parcels", consumes = "application/json")
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
     * @param parcelId the User id
     * @param parcelDetails the User details
     * @return the response entity
     * @throws Exception
     */
    @PutMapping("/{id}")    // PUT Method for Update operation
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long parcelId, @Valid @RequestBody User parcelDetails)
            throws Exception {

        User parcel = userRepository.findById(parcelId)
                .orElseThrow(() -> new Exception("User " + parcelId + " not found"));


        // Set the values for User here to update

        Set<ConstraintViolation<User>> violations = validator.validate(parcel);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }


        final User updatedUser = userRepository.save(parcel);
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

