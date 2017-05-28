using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Customer
    {


        public Customer()
        {
            currency = "NIS";
        }

        [Display(Name = "Account Balance")]
        public int accountBalance { get; set; }
        [Display(Name = "Currency")]
        public string currency { get; set; }
        [Display(Name = "BirthDate")]
        public string customerBirthDate { get; set; }
        [Display(Name = "First Name")]
        public string customerFirstName { get; set; }
        [Display(Name = "Last Name")]
        public string customerLastName { get; set; }

        [Display(Name = "Email")]
        public string email { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Payment Info")]
        public Payment paymentInfo { get; set; }
        public string type { get; set; }
    }
}
