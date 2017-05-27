using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using IMS.DAL;
using IMS.Models;

namespace IMS.Mvc.Models
{
    public class OrdersVm : Order
    {
        private DataAccessLayer dl;


        [Display(Name = "Shipment")]
        public string SelectedShipment { get; set; }

        [Display(Name = "Customer")]
        public string SelectedCustomer { get; set; }
        [Display(Name = "Owner")]
        public string SelectedEmployee { get; set; }

        [Display(Name = "Product")]
        public string SelectedProduct { get; set; }
        [Display(Name = "Payment")]
        public string SelectedPayment { get; set; }


        [Display(Name = "Item")]
        public string SelectedItem { get; set; }

        public OrdersVm()
        {
            dl = new DataAccessLayer();
            Shipments = dl.GetShipments();
            Customers = dl.GetCustomers();
            Payments = dl.GetPayments();
            Employees = dl.GetEmployees();
            Products = dl.GetProducts();
            Items = dl.GetItems();
            
        }

        [Display(Name = "Shipment")]
        public List<Shipment> Shipments { get; set; }

        [Display(Name = "Customer")]
        public List<Customer> Customers { get; set; }

        [Display(Name = "Payment")]
        public List<Payment> Payments { get; set; }

        [Display(Name = "Owner")]
        public List<Employee> Employees { get; set; }

        [Display(Name = "Product")]
        public List<Product> Products { get; set; }


        [Display(Name = "Item")]
        public List<Item> Items { get; set; }

    }
}