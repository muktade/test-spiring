import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/types/user';
import { EmployeeService } from 'src/app/views/employee/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employees: User[] = [];

  constructor(private employeeSerevice: EmployeeService) { }

  ngOnInit(): void {
    this.employeeSerevice.getEmployees().subscribe((res) => {
      this.employees = res;
    });
  }

}
