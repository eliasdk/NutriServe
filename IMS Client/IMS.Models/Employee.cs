using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Employee
    {

        [Display(Name = "Address")]
        public string address { get; set; }

        [Display(Name = "Company Name")]
        public string companyName { get; set; }

        [Display(Name = "Email")]
        public string email { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Name")]
        public string name { get; set; }

        [Display(Name = "Notes")]
        public string notes { get; set; }

        [Display(Name = "Phone")]
        public string phone { get; set; }
        public string type { get; set; }

        [Display(Name = "WebSite")]
        public string website { get; set; }
    }
}
