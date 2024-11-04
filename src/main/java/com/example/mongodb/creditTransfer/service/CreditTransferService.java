package com.example.mongodb.creditTransfer.service;

import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.creditTransfer.dto.CreditTransferRequestDTO;
import com.example.mongodb.creditTransfer.dto.CreditTransferResponseDTO;
import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;
import com.example.mongodb.creditTransfer.mapper.CreditTransferMapper;
import com.example.mongodb.creditTransfer.model.CreditTransfer;
import com.example.mongodb.creditTransfer.record.AcceptCreditRecord;
import com.example.mongodb.creditTransfer.record.CreditFilterRecord;
import com.example.mongodb.creditTransfer.record.CreditTransferRecord;
import com.example.mongodb.creditTransfer.repository.CreditTransferRepo;
import com.example.mongodb.user.model.User;
import com.example.mongodb.user.repository.UserRepo;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import com.example.mongodb.wallet.mapper.WalletMapper;
import com.example.mongodb.wallet.model.Wallet;
import com.example.mongodb.wallet.repository.WalletRepo;
import com.example.mongodb.walletHistory.enumuration.TransactionType;
import com.example.mongodb.walletHistory.model.WalletHistory;
import com.example.mongodb.walletHistory.repository.WalletHistoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditTransferService {
    private final CreditTransferRepo creditTransferRepo;
    private final UserRepo userRepo;
    private final WalletRepo walletRepo;
    private final WalletHistoryRepo walletHistoryRepo;
    private final CreditTransferMapper creditTransferMapper;
    private final WalletMapper walletMapper;

    public CreditTransferService(CreditTransferRepo creditTransferRepo,
                                 UserRepo userRepo,
                                 WalletRepo walletRepo,
                                 WalletHistoryRepo walletHistoryRepo,
                                 CreditTransferMapper creditTransferMapper,
                                 WalletMapper walletMapper) {
        this.creditTransferRepo = creditTransferRepo;
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.walletHistoryRepo = walletHistoryRepo;
        this.creditTransferMapper = creditTransferMapper;
        this.walletMapper = walletMapper;
    }

    public String save(CreditTransferRequestDTO requestDTO) {
        CreditTransfer creditTransfer = creditTransferMapper.toEntity(requestDTO);
        return creditTransferRepo.save(creditTransfer).getId();
    }

    public void update(String id, CreditTransferRequestDTO requestDTO) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        creditTransferMapper.toEntity(requestDTO, creditTransfer);
        creditTransferRepo.save(creditTransfer);
    }

    public CreditTransferResponseDTO findById(String id) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return creditTransferMapper.toDTO(creditTransfer);
    }

    public Page<CreditTransferResponseDTO> findAll(Pageable pageable) {
        Page<CreditTransfer> all = creditTransferRepo.findAll(pageable);
        return creditTransferMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        creditTransferRepo.delete(creditTransfer);
    }

    public CreditTransferResponseDTO AddCreditForUser(CreditTransferRecord requestDTO) {
        if (requestDTO.userId() != null && requestDTO.amount() != null && requestDTO.amount().doubleValue() > 0) {
            Optional<User> userOpt = userRepo.findById(requestDTO.userId());
            User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.userId())));
            CreditTransfer creditTransfer = new CreditTransfer();
            creditTransfer.setUser(user);
            creditTransfer.setAmount(requestDTO.amount());
            creditTransfer.setCreditTransferType(CreditTransferType.INITIAL_REQUEST);
            creditTransfer.setDescription(requestDTO.description());
            creditTransferRepo.save(creditTransfer);
            return creditTransferMapper.toDTO(creditTransfer);
        } else throw new CustomException("مبلغ واریزی به کیف پول باید بیشتر از صفر باشد");
    }

    @Transactional
    public WalletResponseDTO increaseCredit(AcceptCreditRecord record) {
        Optional<User> userOpt = userRepo.findById(record.userId());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.userId())));
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(record.creditId());
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.creditId())));
        creditTransfer.getUser().setWallet(user.getWallet());
        Wallet wallet = user.getWallet();
        user.getWallet().setBalance(user.getWallet().getBalance().add(creditTransfer.getAmount()));
        userRepo.save(user);
        wallet.setBalance(user.getWallet().getBalance());
        walletRepo.save(wallet);
        return walletMapper.toDTO(creditTransfer.getUser().getWallet());
    }

    @Transactional
    public WalletResponseDTO acceptCreditTransfer(AcceptCreditRecord record) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(record.creditId());
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.creditId())));
        creditTransfer.setCreditTransferType(CreditTransferType.CONFIRMED);
        creditTransferRepo.save(creditTransfer);

        WalletResponseDTO walletResponseDTO = increaseCredit(record);

        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setCredit(creditTransfer.getAmount());
        walletHistory.setDescription(creditTransfer.getDescription());
        walletHistory.setTransactionDate(LocalDateTime.now());
        List<WalletHistory> walletHistories = creditTransfer.getUser().getWallet().getWalletHistories();
        walletHistories.add(walletHistory);
        walletHistory.setTransactionType(TransactionType.DEPOSIT);
        walletHistoryRepo.save(walletHistory);
        return walletResponseDTO;
    }

    public void rejectCreditTransfer(AcceptCreditRecord record) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(record.creditId());
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.creditId())));
        creditTransfer.setCreditTransferType(CreditTransferType.REJECT);
        creditTransferRepo.save(creditTransfer);
    }

    public List<CreditTransferResponseDTO> filter(CreditFilterRecord creditFilterRecord) {
        return creditTransferRepo.findByUserIdAndType(creditFilterRecord.userId(), creditFilterRecord.creditTransferType()).stream().map(creditTransferMapper::toDTO).collect(Collectors.toList());
    }
}
