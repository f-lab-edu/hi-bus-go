package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.aop.LoginCheck;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ROUTE_BAD_REQUEST;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_SEAT_BAD_REQUEST;


import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.dto.PasswordDto;
import com.younghun.hibusgo.dto.PaymentDto;
import com.younghun.hibusgo.dto.PaymentMeansDto;
import com.younghun.hibusgo.dto.ProfileDto;
import com.younghun.hibusgo.dto.ReservationDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import com.younghun.hibusgo.service.MileageService;
import com.younghun.hibusgo.service.PaymentService;
import com.younghun.hibusgo.service.ReservationService;
import com.younghun.hibusgo.service.RouteService;
import com.younghun.hibusgo.service.SeatService;
import com.younghun.hibusgo.utils.LoginUserId;
import com.younghun.hibusgo.validator.AccountDtoValidator;
import com.younghun.hibusgo.validator.PasswordValidator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class AccountController {

    private final AccountService accountService;
    private final LoginService loginService;
    private final MileageService mileageService;
    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final RouteService routeService;
    private final SeatService seatService;

    private final AccountDtoValidator accountDtoValidator;
    private final PasswordValidator passwordValidator;

    /**
     * accountDto객체에 바인딩된 값을 검증
     *
     * InitBinder는 특정 컨트롤러에서 바인딩 또는 검증 설정 변경에 사용한다.
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("accountDto")
    public void accountInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountDtoValidator);
    }

    /**
     * passwordDto객체에 바인딩 된 값을 검증
     *
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("passwordDto")
    public void passwordInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordValidator);
    }

    /**
     * 회원 가입 메소드
     *
     * 회원 가입 성공시 201(Created) code return.
     * 객체 valitaion 실패시 에러 정보와 400(Bad Request) code return.
     *
     * @param accountDto 저장할 회원의 정보
     * @return ResponseEntity(성공시 201 code, 실패시 400 code)
     */
    @PostMapping("/signUp")
    public ResponseEntity<Void> addAccount(@RequestBody @Valid AccountDto accountDto) {
        Account account = accountDto.toEntity();
        accountService.addAccount(account);

        return RESPONSE_ENTITY_CREATED;
    }

    /**
     * 회원 탈퇴 메소드
     *
     * @param accountId 로그인된 사용자 id
     * @return ResponseEntity(성공시 204 code)
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @DeleteMapping("/myInfo")
    public ResponseEntity<Void> deleteAccount(@LoginUserId Long accountId) {
        accountService.deleteAccount(accountId);
        loginService.accountLogout();

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 비밀번호 수정 메소드
     *
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return.
     * 객체 validation 실패시 에러정보와 400(Bad Request) code return.
     *
     * @param accountId 로그인한 사용자 id
     * @param passwordDto 이전 패스워드, 새로운 패스워드
     * @return ResponseEntity(성공시 201 code, 실패시 204 code)
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @PatchMapping("/myInfo/password")
    public ResponseEntity<Void> updatePassword(@LoginUserId Long accountId, @RequestBody @Valid PasswordDto passwordDto) {
        accountService.updatePassword(accountId, passwordDto.getNewPassword());

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 정보(이름, 폰번호) 수정 메소드
     *
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return.
     * 객체 validation 실패시 에러 정보와 400(Bad Request) code return.
     *
     * @param ProfileDto 프로필 정보
     * @return ResponseEntity(성공시 204 code, 실패시 400 code)
     * */
    @LoginCheck(userLevel = UserLevel.USER)
    @PatchMapping("/myInfo")
    public ResponseEntity<Void> updateAccountInfo(@RequestBody @Valid ProfileDto ProfileDto) {
        Account accountInfo = ProfileDto.toEntity();
        accountService.updateAccountInfo(accountInfo);

        return RESPONSE_ENTITY_NO_CONTENT;
    }


    /**
     * 회원 자신의 마일리지를 조회하는 메소드
     * @param accountId
     * @return ResponseEntity
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @GetMapping("/mileage")
    public ResponseEntity<?> getMyMileage(@LoginUserId long accountId) {
        Optional<Mileage> mileage = mileageService.findByAccountId(accountId);

        if (!mileage.isPresent()) {
            return RESPONSE_NOT_FOUND;
        }

        return ResponseEntity.ok().body(mileage.get());
    }

    /**
     * 회원의 결제 목록 조회 메서드
     * @param accountId 로그인한 회원 아이디
     * @return List<Payment>
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments(@LoginUserId long accountId) {
        List<Payment> payments = paymentService.findByAccountId(accountId);

        return ResponseEntity.ok().body(payments);
    }

    /**
     * 회원의 결제 정보 조회 메서드
     * @param paymentId 결제 아이디
     * @return ResponseEntity
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPayment(long paymentId) {
        Optional<Payment> payment = paymentService.findById(paymentId);

        return ResponseEntity.ok().body(payment.get());
    }


    /**
     * 회원의 예매 목록 조회 메서드
     * @param accountId 회원의 아이디
     * @return List<Reservation>
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@LoginUserId long accountId) {
        List<Reservation> reservations = reservationService.findByAccountId(accountId);

        return ResponseEntity.ok().body(reservations);
    }

    /**
     * 회원의 예매 정보 조회 메서드
     * @param id 예매 아이디
     * @return ResponseEntity<?>
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> getReservation(@PathVariable long id) {
        Optional<Reservation> reservation = reservationService.findById(id);

        if (!reservation.isPresent()) {
            return RESPONSE_NOT_FOUND;
        }

        return ResponseEntity.ok().body(reservation.get());
    }

    /**
     * 회원의 예매 정보 추가 메서드
     * @param
     * @return ResponseEntity<?>
     */
    @LoginCheck(userLevel = UserLevel.USER)
    @PostMapping("/reservations")
    public ResponseEntity<?> addReservation(@RequestBody @Valid PaymentDto paymentDto) {
        long routeId = paymentDto.getRouteId();
        long seatNumber = paymentDto.getSeatNumber();

        boolean existRoute = routeService.existsById(routeId);

        if (!existRoute) {
            return RESPONSE_ROUTE_BAD_REQUEST;
        }

        // 좌석 번호가 비어 있는 좌석인지
        boolean existEmptySeat = seatService.existEmptySeatByRoutIdAndNumber(routeId, seatNumber);

        if (!existEmptySeat) {
            return RESPONSE_SEAT_BAD_REQUEST;
        }

        reservationService.addReservation(paymentDto);

        return RESPONSE_ENTITY_CREATED;
    }

}
