using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IMS.Models
{
    public class Item
    {

        [Display(Name = "Description")]
        public string description { get; set; }

        [Display(Name = "Id")]
        public string id { get; set; }

        [Display(Name = "Name")]
        public string name { get; set; }

        [Display(Name = "Category")]
        public Product product { get; set; }

        [Display(Name = "Purchase Price")]
        public int purchasePrice { get; set; }


        [Display(Name = "Quantity")]
        public int quantity { get; set; }

        [Display(Name = "Rate")]
        [MaxLength(1,ErrorMessage = "Rating is from 0 to 9 only")]
        public string rate { get; set; }

        [Display(Name = "Selling Price")]
        public int sellingPrice { get; set; }

        [Display(Name = "Status")]
        public string status { get; set; }
        public string type { get; set; }

        [Display(Name = "Unit")]
        public string unit { get; set; }
    }
}
