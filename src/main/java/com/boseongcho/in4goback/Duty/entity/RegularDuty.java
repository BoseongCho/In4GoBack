//package com.boseongcho.in4goback.Duty.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.sql.Date;
//
//@Entity
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//public class RegularDuty {
//
//    @Id
//    @Column(name = "REGULAR_DUTY_CODE")
//    //시퀀스 구문 추가해야함
//    private String regularDutyCode;
//
//    /* 근무시간 변경이 개인별이 아닌 회사 전체로 바뀔 예정이라 테이블 및 코드도 달라지고 테이블도 따로 뽑아야 함 */
//    @ManyToOne
//    @JoinColumn(name = "DUTY_MEM_CODE")
//    private DutyMember dutyMemCode; // 사원번호
//
//    /* 근무시간 변경이 개인별이 아닌 회사 전체로 바뀔 예정이라 테이블 및 코드도 달라지고 테이블도 따로 뽑아야 함 */
//
//    @ManyToOne
//    @JoinColumn(name = "REGULAR_DUTY_CRITERIA_CODE")
//    private RegularDutyCriteria RegularDutyCriteriaCode; // 기준번호
//
//    @Column(name = "WORK_DAYS_COMBO")
//    private int workDaysCombo; // 근무요일코드
//
//    @Column(name = "ATTENDANCE_HOUR")
//    private int attendanceHour; // 출근예정시간
//
//    @Column(name = "LEAVE_HOUR")
//    private int leaveHour; // 퇴근예정시간
//
//    @Column(name ="BEGIN_DATE")
//    private Date beginDate; // 적용시작일
//
//    @Column(name ="EXPIRE_DATE")
//    private Date expireDate  ; // 적용종료일
//
//    @Column(name ="APPLICATION_DATE")
//    private String applicationDate  ; // 변경점 작성일
//
//    @Column(name ="IS_VALID")
//    private int isValid; // 유효여부 : false=0, true=1, default=1
//
//}
