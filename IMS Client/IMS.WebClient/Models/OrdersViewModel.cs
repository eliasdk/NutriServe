using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using IMS.Models;

namespace IMS.WebClient.Models
{
    public class OrdersViewModel
    {

        public OrdersViewModel()
        {
            Orders = new List<Order>();
        }
        public List<Order> Orders { get; set; }
    }
}