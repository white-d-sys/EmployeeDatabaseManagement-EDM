package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.MonthlySalAttenNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.*;
import com.employee.EmployeeDatabaseManagement.EDM.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MonthlySalAttenService {
    private MonthlySalAttenRepository monthlySalAttenRepository;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private  PayrollStructRepository payrollStructRepository;
    private DailyAttendanceRepository dailyAttendanceRepository;
    @Autowired
    public MonthlySalAttenService(MonthlySalAttenRepository monthlySalAttenRepository,
                                  EmployeeRepository employeeRepository,
                                  PayrollStructRepository payrollStructRepository,
                                  DepartmentRepository departmentRepository,
                                  DailyAttendanceRepository dailyAttendanceRepository) {
        this.payrollStructRepository=payrollStructRepository;
        this.monthlySalAttenRepository = monthlySalAttenRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.dailyAttendanceRepository=dailyAttendanceRepository;
    }

    public List<MonthlySalAtten> listAll() {
        return monthlySalAttenRepository.findAll();
    }

    public MonthlySalAtten save(MonthlySalAtten monthlySalAtten) {
        return monthlySalAttenRepository.save(monthlySalAtten);
    }
    public List<MonthlySalAtten> listAll(Employee employee){
        System.out.println("working");
        return monthlySalAttenRepository.findAll().stream().filter(monthlySalAtten -> monthlySalAtten.getEmployee().getEmail().equalsIgnoreCase(employee.getEmail())).collect(Collectors.toList());

    }

    public MonthlySalAtten get(String id) {
        return monthlySalAttenRepository.findById(id).orElseThrow(MonthlySalAttenNoSuchElementException::new);
    }
    public List<MonthlySalAtten> getSalary(){
        for (Employee employee:employeeRepository.findAll()){
            if (monthlySalAttenRepository.findAll().stream().filter(dailyAttendance -> dailyAttendance.getEmployee().getId().equals(employee.getId())).collect(Collectors.toList()).isEmpty()){
//                getAllPayroll(employee.getDOJ(),employee);
            }else {
              List <MonthlySalAtten> monthlySal=monthlySalAttenRepository.findAll().stream().filter(monthlySalAtten ->
                        monthlySalAtten.getEmployee().getId().equals(employee.getId())).collect(Collectors.toList());
                LocalDate date=monthlySal.get(monthlySal.size()-1).getMonth();
                getAllPayroll(date,employee);
            }
        }
        return monthlySalAttenRepository.findAll();
    }
    public  void getAllPayroll(LocalDate date,Employee employee) {
        LocalDate date1 = date;
        Calendar calendar=Calendar.getInstance();
        YearMonth prev = YearMonth.of(date1.getYear(), date1.getMonth());
        PayrollStruct payrolls = new PayrollStruct();
        YearMonth next = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());
        int diff = next.getMonth().compareTo(prev.getMonth());
        List<DailyAttendance> attendanceList = dailyAttendanceRepository.findAll().stream().filter(dailyAttendance -> dailyAttendance.getEmployee().getId().equals(employee.getId())).collect(Collectors.toList());
        while (diff <= 0) {
            LocalDate finalDate = date1;
            LocalDate date2=LocalDate.of(date1.getYear(),date1.getMonth(),date1.lengthOfMonth());
            List<AttendanceMark> marks = attendanceList.stream().
                    filter(
                            dailyAttendance ->
                                    (finalDate.compareTo(dailyAttendance.getDate()) == 0 || finalDate.compareTo(dailyAttendance.getDate()) == 1)
                                            && (date2.compareTo(dailyAttendance.getDate()) == 0 ||date2.compareTo(dailyAttendance.getDate()) == 1)
                    ).map(dailyAttendance -> dailyAttendance.getMark()).collect(Collectors.toList());
            date1 = date1.plusMonths(1).withDayOfMonth(1);
            monthlySalAttenRepository.save(new MonthlySalAtten(finalDate, (int) marks.stream().filter(mark1 -> mark1.equals(AttendanceMark.P)).count(),
                    (int) marks.stream().filter(mark1 -> mark1.equals(AttendanceMark.PL)).count(), (int) marks.stream().filter(mark1 -> mark1.equals(AttendanceMark.UP)).count(), getSalary(marks, employee), employee));

        }
//List<Map<Employee,Map<List<LocalDate>,List<AttendanceMark>>>>
        //
    }
    public Map<String,List<AttendanceMark>> getMonthlyAttendance(LocalDate date){
        List<Employee> employeeList=employeeRepository.findAll();
       List<AttendanceMark> listOfAll=new ArrayList<>();
       HashMap<String,List<AttendanceMark>> listHashMap=new HashMap<>();
        for (Employee employee:employeeList){
            listHashMap.put(employee.getId(), dailyAttendanceRepository.findAll().stream().filter(dailyAttendance -> dailyAttendance.getEmployee().getId().equalsIgnoreCase(employee.getId()))
                    .filter(dailyAttendance -> dailyAttendance.getDate().getMonth().compareTo(date.getMonth())==0 &&dailyAttendance.getDate().getYear()==date.getYear()).map(dailyAttendance -> dailyAttendance.getMark()).collect(Collectors.toList()));
            listOfAll.clear();
        }
        return listHashMap;

    }
    //Calculate to monthly salary
    public Integer getSalary(List<AttendanceMark> list,Employee employee){
        Long base =list.stream().filter(mark1 -> mark1.equals(AttendanceMark.P)).count()+
                list.stream().filter(mark1 -> mark1.equals(AttendanceMark.PL)).count();
        PayrollStruct struct= departmentRepository.findAll().stream()
                .filter(payrollStruct -> payrollStruct.getId().equals(employee.getDepartment().getId())).map(department -> department.getPayrollStruct()).findFirst().get();
        Integer total= Math.toIntExact(base * struct.getBaseSalary());
        Integer temp=total;
        total= (struct.getDA()/100*total)+total;
        total=(struct.getHRA()/100*temp)+temp;
        total=(struct.getBonus()/100*temp)+temp;
        total=total-(struct.getDA()/100*temp);
        total=total-(struct.getDA()/100*temp);
        return total;
    }

    public MonthlySalAtten update(String id, MonthlySalAtten updateMonthlySalAtten) {
        MonthlySalAtten existingMonthlySalAtten = get(id);
        existingMonthlySalAtten.setMonth(updateMonthlySalAtten.getMonth());
        existingMonthlySalAtten.setMonthlyPresent(updateMonthlySalAtten.getMonthlyPresent());
        existingMonthlySalAtten.setPaidleave(updateMonthlySalAtten.getPaidleave());
        existingMonthlySalAtten.setUnpaidLeave(updateMonthlySalAtten.getUnpaidLeave());
        existingMonthlySalAtten.setSalary(updateMonthlySalAtten.getSalary());
        return monthlySalAttenRepository.save(existingMonthlySalAtten);
    }

    public List<MonthlySalAtten> listAll(LocalDate localDate) {
        listAll();
       return monthlySalAttenRepository.findAll().stream().filter(monthlySalAtten -> monthlySalAtten.getMonth().getYear()==localDate.getYear() && monthlySalAtten.getMonth().getMonth()==localDate.getMonth()).collect(Collectors.toList());
    }
}
