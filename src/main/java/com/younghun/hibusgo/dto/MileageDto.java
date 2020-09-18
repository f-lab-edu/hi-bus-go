package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Mileage;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MileageDto {

    // 회원 아이디
    @NotBlank
    @Email
    @Max(255)
    private long accountId;

    // 마일리지
    @NotBlank
    @Min(0)
    private long mileage;

    // 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;

    public Mileage toEntity() {
        return Mileage.builder()
                .accountId(this.accountId)
                .mileage(this.mileage)
                .status(DataStatus.DEFAULT)
                .build();
    }

}
