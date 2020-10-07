package com.younghun.hibusgo.service;

import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.mapper.AccountMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @InjectMocks
  private AccountService accountService;

  @Mock
  private AccountMapper accountMapper;

  @Mock
  private PasswordEncoder passwordEncoder;

  Account account;

  final String userId = "whdudgns2654@gmail.com";
  final String password = "younghun123!";

  @BeforeEach
  public void setUp() {
    account = Account.builder()
        .userId("whdudgns2654@gmail.com")
        .password("younghun123!")
        .name("younghun")
        .phoneNumber("010-6769-5355")
        .status(DataStatus.DEFAULT)
        .userLevel(UserLevel.USER)
        .build();
  }

  @Test
  public void 아이디로_회원_조회() throws Exception {
    //given
    given(accountMapper.findById(1L)).willReturn(account);

    //when
    final Account addedAccount = accountService.findById(1L);

    //then
    assertEquals(account.getId(), addedAccount.getId());
  }

  @Test
  public void 아이디로_회원_존재_확인() throws Exception {
    //given
    given(accountMapper.existsById(1L)).willReturn(true);

    //when
    final boolean existsById = accountService.existsById(1L);

    //then
    assertTrue(existsById);
  }

  @Test
  public void 회원_추가() throws Exception{
    //when
    accountService.addAccount(account);

    //then
    then(accountMapper)
        .should()
        .addAccount(any(Account.class));

  }

  @Test
  public void 아이디_비밀번호로_회원_조회() throws Exception{
    //given
    String encodePassword = passwordEncoder.encode(password);
    given(accountMapper.findByUserIdAndPassword(userId, encodePassword))
        .willReturn(account);

    //when
    final Optional<Account> addedAccount = accountService.findByUserIdAndPassword(userId, password);

    //then
    assertEquals(userId, addedAccount.get().getUserId());
  }

  @Test
  public void 회원_삭제() throws Exception{
    //when
    accountService.deleteAccount(1L);

    //then
    then(accountMapper)
        .should()
        .deleteAccount(anyLong());
  }

  @Test
  public void 비밀번호_수정() throws Exception{
    //given
    String encodePassword = passwordEncoder.encode(password);

    //when
    accountService.updatePassword(1L, password);

    //then
    then(accountMapper)
        .should()
        .updatePassword(1L, encodePassword);
  }

  @Test
  public void 회원정보_수정() throws Exception{
    //when
    accountService.updateAccountInfo(account);

    //then
    then(accountMapper)
        .should()
        .updateAccountInfo(account);
  }

}