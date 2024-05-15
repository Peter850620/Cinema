package com.cinema;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "emp")
public class EmpVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name", length = 10)
    private String empName;

    @Column(name = "emp_password", length = 12,nullable = false)
    private String empPassword;

    @Column(name = "emp_email", length = 40)
    private String empEmail;

    @Column(name = "hiredate")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobVO jobVO;

    @Column(name = "emp_status", length = 3,nullable = false)
    private String empStatus;




    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public JobVO getJobVO() {
        return jobVO;
    }

    public void setJobVO(JobVO jobVO) {
        this.jobVO = jobVO;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public String toString() {
        return "Emp [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empEmail="
                + empEmail + ", hireDate=" + hireDate + ", job=" + jobVO + ", empStatus=" + empStatus + "]";
    }






}
