using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using IMS.DAL;
using IMS.Models;

namespace IMS.Mvc.Models
{
    public class CustomerVm : Customer
    {
       

        [Display (Name ="Payment Info")]
        public string SelectedPayment { get; set; }

        private readonly DataAccessLayer dl;
        public List<Payment> CustomerPayments { get; set; }

        public CustomerVm()
        {
            dl = new DataAccessLayer();
            CustomerPayments = dl.GetPayments();
        }

    }
}