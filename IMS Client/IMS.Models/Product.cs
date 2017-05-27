using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Product
    {

        [Display(Name = "Category")]
        public string category { get; set; }

        [Display(Name = "Guaratnee Info")]
        public string guaranteeInfo { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Supplier")]
        public string supplier { get; set; }
        public string type { get; set; }
    }
}
