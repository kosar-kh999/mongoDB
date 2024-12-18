package com.example.mongodb.user.service;

import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.role.model.Role;
import com.example.mongodb.role.repository.RoleRepo;
import com.example.mongodb.user.dto.UserRequestDTO;
import com.example.mongodb.user.dto.UserResponseDTO;
import com.example.mongodb.user.mapper.UserMapper;
import com.example.mongodb.user.model.User;
import com.example.mongodb.user.record.ResetPasswordRecord;
import com.example.mongodb.user.record.UserRecord;
import com.example.mongodb.user.repository.UserRepo;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import com.example.mongodb.wallet.mapper.WalletMapper;
import com.example.mongodb.wallet.model.Wallet;
import com.example.mongodb.wallet.repository.WalletRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final RoleRepo roleRepo;
    private final WalletMapper walletMapper;
    private final WalletRepo walletRepo;

    public UserService(UserRepo userRepo,
                       UserMapper userMapper,
                       RoleRepo roleRepo,
                       WalletMapper walletMapper,
                       WalletRepo walletRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.roleRepo = roleRepo;
        this.walletMapper = walletMapper;
        this.walletRepo = walletRepo;
    }

    public String save(UserRequestDTO requestDTO) {
        Optional<User> user = userRepo.findByUsername(requestDTO.getUsername());
        if (user.isPresent())
            throw new CustomException(String.format("کاربر  %s قبلا ثبت نام کرده است.", requestDTO.getUsername()));
        User newUser = userMapper.toEntity(requestDTO);
        return userRepo.save(newUser).getId();
    }

    public UserResponseDTO findById(String id) {
        Optional<User> userOptional = userRepo.findById(id);
        User user = userOptional.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return userMapper.toDTO(user);
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        return userMapper.toDTO(users);
    }

    public void update(String id, UserRequestDTO requestDTO) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userMapper.toEntity(requestDTO, user);
        userRepo.save(user);
    }

    public void delete(String id) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepo.delete(user);
    }

    public void assignRoleForUser(UserRecord userRecord) {
        Optional<User> userOpt = userRepo.findById(userRecord.userId());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", userRecord.userId())));

        for (String roleId : userRecord.roleIds()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", roleId)));
            user.getRoles().add(role);
        }
        userMapper.toDTO(userRepo.save(user));
    }

    public void resetPasswordOfUser(ResetPasswordRecord record) {
        Optional<User> userOpt = userRepo.findById(record.id());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.id())));
        if (!(record.newPassword().equals(record.confirmedPassword())))
            throw new CustomException("رمز عبور وارد شده با تکرار رمز عبور آن برابر باشد.");
        user.setPassword(record.newPassword());
        userRepo.save(user);
    }

    @Transactional
    public void updateWalletOfUser() {
        List<User> users = userRepo.findAll();

        for (User user : users) {
            if (user.getWallet() == null) {
                Wallet wallet = new Wallet();
                wallet.setBalance(BigDecimal.ZERO);
                user.setWallet(wallet);
                walletRepo.save(wallet);
            }
        }
        userRepo.saveAll(users);
    }

    public List<WalletResponseDTO> getUserWalletHistory(String userId) {
        return userRepo.getUserWalletHistory(userId).stream().map(User::getWallet).map(walletMapper::toDTO).collect(Collectors.toList());
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public void save(String username, String email, String encodedPassword, String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName(role));

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        userRepo.save(user);
    }
}
