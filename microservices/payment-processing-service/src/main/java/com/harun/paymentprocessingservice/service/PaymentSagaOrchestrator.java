package com.harun.paymentprocessingservice.service;

import com.harun.common.dto.AccountDTO;
import com.harun.common.dto.TransactionDTO;

import com.harun.common.feign.impl.AccountServiceClientImpl;
import com.harun.common.feign.impl.MoneyTransferServiceClientImpl;

import com.harun.common.utils.StringBuilderUtil;
import com.harun.entity.enums.PaymentStatus;
import com.harun.entity.enums.TransactionType;
import com.harun.entity.models.Transaction;
import com.harun.paymentprocessingservice.dto.PaymentDTO;
import com.harun.paymentprocessingservice.mapper.MapperGenerator;
import com.harun.paymentprocessingservice.mapper.MapperGeneratorSingleton;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentSagaOrchestrator {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final PaymentService paymentService;
    private final AccountServiceClientImpl accountServiceClientImpl;
    private final MoneyTransferServiceClientImpl moneyTransferServiceClientImpl;

    private static final Logger logger = LoggerFactory.getLogger(PaymentSagaOrchestrator.class);

    private String message = "";


    public PaymentDTO processPayment(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        PaymentDTO paymentDTO = new PaymentDTO();
        AccountDTO sourceAccountDTO;
        AccountDTO targetAccountDTO;
        TransactionDTO transactionDTO;
        try {
            paymentDTO.setStatus(PaymentStatus.PENDING);

            message = StringBuilderUtil.buildMessage(
                    "Transfer is started: Source Account ID: {} Target Account ID: {} Amount: {}",
                    sourceAccountId, targetAccountId, amount
            );
            logger.info(message);

            paymentService.validateTransferInputs(sourceAccountId, targetAccountId, amount);
            logger.info("Transfer inputs validated.");

            sourceAccountDTO = getAccountById(sourceAccountId);
            message = StringBuilderUtil.buildMessage("Source account ID: {}", sourceAccountDTO.getId());
            logger.info(message);

            targetAccountDTO = getAccountById(targetAccountId);
            message = StringBuilderUtil.buildMessage("Target account ID: {}", targetAccountDTO.getId());
            logger.info(message);

            performMoneyTransfer(sourceAccountDTO, targetAccountDTO, amount);
            message = StringBuilderUtil.buildMessage("Transfer completed: Source Account ID: {} Target Account ID: {} Amount: {}",
                    sourceAccountDTO.getId(),
                    targetAccountDTO.getId(),
                    amount);
            logger.info(message);

            transactionDTO = moneyTransferServiceClientImpl.saveTransaction(createTransactionEntity(amount, sourceAccountDTO, targetAccountDTO));
            logger.info("Transaction saved");
            //TODO Transaction için rollback senaryosu oluştur.

            paymentDTO.setAmount(amount);
            paymentDTO.setStatus(PaymentStatus.COMPLETED);


        } catch (IllegalArgumentException e) {
            logger.error(StringBuilderUtil.buildMessage("Validation error: {}", e.getMessage()));
            paymentDTO.setStatus(PaymentStatus.FAILED);
            //notification
        } catch (EntityNotFoundException e) {
            logger.error(StringBuilderUtil.buildMessage("EntityNotFound error: {}", e.getMessage()));
            paymentDTO.setStatus(PaymentStatus.FAILED);
        }
        //TODO  //notification
//            catch(Exception e){
//
//            }
        return null;
    }

    private Transaction createTransactionEntity(BigDecimal amount, AccountDTO sourceAccount, AccountDTO targetAccount) {
        return Transaction.builder()
                .withAmount(amount)
                .withType(TransactionType.PAYMENT)
                .withSourceAccount(mapper.accountDTOToAccount(sourceAccount))
                .withTargetAccount(mapper.accountDTOToAccount(targetAccount))
                .build();
    }

    private AccountDTO getAccountById(Long sourceAccountId) {
        return accountServiceClientImpl.getAccountById(sourceAccountId);
    }

    private void performMoneyTransfer(AccountDTO sourceAccount, AccountDTO targetAccount, BigDecimal amount) {

        BigDecimal sourceBalance = sourceAccount.getBalance();
        BigDecimal targetBalance = targetAccount.getBalance();

        sourceAccount.setBalance(sourceBalance.subtract(amount));
        targetAccount.setBalance(targetBalance.add(amount));

        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(sourceAccount));
        accountServiceClientImpl.updateAccount(mapper.accountDTOToAccount(targetAccount));
        //TODO //Buraya custom bir error class oluştur.
        //TODO //circuit breaker oluştur.
    }
}
//
//            // Adım 2: Kaynak hesaptan bakiye düş
//            accountClient.debitAccount(sourceAccountId, amount);
//
//            // Adım 3: Hedef hesaba bakiye ekle
//            accountClient.creditAccount(targetAccountId, amount);
//
//            // Adım 4: İşlemi kaydet
//            Transaction transaction = transactionClient.createTransaction(sourceAccountId, targetAccountId, amount);
//
//            // Adım 5: Bildirim gönder
//            notificationClient.sendNotification(transaction.getId(), sourceAccountId, targetAccountId);
//
//        } catch (Exception e) {
//            // Telafi işlemleri
//            handleCompensation(sourceAccountId, targetAccountId, amount);
//            throw new RuntimeException("Saga işlemi başarısız: " + e.getMessage(), e);
//        }
//    }
//
//    private void handleCompensation(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
//        // Kaynak hesaba bakiye ekle
//        accountClient.creditAccount(sourceAccountId, amount);
//
//        // Hedef hesaptan bakiye düş
//        accountClient.debitAccount(targetAccountId, amount);
//
//        // Gerekirse diğer servislerde rollback işlemleri yapılabilir
//        }
//    }

