package co.edu.poli.acmelibrary.services;

import co.edu.poli.acmelibrary.dtos.UserDTO;
import co.edu.poli.acmelibrary.entities.User;
import co.edu.poli.acmelibrary.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createNewUser(UserDTO userDTO) {
        User userEntity = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getIdentification(),
                userDTO.getEmail(),
                userDTO.getAge()
        );

        userRepository.save(userEntity);

        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());

        isANullUser(optionalUser);

        User userEntity = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getIdentification(),
                userDTO.getEmail(),
                userDTO.getAge()
        );

        userRepository.save(userEntity);

        return userDTO;
    }

    public Optional<UserDTO> findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        User userFound = optionalUser.get();

        UserDTO userDTO = new UserDTO(
                userFound.getId(),
                userFound.getName(),
                userFound.getLastName(),
                userFound.getIdentification(),
                userFound.getEmail(),
                userFound.getAge()
        );

        return Optional.of(userDTO);
    }

    public List<UserDTO> getUserList() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        users.forEach(user -> userDTOs.add(new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getIdentification(),
                user.getEmail(),
                user.getAge()
        )));

        return userDTOs;
    }

    private void isANullUser(Optional<User> user) throws Exception {

        if (user.isEmpty()) {
            throw new Exception("Book not found");
        }
    }
}
