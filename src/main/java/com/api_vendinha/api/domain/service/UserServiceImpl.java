package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {

        Optional<User> userExist = userRepository.findByCpf(userRequestDto.getCpf());

        if(userExist.isPresent()){
            throw new IllegalArgumentException("Já existe um cadastro com esse telefone");
        }

        User user = new User();

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCpf(userRequestDto.getCpf());
        user.setCnpj(userRequestDto.getCnpj());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setCpf(savedUser.getCpf());
        userResponseDto.setCnpj(savedUser.getCnpj());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }
    @Override
    public UserResponseDto update(UserRequestDto userRequestDto, Long id){
        User userExist = userRepository.findById(id).orElseThrow();

        userExist.setName(userRequestDto.getName());
        userExist.setEmail(userRequestDto.getEmail());
        userExist.setPassword(userRequestDto.getPassword());
        userExist.setCpf(userRequestDto.getCpf());
        userExist.setCnpj(userRequestDto.getCnpj());
        userRepository.save(userExist);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userExist.getId());
        userResponseDto.setName(userExist.getName());
        userResponseDto.setEmail(userExist.getEmail());
        userResponseDto.setPassword(userExist.getPassword());
        userResponseDto.setCpf(userExist.getCpf());
        userResponseDto.setCnpj(userExist.getCnpj());

        return userResponseDto;
    }

    @Override
    public void deletar(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserResponseDto ativar(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setActive(true);
        User activatedUser = userRepository.save(userExist);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(activatedUser.getId());
        userResponseDto.setName(activatedUser.getName());
        userResponseDto.setEmail(activatedUser.getEmail());
        userResponseDto.setPassword(activatedUser.getPassword());
        userResponseDto.setCpf(activatedUser.getCpf());
        userResponseDto.setCnpj(activatedUser.getCnpj());
        userResponseDto.setActive(activatedUser.getActive());

        return userResponseDto;
    }

    @Override
    public UserResponseDto desativar(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setActive(false);
        User activatedUser = userRepository.save(userExist);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(activatedUser.getId());
        userResponseDto.setName(activatedUser.getName());
        userResponseDto.setEmail(activatedUser.getEmail());
        userResponseDto.setPassword(activatedUser.getPassword());
        userResponseDto.setCpf(activatedUser.getCpf());
        userResponseDto.setCnpj(activatedUser.getCnpj());
        userResponseDto.setActive(activatedUser.getActive());

        return userResponseDto;
    }

}
