import { Component, OnInit } from '@angular/core';
import { Chart } from 'angular-highcharts';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private api: ApiService) { }

  public columnChart: any;
  public projectCount: any;

  ngOnInit() {
    this.api.project_count().subscribe((res: any) => {
      this.projectCount = res;
    });

    this.api.chart_count().subscribe((response: any[][]): void => {
      const categories = response.map(item => item[0]); // First element is the department name
      const closedProjectsData = response.map(item => item[1]); // Second element is the closed projects count
      const totalProjectsData = response.map(item => item[2]); // Third element is the total projects count

      this.columnChart = new Chart({
        chart: {
          type: 'column'
        },
        title: {
          text: 'Department-wise Project Summary'
        },
        xAxis: {
          categories: categories,
          crosshair: true
        },
        yAxis: {
          title: {
            text: 'Number of Projects'
          },
          gridLineWidth: 0
        },
        tooltip: {
          headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
          pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
          footerFormat: '</table>',
          shared: true,
          useHTML: true
        },
        plotOptions: {
          column: {
            pointPadding: 0.2,
            borderWidth: 0,
            dataLabels: {
              enabled: true,
              format: '{point.y}'
            }
          }
        },
        series: [
          {
            color: '#025AAB',
            name: 'Total',
            data: totalProjectsData,
          },
          {
            color: '#5AA647',
            name: 'Closed',
            data: closedProjectsData,
          },
        ] as any[]
      });
    });
  }
}
