using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Order
    {

        [Display(Name = "Add To Stock?")]
        public bool addedToStock { get; set; }


        [Display(Name = "Customer")]
        public Customer customer { get; set; }

        [Display(Name = "Date Recieved")]
        public string dateReceived { get; set; }

        [Display(Name = "Date Stringed")]
        public string dateStringed { get; set; }

        [Display(Name = "Date Placed")]
        public string dateplaced { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Number")]
        public string number { get; set; }

        [Display(Name = "Outstanding")]
        public bool outstanding { get; set; }

        [Display(Name = "Payment Info")]
        public Payment payment { get; set; }


        [Display(Name = "Purchased Items")]
        public List<Item> purchasedItems { get; set; }

        [Display(Name = "Quantity")]
        public int quantity { get; set; }

        [Display(Name = "Shipment")]
        public Shipment shipment { get; set; }

        [Display(Name = "Total")]
        public int total { get; set; }
        public string type { get; set; }
    }
}
