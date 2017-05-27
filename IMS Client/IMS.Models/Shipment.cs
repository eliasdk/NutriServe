using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
   public class Shipment
    {

        [Display(Name = "Address")]
        public string address { get; set; }

        [Display(Name = "Id")]
        public int id { get; set; }

        [Display(Name = "Owner")]
        public Employee owner { get; set; }

        [Display(Name = "Ship Date")]
        public string shipDate { get; set; }

       public string type { get; set; }
    }
}
