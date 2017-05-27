using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using IMS.DAL;
using IMS.Models;

namespace IMS.Mvc.Models
{
    public class ShipmentVm : Shipment
    {

        [Display(Name = "Owner")]
        public string SelectedEmployee { get; set; }

        public List<Employee> Employees { get; set; }

        private DataAccessLayer dl;
        public ShipmentVm()
        {
            dl = new DataAccessLayer();
            Employees = dl.GetEmployees();
        }
    }
}